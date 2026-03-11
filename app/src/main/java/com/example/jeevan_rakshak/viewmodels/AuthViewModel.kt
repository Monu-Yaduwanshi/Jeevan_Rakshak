package com.example.jeevan_rakshak.viewmodels

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.jeevan_rakshak.navigation.Screen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class AuthViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance().reference

    // 🔥 Loading state
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    // 🔥 User Role state
    private val _userRole = MutableStateFlow("patient")
    val userRole: StateFlow<String> = _userRole

    // 🔥 Auth state
    private val _isLoggedIn = MutableStateFlow(auth.currentUser != null)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    init {
        if (_isLoggedIn.value) {
            loadUserRole()
        }
    }

    // ==============================
    // ✅ SIGN UP
    // ==============================

    fun signUp(
        fullName: String,
        email: String,
        password: String,
        role: String,
        navController: NavController,
        showToast: (String) -> Unit
    ) {

        if (!validateSignUp(fullName, email, password, showToast)) return

        _isLoading.value = true

        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->

                val uid = result.user?.uid ?: return@addOnSuccessListener

                // Common user data
                val userMap = HashMap<String, Any>()
                userMap["uid"] = uid
                userMap["fullName"] = fullName
                userMap["email"] = email
                userMap["role"] = role
                userMap["createdAt"] = System.currentTimeMillis()
                userMap["isActive"] = true

                // Save to main users node
                database.child("users")
                    .child(uid)
                    .setValue(userMap)
                    .addOnSuccessListener {

                        // Save role-specific data
                        saveRoleSpecificData(uid, role, fullName, email)

                        _userRole.value = role
                        _isLoading.value = false

                        showToast("Account Created Successfully! Please Sign In")

                        // Navigate to Sign In instead of Home
                        navController.navigate(Screen.SignIn.route) {
                            popUpTo(Screen.SignUp.route) { inclusive = true }
                        }
                    }
                    .addOnFailureListener {
                        _isLoading.value = false
                        showToast("Database Error: ${it.message}")
                        // Rollback auth creation if database fails
                        auth.currentUser?.delete()
                    }
            }
            .addOnFailureListener {
                _isLoading.value = false
                showToast("Signup Failed: ${it.message}")
            }
    }

    // ==============================
    // ✅ SAVE ROLE-SPECIFIC DATA
    // ==============================

    private fun saveRoleSpecificData(uid: String, role: String, fullName: String, email: String) {
        val roleData = HashMap<String, Any>()
        roleData["uid"] = uid
        roleData["fullName"] = fullName
        roleData["email"] = email
        roleData["createdAt"] = System.currentTimeMillis()

        when (role) {
            "patient" -> {
                roleData["bloodGroup"] = ""
                roleData["emergencyContact"] = ""
                roleData["medicalHistory"] = ""
                roleData["allergies"] = ""
                roleData["defaultAddress"] = ""

                database.child("patients").child(uid).setValue(roleData)
            }
            "doctor" -> {
                roleData["specialization"] = ""
                roleData["licenseNumber"] = ""
                roleData["hospital"] = ""
                roleData["experience"] = ""
                roleData["available"] = true
                roleData["consultationFee"] = 0

                database.child("doctors").child(uid).setValue(roleData)

                // Initialize pending appointments node for doctor
                database.child("doctor_appointments").child(uid).child("pending").setValue(emptyMap<String, Any>())
            }
            "ambulance" -> {
                roleData["vehicleNumber"] = ""
                roleData["ambulanceType"] = "Basic Life Support"
                roleData["driverName"] = ""
                roleData["driverPhone"] = ""
                roleData["available"] = true
                roleData["currentLocation"] = ""
                roleData["latitude"] = 0.0
                roleData["longitude"] = 0.0

                database.child("ambulance_providers").child(uid).setValue(roleData)

                // Initialize emergency visits node for ambulance
                database.child("emergency_visits").child(uid).setValue(emptyMap<String, Any>())
            }
        }
    }

    // ==============================
    // ✅ SIGN IN
    // ==============================

    fun signIn(
        email: String,
        password: String,
        role: String,
        navController: NavController,
        showToast: (String) -> Unit
    ) {

        if (!validateSignIn(email, password, showToast)) return

        _isLoading.value = true

        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->

                val uid = result.user?.uid ?: return@addOnSuccessListener

                // Verify user role matches selected role
                database.child("users")
                    .child(uid)
                    .child("role")
                    .get()
                    .addOnSuccessListener { snapshot ->
                        val userRoleInDb = snapshot.value?.toString() ?: ""

                        if (userRoleInDb == role) {
                            _userRole.value = role
                            _isLoggedIn.value = true
                            _isLoading.value = false

                            showToast("Login Successful")

                            // Navigate to Home
                            navController.navigate(Screen.Home.route) {
                                popUpTo(Screen.SignIn.route) { inclusive = true }
                            }
                        } else {
                            // Role mismatch - sign out
                            auth.signOut()
                            _isLoading.value = false
                            showToast("Invalid role selected for this account")
                        }
                    }
                    .addOnFailureListener {
                        _isLoading.value = false
                        showToast("Failed to verify user role")
                        auth.signOut()
                    }
            }
            .addOnFailureListener {
                _isLoading.value = false
                showToast("Login Failed: ${it.message}")
            }
    }

    // ==============================
    // ✅ LOAD USER ROLE
    // ==============================

    private fun loadUserRole() {
        val uid = auth.currentUser?.uid ?: return

        database.child("users")
            .child(uid)
            .child("role")
            .get()
            .addOnSuccessListener {
                val role = it.value?.toString() ?: "patient"
                _userRole.value = role
            }
    }

    // ==============================
    // ✅ LOGOUT
    // ==============================
    fun logout(navController: NavController) {
        auth.signOut()
        _isLoggedIn.value = false
        _userRole.value = "patient"

        navController.navigate(Screen.SignIn.route) {
            popUpTo(0) { inclusive = true } // Clear entire back stack
            launchSingleTop = true
        }
    }
//    fun logout(navController: NavController) {
//        auth.signOut()
//        _isLoggedIn.value = false
//        _userRole.value = "patient"
//
//        navController.navigate(Screen.SignIn.route) {
//            popUpTo(0)
//        }
//    }

    // ==============================
    // ✅ VALIDATIONS
    // ==============================

    private fun validateSignUp(
        fullName: String,
        email: String,
        password: String,
        showToast: (String) -> Unit
    ): Boolean {

        if (fullName.isEmpty()) {
            showToast("Full Name required")
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showToast("Invalid Email")
            return false
        }

        if (password.length < 6) {
            showToast("Password must be at least 6 characters")
            return false
        }

        return true
    }

    private fun validateSignIn(
        email: String,
        password: String,
        showToast: (String) -> Unit
    ): Boolean {

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showToast("Invalid Email")
            return false
        }

        if (password.isEmpty()) {
            showToast("Password required")
            return false
        }

        return true
    }
}