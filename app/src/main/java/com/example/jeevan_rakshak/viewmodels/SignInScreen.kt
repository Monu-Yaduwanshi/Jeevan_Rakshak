package com.example.jeevan_rakshak.viewmodels
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.jeevan_rakshak.R
import com.example.jeevan_rakshak.navigation.Screen
import com.example.jeevan_rakshak.ui.screens.bottomNavBar.safeClickable
import com.example.jeevan_rakshak.ui.theme.*
import com.example.jeevan_rakshak.viewmodels.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    navController: NavController,
    viewModel: AuthViewModel = viewModel()
) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var selectedRole by remember { mutableStateOf("patient") }
    var passwordVisible by remember { mutableStateOf(false) }

    // Validation states
    var isEmailValid by remember { mutableStateOf(true) }
    var isPasswordValid by remember { mutableStateOf(true) }
    var isRoleSelected by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(FieldBackground)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            item {
                // 🔵 Company Logo in Circle
                Card(
                    modifier = Modifier.size(100.dp),
                    shape = CircleShape,
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logostatic),
                        contentDescription = "App Logo",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 🔵 App Name in Bold
                Text(
                    text = "Jeevan Rakshak",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = HeadingBlue
                )

                Spacer(modifier = Modifier.height(8.dp))

                // 🔵 Sign In Heading
                Text(
                    text = "Sign In",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = HeadingBlue
                )

                Spacer(modifier = Modifier.height(32.dp))
            }

            item {
                // 🔹 Email Field with Icon
                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                        isEmailValid = true
                    },
                    label = { Text("Email Address", color = TextBlue) },
                    leadingIcon = {
                        Icon(
                            Icons.Default.Email,
                            contentDescription = "Email Icon",
                            tint = TextBlue
                        )
                    },
                    isError = !isEmailValid,
                    supportingText = {
                        if (!isEmailValid) {
                            Text(
                                text = "Please enter a valid email",
                                color = ButtonRed,
                                fontSize = 12.sp
                            )
                        }
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedBorderColor = HeadingBlue,
                        unfocusedBorderColor = TextBlue,
                        cursorColor = HeadingBlue
                    ),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))
            }

            item {
                // 🔹 Password Field with Icon
                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        isPasswordValid = true
                    },
                    label = { Text("Password", color = TextBlue) },
                    leadingIcon = {
                        Icon(
                            Icons.Default.Lock,
                            contentDescription = "Password Icon",
                            tint = TextBlue
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                painter = painterResource(
                                    id = if (passwordVisible)
                                        R.drawable.visibility
                                    else
                                        R.drawable.visible
                                ),
                                contentDescription = "Toggle Password Visibility",
                                tint = TextBlue
                            )
                        }
                    },
                    visualTransformation = if (passwordVisible)
                        VisualTransformation.None
                    else
                        PasswordVisualTransformation(),
                    isError = !isPasswordValid,
                    supportingText = {
                        if (!isPasswordValid) {
                            Text(
                                text = "Password cannot be empty",
                                color = ButtonRed,
                                fontSize = 12.sp
                            )
                        }
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedBorderColor = HeadingBlue,
                        unfocusedBorderColor = TextBlue,
                        cursorColor = HeadingBlue
                    ),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))
            }

            item {
                // 🔹 User Type Heading with Icon
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = "User Type",
                        tint = TextBlue,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Select User Type",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextBlue
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))
            }

            item {
                // 🔥 Role Selection with Cards
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    listOf(
                        Triple("Patient", R.drawable.patient, "patient"),
                        Triple("Doctor", R.drawable.doctor, "doctor"),
                        Triple("Ambulance", R.drawable.ambulance, "ambulance")
                    ).forEach { (roleName, iconId, roleValue) ->

                        Card(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 4.dp)
                                .safeClickable  {
                                    selectedRole = roleValue
                                    isRoleSelected = true
                                },
                            colors = CardDefaults.cardColors(
                                containerColor = if (selectedRole == roleValue)
                                    ButtonRed
                                else
                                    Color.White
                            ),
                            shape = RoundedCornerShape(12.dp),
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 12.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    painter = painterResource(id = iconId),
                                    contentDescription = roleName,
                                    tint = if (selectedRole == roleValue)
                                        Color.White
                                    else
                                        TextBlue,
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = roleName,
                                    color = if (selectedRole == roleValue)
                                        Color.White
                                    else
                                        TextBlue,
                                    fontSize = 12.sp,
                                    fontWeight = if (selectedRole == roleValue)
                                        FontWeight.Bold
                                    else
                                        FontWeight.Normal
                                )
                            }
                        }
                    }
                }

                if (!isRoleSelected) {
                    Text(
                        text = "Please select a user type",
                        color = ButtonRed,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .align(Alignment.Start as Alignment)
                            .padding(top = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                // 🔥 Sign In Button
                Button(
                    onClick = {
                        // Validate inputs
                        isEmailValid = email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
                        isPasswordValid = password.isNotEmpty()
                        isRoleSelected = selectedRole.isNotEmpty()

                        if (isEmailValid && isPasswordValid && isRoleSelected) {
                            viewModel.signIn(
                                email = email.trim(),
                                password = password.trim(),
                                role = selectedRole,
                                navController = navController,
                                showToast = { message ->
                                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                                }
                            )
                        } else {
                            when {
                                !isEmailValid -> Toast.makeText(context, "Please enter a valid email", Toast.LENGTH_SHORT).show()
                                !isPasswordValid -> Toast.makeText(context, "Password cannot be empty", Toast.LENGTH_SHORT).show()
                                !isRoleSelected -> Toast.makeText(context, "Please select a user type", Toast.LENGTH_SHORT).show()
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ButtonRed,
                        disabledContainerColor = ButtonRed.copy(alpha = 0.5f)
                    ),
                    shape = RoundedCornerShape(14.dp),
                    enabled = !viewModel.isLoading.collectAsState().value
                ) {
                    if (viewModel.isLoading.collectAsState().value) {
                        CircularProgressIndicator(
                            color = Color.White,
                            strokeWidth = 2.dp,
                            modifier = Modifier.size(24.dp)
                        )
                    } else {
                        Text(
                            text = "Sign In",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                // 🔁 Navigate to SignUp
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp)
                ) {
                    Text(
                        text = "Don't have an account? ",
                        color = TextBlue
                    )
                    Text(
                        text = "Sign Up",
                        color = HeadingBlue,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.safeClickable  {
                            navController.navigate(Screen.SignUp.route)
                        }
                    )
                }
            }
        }
    }
}