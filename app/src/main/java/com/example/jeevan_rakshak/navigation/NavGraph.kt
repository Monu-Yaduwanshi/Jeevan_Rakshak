package com.example.jeevan_rakshak.navigation

import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jeevan_rakshak.ui.drawer.*
import com.example.jeevan_rakshak.ui.screens.SplashScreen
import com.example.jeevan_rakshak.ui.screens.bottomNavBar.*
import com.example.jeevan_rakshak.viewmodels.SignInScreen
import com.example.jeevan_rakshak.viewmodels.SignUpScreen
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

@Composable
fun NavGraph(navController: NavHostController) {

    val auth = FirebaseAuth.getInstance()

    // Use a state to handle authentication check
    var isCheckingAuth by remember { mutableStateOf(true) }
    var startDestination by remember { mutableStateOf(Screen.Splash.route) }

    // Check authentication status
    LaunchedEffect(Unit) {
        // Small delay to show splash screen
        delay(500)

        startDestination = if (auth.currentUser != null) {
            Screen.Home.route
        } else {
            Screen.SignIn.route
        }
        isCheckingAuth = false
    }

    // Show nothing while checking auth
    if (isCheckingAuth) {
        SplashScreen(navController)
        return
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // ============================================
        // AUTH SCREENS
        // ============================================
        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }

        composable(Screen.SignIn.route) {
            SignInScreen(navController)
        }

        composable(Screen.SignUp.route) {
            SignUpScreen(navController)
        }

        // ============================================
        // MAIN HOME SCREEN
        // ============================================
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }

        // ============================================
        // BOTTOM NAVIGATION SCREENS
        // ============================================
        composable(Screen.Ambulance.route) {
            AmbulanceScreen(navController)
        }

        composable(Screen.Pharmacy.route) {
            PharmacyScreen(navController)
        }

        composable(Screen.Hospital.route) {
            HospitalScreen(navController)
        }

        composable(Screen.Profile.route) {
            ProfileScreen(navController)
        }

        // ============================================
        // PATIENT SCREENS
        // ============================================
        composable(Screen.BookingHistory.route) {
            BookingHistoryScreen(navController)
        }

        composable(Screen.MyOrders.route) {
            MyOrdersScreen(navController)
        }

        composable(Screen.Address.route) {
            AddressScreen(navController)
        }

        composable(Screen.MedicalRecords.route) {
            MedicalRecordsScreen(navController)
        }

        // ============================================
        // DOCTOR SCREENS
        // ============================================
        composable(Screen.PendingAppointments.route) {
            PendingAppointmentsScreen(navController)
        }

        composable(Screen.DoctorSchedule.route) {
            DoctorScheduleScreen(navController)
        }

        composable(Screen.MyPatients.route) {
            MyPatientsScreen(navController)
        }

        composable(Screen.ConsultationHistory.route) {
            ConsultationHistoryScreen(navController)
        }

        // ============================================
        // AMBULANCE SCREENS
        // ============================================
        composable(Screen.EmergencyVisit.route) {
            EmergencyVisitScreen(navController)
        }

        composable(Screen.UpdateLocation.route) {
            UpdateLocationScreen(navController)
        }

        composable(Screen.TripHistory.route) {
            TripHistoryScreen(navController)
        }

        composable(Screen.VehicleStatus.route) {
            VehicleStatusScreen(navController)
        }

        // ============================================
        // COMMON SCREENS (All Users)
        // ============================================
        composable(Screen.MyContacts.route) {
            MyContactsScreen(navController)
        }

        composable(Screen.Account.route) {
            AccountScreen(navController)
        }

        composable(Screen.Notifications.route) {
            NotificationsScreen(navController)
        }

        composable(Screen.Cart.route) {
            CartScreen(navController)
        }

        composable(Screen.HelpSupport.route) {
            HelpSupportScreen(navController)
        }

        composable(Screen.AboutApp.route) {
            AboutAppScreen(navController)
        }

        // In NavGraph.kt, update the LogoutScreen composable:
        composable(Screen.Logout.route) {
            LogoutScreen(
                navController = navController,
                viewModel = androidx.lifecycle.viewmodel.compose.viewModel() // Pass ViewModel
            )
        }
//        composable(Screen.Logout.route) {
//            LogoutScreen(navController)
//        }
    }
}