package com.example.jeevan_rakshak.ui.screens.bottomNavBar

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import com.example.jeevan_rakshak.viewmodels.ButtonRed
import com.example.jeevan_rakshak.viewmodels.HeadingBlue
import com.example.jeevan_rakshak.viewmodels.TextBlue
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HospitalScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Nearby Hospitals",
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
                            imageVector = Icons.Default.AddLocation,
                            contentDescription = "Location",
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
        // Sample hospital data
        val hospitals = listOf(
            Hospital("City General Hospital", "2.5 km", "Open 24/7", "4.5", "Multi-Specialty"),
            Hospital("Apollo Medical Center", "1.8 km", "Open 24/7", "4.8", "Cardiology"),
            Hospital("Sunrise Healthcare", "3.2 km", "Closes 10 PM", "4.3", "Pediatrics"),
            Hospital("Medanta Hospital", "4.1 km", "Open 24/7", "4.7", "Multi-Specialty"),
            Hospital("Children's Medical", "5.0 km", "Open 24/7", "4.4", "Children"),
            Hospital("City General Hospital", "2.5 km", "Open 24/7", "4.5", "Multi-Specialty"),
            Hospital("Apollo Medical Center", "1.8 km", "Open 24/7", "4.8", "Cardiology"),
            Hospital("Sunrise Healthcare", "3.2 km", "Closes 10 PM", "4.3", "Pediatrics"),
            Hospital("Medanta Hospital", "4.1 km", "Open 24/7", "4.7", "Multi-Specialty"),
            Hospital("Children's Medical", "5.0 km", "Open 24/7", "4.4", "Children")
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                // Search Bar
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("Search hospitals...", color = TextBlue) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = TextBlue
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedBorderColor = HeadingBlue,
                        unfocusedBorderColor = TextBlue
                    ),
                    shape = MaterialTheme.shapes.medium
                )
            }

            item {
                Text(
                    text = "Hospitals Near You",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = HeadingBlue,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            items(hospitals) { hospital ->
                HospitalCard(hospital = hospital)
            }
        }
    }
}

@Composable
fun HospitalCard(hospital: Hospital) {
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
                // Hospital Icon
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(
                            color = HeadingBlue.copy(alpha = 0.1f),
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.LocalHospital,
                        contentDescription = null,
                        tint = HeadingBlue,
                        modifier = Modifier.size(28.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = hospital.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = HeadingBlue
                    )
                    Text(
                        text = "${hospital.specialty} • ${hospital.distance}",
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
                            text = " ${hospital.rating}",
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
                        containerColor = if (hospital.timing == "Open 24/7")
                            Color(0xFF4CAF50).copy(alpha = 0.1f)
                        else
                            ButtonRed.copy(alpha = 0.1f)
                    ),
                    shape = CircleShape
                ) {
                    Text(
                        text = hospital.timing,
                        fontSize = 11.sp,
                        color = if (hospital.timing == "Open 24/7")
                            Color(0xFF4CAF50)
                        else
                            ButtonRed,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { /* Navigate to hospital details */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = HeadingBlue
                    ),
                    shape = CircleShape,
                    modifier = Modifier.height(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Navigation,
                        contentDescription = "Navigate",
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Directions",
                        color = Color.White,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

data class Hospital(
    val name: String,
    val distance: String,
    val timing: String,
    val rating: String,
    val specialty: String
)