package com.example.jeevan_rakshak.ui.screens.bottomNavBar

import androidx.compose.ui.unit.sp
import com.example.jeevan_rakshak.viewmodels.FieldBackground
import com.example.jeevan_rakshak.viewmodels.HeadingBlue
import com.example.jeevan_rakshak.viewmodels.TextBlue
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.jeevan_rakshak.navigation.Screen
import com.example.jeevan_rakshak.viewmodels.AuthViewModel
import androidx.compose.runtime.collectAsState
import com.example.jeevan_rakshak.ui.theme.*
import com.example.jeevan_rakshak.viewmodels.ButtonRed
import kotlinx.coroutines.launch
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.jeevan_rakshak.ui.components.AppDrawer
import com.example.jeevan_rakshak.ui.components.BottomBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: AuthViewModel = viewModel()
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val role by viewModel.userRole.collectAsState()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            AppDrawer(
                navController = navController,
                userRole = role,
                onLogout = {
                    viewModel.logout(navController)
                }
            )
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Jeevan Rakshak",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF062d66),
                        titleContentColor = Color.White
                    ),
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch { drawerState.open() }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu",
                                tint = Color.White
                            )
                        }
                    },
                    actions = {
                        // Cart Icon
                        IconButton(
                            onClick = {
                                navController.navigate(Screen.MyOrders.route) // or your cart screen route
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = "Cart",
                                tint = Color.White
                            )
                        }
                        // Notification Icon
                        IconButton(
                            onClick = {
                                navController.navigate(Screen.Notifications.route)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Notifications,
                                contentDescription = "Notifications",
                                tint = Color.White
                            )
                        }
                    }
                )
            },
            bottomBar = {
                BottomBar(navController = navController)
            }
        ) { paddingValues ->
            // Main Content Area based on role
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(FieldBackground)
            ) {
                when (role) {
                    "patient" -> PatientHomeContent(navController)
                    "doctor" -> DoctorHomeContent(navController)
                    "ambulance" -> AmbulanceHomeContent(navController)
                    else -> DefaultHomeContent(role)
                }
            }
        }
    }
}

@Composable
fun PatientHomeContent(navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            // Welcome Card with Profile Summary
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(4.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Welcome Back!",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = HeadingBlue
                        )
                        Text(
                            text = "Your health is our priority",
                            fontSize = 14.sp,
                            color = TextBlue
                        )
                    }

                    // Profile Avatar
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(ButtonRed.copy(alpha = 0.1f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Profile",
                            tint = ButtonRed,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            }
        }

        item {
            // Quick Actions Section
            Text(
                text = "Quick Actions",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = HeadingBlue,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        item {
            // First Row of Quick Actions
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                QuickActionCard(
                    title = "Book Ambulance",
                    icon = Icons.Default.AirportShuttle,
                    onClick = { navController.navigate(Screen.Ambulance.route) },
                    modifier = Modifier.weight(1f)
                )
                QuickActionCard(
                    title = "Buy Medicine",
                    icon = Icons.Default.LocalPharmacy,
                    onClick = { navController.navigate(Screen.Pharmacy.route) },
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item {
            // Second Row of Quick Actions
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                QuickActionCard(
                    title = "Find Hospital",
                    icon = Icons.Default.LocalHospital,
                    onClick = { navController.navigate(Screen.Hospital.route) },
                    modifier = Modifier.weight(1f)
                )
                QuickActionCard(
                    title = "My Profile",
                    icon = Icons.Default.AccountCircle,
                    onClick = { navController.navigate(Screen.Profile.route) },
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item {
            // SOS Emergency Button
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = cardColors(
                    containerColor = ButtonRed
                ),
                elevation = CardDefaults.cardElevation(8.dp),
                shape = RoundedCornerShape(12.dp),
                onClick = {
                    // Handle SOS
                }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = "SOS",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "SOS EMERGENCY",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        item {
            // Recent Activity Section
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Recent Activity",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = HeadingBlue
                )
                TextButton(
                    onClick = { navController.navigate(Screen.BookingHistory.route) }
                ) {
                    Text(
                        text = "View All",
                        color = ButtonRed
                    )
                }
            }
        }

        items(3) { index ->
            ActivityCard(
                title = if (index == 0) "Ambulance Booking" else if (index == 1) "Medicine Order" else "Hospital Visit",
                date = "2024-01-${15 + index}",
                status = if (index == 0) "Completed" else if (index == 1) "In Progress" else "Scheduled",
                icon = if (index == 0) Icons.Default.AirportShuttle else if (index == 1) Icons.Default.LocalPharmacy else Icons.Default.LocalHospital
            )
        }
    }
}

@Composable
fun DoctorHomeContent(navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            // Welcome Card with Stats
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(4.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Dr. Welcome!",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = HeadingBlue
                            )
                            Text(
                                text = "You have 3 pending appointments",
                                fontSize = 14.sp,
                                color = ButtonRed,
                                fontWeight = FontWeight.Medium
                            )
                        }

                        // Doctor Avatar
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape)
                                .background(HeadingBlue.copy(alpha = 0.1f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.MedicalServices,
                                contentDescription = "Doctor",
                                tint = HeadingBlue,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                }
            }
        }

        item {
            // Today's Stats
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                StatCard(
                    title = "Appointments",
                    value = "8",
                    icon = Icons.Default.Schedule,
                    modifier = Modifier.weight(1f)
                )
                StatCard(
                    title = "Patients",
                    value = "12",
                    icon = Icons.Default.People,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item {
            // Today's Schedule
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(4.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Today's Schedule",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = HeadingBlue
                        )
                        IconButton(
                            onClick = { navController.navigate(Screen.DoctorSchedule.route) }
                        ) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "More",
                                tint = TextBlue
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    listOf(
                        Triple("9:00 AM", "Rahul Sharma", "Pending"),
                        Triple("11:00 AM", "Priya Patel", "Confirmed"),
                        Triple("2:00 PM", "Amit Kumar", "Confirmed")
                    ).forEach { (time, patient, status) ->
                        AppointmentItem(
                            time = time,
                            patient = patient,
                            status = status
                        )
                    }
                }
            }
        }

        item {
            Button(
                onClick = { navController.navigate(Screen.PendingAppointments.route) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ButtonRed
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Visibility,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "View All Pending Appointments",
                    color = Color.White,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}

@Composable
fun AmbulanceHomeContent(navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            // Status Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(4.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Vehicle Status",
                            fontSize = 14.sp,
                            color = TextBlue
                        )
                        Text(
                            text = "Available",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF4CAF50)
                        )
                    }

                    // Status Indicator
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF4CAF50).copy(alpha = 0.1f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "Available",
                            tint = Color(0xFF4CAF50),
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            }
        }

        item {
            // Current Location
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(4.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.MyLocation,
                            contentDescription = null,
                            tint = ButtonRed,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Current Location",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = HeadingBlue
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = cardColors(
                            containerColor = FieldBackground
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = null,
                                tint = TextBlue,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Sector 62, Noida",
                                fontSize = 14.sp,
                                color = TextBlue,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = { navController.navigate(Screen.UpdateLocation.route) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = HeadingBlue
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Update Location", color = Color.White)
                    }
                }
            }
        }

        item {
            // Emergency Requests
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(4.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Active Emergency Requests",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = HeadingBlue
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Emergency Request 1
                    EmergencyRequestItem(
                        location = "Sector 45, Noida",
                        distance = "2.5 km",
                        time = "5 min ago"
                    )

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 8.dp),
                        color = TextBlue.copy(alpha = 0.2f)
                    )

                    // Emergency Request 2
                    EmergencyRequestItem(
                        location = "Sector 62, Noida",
                        distance = "0.5 km",
                        time = "2 min ago",
                        isUrgent = true
                    )
                }
            }
        }

        item {
            // Recent Emergency Visits
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Recent Emergency Visits",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = HeadingBlue
                )
                TextButton(
                    onClick = { navController.navigate(Screen.TripHistory.route) }
                ) {
                    Text(
                        text = "View All",
                        color = ButtonRed
                    )
                }
            }
        }

        items(2) { index ->
            VisitCard(
                visitNumber = index + 1,
                location = "Sector ${60 + index * 5}, Noida",
                status = "Completed",
                icon = Icons.Default.AirportShuttle
            )
        }
    }
}

@Composable
fun DefaultHomeContent(role: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Card(
                modifier = Modifier.size(100.dp),
                shape = CircleShape,
                colors = cardColors(
                    containerColor = ButtonRed.copy(alpha = 0.1f)
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.HealthAndSafety,
                        contentDescription = null,
                        tint = ButtonRed,
                        modifier = Modifier.size(50.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Welcome ${role.uppercase()}",
                color = HeadingBlue,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Jeevan Rakshak - Your Life Saver",
                color = TextBlue,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun QuickActionCard(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.safeClickable(onClick), // Fixed
        colors = cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(12.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(ButtonRed.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = ButtonRed,
                    modifier = Modifier.size(28.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                fontSize = 12.sp,
                color = HeadingBlue,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun StatCard(
    title: String,
    value: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = value,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = HeadingBlue
                )
                Text(
                    text = title,
                    fontSize = 12.sp,
                    color = TextBlue
                )
            }
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(ButtonRed.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = ButtonRed,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
fun ActivityCard(
    title: String,
    date: String,
    status: String,
    icon: ImageVector
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(HeadingBlue.copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = HeadingBlue,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = title,
                        fontWeight = FontWeight.Medium,
                        color = HeadingBlue
                    )
                    Text(
                        text = date,
                        fontSize = 12.sp,
                        color = TextBlue
                    )
                }
            }
            Card(
                colors = cardColors(
                    containerColor = when(status) {
                        "Completed" -> Color(0xFF4CAF50).copy(alpha = 0.1f)
                        "In Progress" -> ButtonRed.copy(alpha = 0.1f)
                        "Scheduled" -> HeadingBlue.copy(alpha = 0.1f)
                        else -> Color.Gray.copy(alpha = 0.1f)
                    }
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = status,
                    fontSize = 11.sp,
                    color = when(status) {
                        "Completed" -> Color(0xFF4CAF50)
                        "In Progress" -> ButtonRed
                        "Scheduled" -> HeadingBlue
                        else -> Color.Gray
                    },
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
    }
}

@Composable
fun AppointmentItem(
    time: String,
    patient: String,
    status: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(
                        when(status) {
                            "Pending" -> ButtonRed
                            "Confirmed" -> Color(0xFF4CAF50)
                            else -> Color.Gray
                        }
                    )
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = patient,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = HeadingBlue
                )
                Text(
                    text = time,
                    fontSize = 12.sp,
                    color = TextBlue
                )
            }
        }
        Card(
            colors = cardColors(
                containerColor = when(status) {
                    "Pending" -> ButtonRed.copy(alpha = 0.1f)
                    "Confirmed" -> Color(0xFF4CAF50).copy(alpha = 0.1f)
                    else -> Color.Gray.copy(alpha = 0.1f)
                }
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = status,
                fontSize = 11.sp,
                color = when(status) {
                    "Pending" -> ButtonRed
                    "Confirmed" -> Color(0xFF4CAF50)
                    else -> Color.Gray
                },
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}

@Composable
fun EmergencyRequestItem(
    location: String,
    distance: String,
    time: String,
    isUrgent: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(if (isUrgent) ButtonRed else HeadingBlue)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = location,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = HeadingBlue
                )
                Text(
                    text = "$distance • $time",
                    fontSize = 12.sp,
                    color = TextBlue
                )
            }
        }
        Button(
            onClick = { /* Accept emergency */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isUrgent) ButtonRed else HeadingBlue
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.height(32.dp)
        ) {
            Text(
                text = "Accept",
                fontSize = 12.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun VisitCard(
    visitNumber: Int,
    location: String,
    status: String,
    icon: ImageVector
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(HeadingBlue.copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = HeadingBlue,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = "Emergency #$visitNumber",
                        fontWeight = FontWeight.Bold,
                        color = HeadingBlue
                    )
                    Text(
                        text = location,
                        fontSize = 12.sp,
                        color = TextBlue
                    )
                }
            }
            Card(
                colors = cardColors(
                    containerColor = Color(0xFF4CAF50).copy(alpha = 0.1f)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = status,
                    fontSize = 11.sp,
                    color = Color(0xFF4CAF50),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
    }
}