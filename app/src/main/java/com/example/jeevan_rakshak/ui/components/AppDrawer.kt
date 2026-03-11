package com.example.jeevan_rakshak.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jeevan_rakshak.navigation.Screen
import com.example.jeevan_rakshak.ui.theme.*
import com.example.jeevan_rakshak.viewmodels.ButtonRed
import com.example.jeevan_rakshak.viewmodels.HeadingBlue
import com.example.jeevan_rakshak.viewmodels.TextBlue
import com.example.jeevan_rakshak.R

@Composable
fun AppDrawer(
    navController: NavController,
    userRole: String,
    onLogout: () -> Unit
) {
    ModalDrawerSheet(
        drawerContainerColor = Color.White,
        drawerContentColor = HeadingBlue
    ) {
        // Drawer Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .background(ButtonRed)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // App Logo Circle
                Card(
                    modifier = Modifier.size(60.dp),
                    shape = CircleShape,
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(HeadingBlue),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logostaticbackground),
                            contentDescription = "App Logo",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
//                        Icon(
//                            imageVector = Icons.Default.HealthAndSafety,
//                            contentDescription = "App Logo",
//                            tint = Color.White,
//                            modifier = Modifier.size(40.dp)
//                        )
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = "Jeevan Rakshak",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = userRole.uppercase(),
                        color = Color.White.copy(alpha = 0.8f),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // 🔥 Role Specific Items
        when (userRole) {
            "patient" -> {
                DrawerItem(
                    title = "Booking History",
                    icon = Icons.Default.History
                ) {
                    navController.navigate(Screen.BookingHistory.route)
                }
                DrawerItem(
                    title = "My Orders",
                    icon = Icons.Default.ShoppingCart
                ) {
                    navController.navigate(Screen.MyOrders.route)
                }
                DrawerItem(
                    title = "Address",
                    icon = Icons.Default.LocationOn
                ) {
                    navController.navigate(Screen.Address.route)
                }
                DrawerItem(
                    title = "Medical Records",
                    icon = Icons.Default.MedicalServices
                ) {
                    navController.navigate(Screen.MedicalRecords.route)
                }
            }

            "doctor" -> {
                DrawerItem(
                    title = "Pending Appointments",
                    icon = Icons.Default.Schedule
                ) {
                    navController.navigate(Screen.PendingAppointments.route)
                }
                DrawerItem(
                    title = "Today's Schedule",
                    icon = Icons.Default.CalendarToday
                ) {
                    navController.navigate(Screen.DoctorSchedule.route)
                }
                DrawerItem(
                    title = "My Patients",
                    icon = Icons.Default.People
                ) {
                    navController.navigate(Screen.MyPatients.route)
                }
                DrawerItem(
                    title = "Consultation History",
                    icon = Icons.Default.MedicalInformation
                ) {
                    navController.navigate(Screen.ConsultationHistory.route)
                }
            }

            "ambulance" -> {
                DrawerItem(
                    title = "Emergency Visits",
                    icon = Icons.Default.Emergency
                ) {
                    navController.navigate(Screen.EmergencyVisit.route)
                }
                DrawerItem(
                    title = "Current Location",
                    icon = Icons.Default.MyLocation
                ) {
                    navController.navigate(Screen.UpdateLocation.route)
                }
                DrawerItem(
                    title = "Trip History",
                    icon = Icons.Default.TripOrigin
                ) {
                    navController.navigate(Screen.TripHistory.route)
                }
                DrawerItem(
                    title = "Vehicle Status",
                    icon = Icons.Default.DirectionsCar
                ) {
                    navController.navigate(Screen.VehicleStatus.route)
                }
            }
        }

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp),
            color = TextBlue.copy(alpha = 0.3f)
        )

        // 🔹 Common Items For All
        DrawerItem(
            title = "My Contacts",
            icon = Icons.Default.Contacts
        ) {
            navController.navigate(Screen.MyContacts.route)
        }
        DrawerItem(
            title = "Account Settings",
            icon = Icons.Default.Settings
        ) {
            navController.navigate(Screen.Account.route)
        }
        DrawerItem(
            title = "Notifications",
            icon = Icons.Default.Notifications
        ) {
            navController.navigate(Screen.Notifications.route)
        }
        DrawerItem(
            title = "Help & Support",
            icon = Icons.Default.Help
        ) {
            navController.navigate(Screen.HelpSupport.route)
        }
        DrawerItem(
            title = "About App",
            icon = Icons.Default.Info
        ) {
            navController.navigate(Screen.AboutApp.route)
        }

        Spacer(modifier = Modifier.height(20.dp))

//        DrawerItem(
//            title = "Logout",
//            icon = Icons.Default.Logout
//        )
        // ============================================
// LOGOUT - Navigate to LogoutScreen
// ============================================
        DrawerItem(
            title = "Logout",
            icon = Icons.Default.Logout
        ) {
            // Navigate to LogoutScreen instead of calling onLogout directly
            navController.navigate(Screen.Logout.route)
        }
//        {
//            onLogout()
//        }
    }
}

@Composable
fun DrawerItem(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    NavigationDrawerItem(
        label = {
            Text(
                text = title,
                color = HeadingBlue,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium
            )
        },
        selected = false,
        onClick = onClick,
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = TextBlue,
                modifier = Modifier.size(24.dp)
            )
        },
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
        colors = NavigationDrawerItemDefaults.colors(
            selectedContainerColor = Color.Transparent,
            unselectedContainerColor = Color.Transparent,
            selectedIconColor = ButtonRed,
            unselectedIconColor = TextBlue,
            selectedTextColor = ButtonRed,
            unselectedTextColor = HeadingBlue
        )
    )
}
//package com.example.jeevan_rakshak.ui.components
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.background
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import com.example.jeevan_rakshak.navigation.Screen
//import com.example.jeevan_rakshak.ui.theme.*
//import com.example.jeevan_rakshak.viewmodels.ButtonRed
//import com.example.jeevan_rakshak.viewmodels.HeadingBlue
//import com.example.jeevan_rakshak.viewmodels.TextBlue
//import com.example.jeevan_rakshak.R
//
//@Composable
//fun AppDrawer(
//    navController: NavController,
//    userRole: String,
//    onLogout: () -> Unit
//) {
//    ModalDrawerSheet(
//        drawerContainerColor = Color.White,
//        drawerContentColor = HeadingBlue
//    ) {
//        // Drawer Header
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(140.dp)
//                .background(ButtonRed)
//        ) {
//            Row(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(16.dp),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                // App Logo Circle
//                Card(
//                    modifier = Modifier.size(60.dp),
//                    shape = CircleShape,
//                    elevation = CardDefaults.cardElevation(4.dp)
//                ) {
//                    Box(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .background(HeadingBlue),
//                        contentAlignment = Alignment.Center
//                    ) {
//                        Image(
//                            painter = painterResource(id = R.drawable.logostaticbackground),
//                            contentDescription = "App Logo",
//                            contentScale = ContentScale.Crop,
//                            modifier = Modifier.fillMaxSize()
//                        )
//                    }
//                }
//
//                Spacer(modifier = Modifier.width(16.dp))
//
//                Column {
//                    Text(
//                        text = "Jeevan Rakshak",
//                        color = Color.White,
//                        fontSize = 20.sp,
//                        fontWeight = FontWeight.Bold
//                    )
//                    Text(
//                        text = userRole.uppercase(),
//                        color = Color.White.copy(alpha = 0.8f),
//                        fontSize = 14.sp,
//                        fontWeight = FontWeight.Medium
//                    )
//                }
//            }
//        }
//
//        Spacer(modifier = Modifier.height(10.dp))
//
//        // ============================================
//        // ROLE-SPECIFIC SECTION
//        // ============================================
//        when (userRole) {
//            "patient" -> {
//                DrawerSection(title = "PATIENT MENU")
//
//                DrawerItem(
//                    title = "Booking History",
//                    icon = Icons.Default.History
//                ) {
//                    navController.navigate(Screen.BookingHistory.route)
//                }
//                DrawerItem(
//                    title = "My Orders",
//                    icon = Icons.Default.ShoppingCart
//                ) {
//                    navController.navigate(Screen.MyOrders.route)
//                }
//                DrawerItem(
//                    title = "Address",
//                    icon = Icons.Default.LocationOn
//                ) {
//                    navController.navigate(Screen.Address.route)
//                }
//                DrawerItem(
//                    title = "Medical Records",
//                    icon = Icons.Default.MedicalServices
//                ) {
//                    navController.navigate(Screen.MedicalRecords.route)
//                }
//            }
//
//            "doctor" -> {
//                DrawerSection(title = "DOCTOR MENU")
//
//                DrawerItem(
//                    title = "Pending Appointments",
//                    icon = Icons.Default.Schedule
//                ) {
//                    navController.navigate(Screen.PendingAppointments.route)
//                }
//                DrawerItem(
//                    title = "Today's Schedule",
//                    icon = Icons.Default.CalendarToday
//                ) {
//                    navController.navigate(Screen.DoctorSchedule.route)
//                }
//                DrawerItem(
//                    title = "My Patients",
//                    icon = Icons.Default.People
//                ) {
//                    navController.navigate(Screen.MyPatients.route)
//                }
//                DrawerItem(
//                    title = "Consultation History",
//                    icon = Icons.Default.MedicalInformation
//                ) {
//                    navController.navigate(Screen.ConsultationHistory.route)
//                }
//            }
//
//            "ambulance" -> {
//                DrawerSection(title = "AMBULANCE MENU")
//
//                DrawerItem(
//                    title = "Emergency Visits",
//                    icon = Icons.Default.Emergency
//                ) {
//                    navController.navigate(Screen.EmergencyVisit.route)
//                }
//                DrawerItem(
//                    title = "Current Location",
//                    icon = Icons.Default.MyLocation
//                ) {
//                    navController.navigate(Screen.UpdateLocation.route)
//                }
//                DrawerItem(
//                    title = "Trip History",
//                    icon = Icons.Default.TripOrigin
//                ) {
//                    navController.navigate(Screen.TripHistory.route)
//                }
//                DrawerItem(
//                    title = "Vehicle Status",
//                    icon = Icons.Default.DirectionsCar
//                ) {
//                    navController.navigate(Screen.VehicleStatus.route)
//                }
//            }
//        }
//
//        HorizontalDivider(
//            modifier = Modifier.padding(vertical = 8.dp),
//            color = TextBlue.copy(alpha = 0.3f)
//        )
//
//        // ============================================
//        // COMMON SECTION (FOR ALL USERS)
//        // ============================================
//        DrawerSection(title = "COMMON MENU")
//
//        DrawerItem(
//            title = "My Contacts",
//            icon = Icons.Default.Contacts
//        ) {
//            navController.navigate(Screen.MyContacts.route)
//        }
//        DrawerItem(
//            title = "Account Settings",
//            icon = Icons.Default.Settings
//        ) {
//            navController.navigate(Screen.Account.route)
//        }
//        DrawerItem(
//            title = "Notifications",
//            icon = Icons.Default.Notifications
//        ) {
//            navController.navigate(Screen.Notifications.route)
//        }
//        DrawerItem(
//            title = "Cart",
//            icon = Icons.Default.ShoppingCart
//        ) {
//            navController.navigate(Screen.Cart.route)
//        }
//        DrawerItem(
//            title = "Help & Support",
//            icon = Icons.Default.Help
//        ) {
//            navController.navigate(Screen.HelpSupport.route)
//        }
//        DrawerItem(
//            title = "About App",
//            icon = Icons.Default.Info
//        ) {
//            navController.navigate(Screen.AboutApp.route)
//        }
//
//        Spacer(modifier = Modifier.height(20.dp))
//
//        // ============================================
//        // LOGOUT (COMMON)
//        // ============================================
//        DrawerItem(
//            title = "Logout",
//            icon = Icons.Default.Logout
//        ) {
//            onLogout()
//        }
//    }
//}
//
//@Composable
//fun DrawerSection(title: String) {
//    Text(
//        text = title,
//        fontSize = 12.sp,
//        fontWeight = FontWeight.Bold,
//        color = TextBlue,
//        modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 4.dp)
//    )
//}
//
//@Composable
//fun DrawerItem(
//    title: String,
//    icon: ImageVector,
//    onClick: () -> Unit
//) {
//    NavigationDrawerItem(
//        label = {
//            Text(
//                text = title,
//                color = HeadingBlue,
//                fontSize = 15.sp,
//                fontWeight = FontWeight.Medium
//            )
//        },
//        selected = false,
//        onClick = onClick,
//        icon = {
//            Icon(
//                imageVector = icon,
//                contentDescription = title,
//                tint = TextBlue,
//                modifier = Modifier.size(24.dp)
//            )
//        },
//        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
//        colors = NavigationDrawerItemDefaults.colors(
//            selectedContainerColor = Color.Transparent,
//            unselectedContainerColor = Color.Transparent,
//            selectedIconColor = ButtonRed,
//            unselectedIconColor = TextBlue,
//            selectedTextColor = ButtonRed,
//            unselectedTextColor = HeadingBlue
//        )
//    )
//}