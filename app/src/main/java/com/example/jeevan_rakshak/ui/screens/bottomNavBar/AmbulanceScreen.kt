package com.example.jeevan_rakshak.ui.screens.bottomNavBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jeevan_rakshak.ui.components.BottomBar
import com.example.jeevan_rakshak.ui.theme.*
import com.example.jeevan_rakshak.viewmodels.ButtonRed
import com.example.jeevan_rakshak.viewmodels.HeadingBlue
import com.example.jeevan_rakshak.viewmodels.TextBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmbulanceScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Ambulance Services",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF062d66)
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* Navigate to cart */ }) {
                        Icon(
                            imageVector = Icons.Default.AddLocationAlt,
                            contentDescription = "Ambulance",
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
        // Sample ambulance data
        val ambulances = listOf(
            Ambulance("City Hospital Ambulance", "5.2 km", "Available", "4.5", "Basic Life Support"),
            Ambulance("Red Cross Ambulance", "2.1 km", "Available", "4.8", "Advanced Life Support"),
            Ambulance("Life Support Emergency", "3.7 km", "Busy", "4.3", "Patient Transport"),
            Ambulance("Quick Response", "1.5 km", "Available", "4.9", "Emergency"),
            Ambulance("MediCare Ambulance", "4.3 km", "Available", "4.2", "Basic Life Support"),
            Ambulance("City Hospital Ambulance", "5.2 km", "Available", "4.5", "Basic Life Support"),
            Ambulance("Red Cross Ambulance", "2.1 km", "Available", "4.8", "Advanced Life Support"),
            Ambulance("Life Support Emergency", "3.7 km", "Busy", "4.3", "Patient Transport"),
            Ambulance("Quick Response", "1.5 km", "Available", "4.9", "Emergency"),
            Ambulance("MediCare Ambulance", "4.3 km", "Available", "4.2", "Basic Life Support")
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                // Header with info
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = HeadingBlue.copy(alpha = 0.1f)
                    ),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = HeadingBlue,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Emergency? Tap the SOS button below",
                            fontSize = 14.sp,
                            color = HeadingBlue,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            item {
                // SOS Emergency Button
                Button(
                    onClick = { /* Handle SOS */ },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ButtonRed
                    ),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "SOS EMERGENCY",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }

            item {
                Text(
                    text = "Nearby Ambulances",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = HeadingBlue,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            items(ambulances) { ambulance ->
                AmbulanceCard(ambulance = ambulance)
            }
        }
    }
}

@Composable
fun AmbulanceCard(ambulance: Ambulance) {
    Card(
        modifier = Modifier.fillMaxWidth().safeClickable { /* Handle card click */ }, // Fixed
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Ambulance Icon
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(
                            color = ButtonRed.copy(alpha = 0.1f),
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.AirportShuttle,
                        contentDescription = null,
                        tint = ButtonRed,
                        modifier = Modifier.size(28.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = ambulance.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = HeadingBlue
                    )
                    Text(
                        text = "${ambulance.type} • ${ambulance.distance}",
                        fontSize = 12.sp,
                        color = TextBlue
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = Color(0xFFFFC107),
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = " ${ambulance.rating}",
                            fontSize = 12.sp,
                            color = TextBlue
                        )
                    }
                }
            }

            Column(
                horizontalAlignment = Alignment.End
            ) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = if (ambulance.status == "Available")
                            Color(0xFF4CAF50).copy(alpha = 0.1f)
                        else
                            ButtonRed.copy(alpha = 0.1f)
                    ),
                    shape = CircleShape
                ) {
                    Text(
                        text = if (ambulance.status == "Available") "● Available" else "● Busy",
                        fontSize = 11.sp,
                        color = if (ambulance.status == "Available")
                            Color(0xFF4CAF50)
                        else
                            ButtonRed,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { /* Call ambulance */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = HeadingBlue
                    ),
                    shape = CircleShape,
                    modifier = Modifier.height(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = "Call",
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Call",
                        color = Color.White,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

data class Ambulance(
    val name: String,
    val distance: String,
    val status: String,
    val rating: String,
    val type: String
)