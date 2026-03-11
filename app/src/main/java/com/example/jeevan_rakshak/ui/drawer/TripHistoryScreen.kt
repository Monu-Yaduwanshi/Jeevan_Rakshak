package com.example.jeevan_rakshak.ui.drawer

import com.example.jeevan_rakshak.viewmodels.ButtonRed
import com.example.jeevan_rakshak.viewmodels.FieldBackground
import com.example.jeevan_rakshak.viewmodels.HeadingBlue
import com.example.jeevan_rakshak.viewmodels.TextBlue
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.vector.ImageVector

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jeevan_rakshak.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripHistoryScreen(navController: NavController) {
    // Sample trip data
    val trips = listOf(
        Trip("TRP001", "Sector 62 to City Hospital", "15 Jan 2024", "10:30 AM", "12 km", "₹450", "Completed"),
        Trip("TRP002", "Sector 45 to Apollo Hospital", "14 Jan 2024", "2:15 PM", "8 km", "₹380", "Completed"),
        Trip("TRP003", "Sector 125 to Red Cross", "13 Jan 2024", "8:45 PM", "15 km", "₹520", "Completed"),
        Trip("TRP004", "Sector 16 to Medanta", "12 Jan 2024", "11:20 AM", "6 km", "₹350", "Completed"),
        Trip("TRP005", "Sector 34 to Fortis", "11 Jan 2024", "3:30 PM", "10 km", "₹410", "Cancelled"),
        Trip("TRP006", "Sector 62 to Max Hospital", "10 Jan 2024", "9:00 AM", "14 km", "₹480", "Completed")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Trip History",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = ButtonRed
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                // Stats Summary
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TripStatCard(
                        title = "Total Trips",
                        value = "156",
                        icon = Icons.Default.DirectionsCar,
                        color = HeadingBlue,
                        modifier = Modifier.weight(1f)
                    )
                    TripStatCard(
                        title = "Distance",
                        value = "1,245 km",
                        icon = Icons.Default.AltRoute,
                        color = ButtonRed,
                        modifier = Modifier.weight(1f)
                    )
                    TripStatCard(
                        title = "Earnings",
                        value = "₹45,680",
                        icon = Icons.Default.CurrencyRupee,
                        color = Color(0xFF4CAF50),
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            item {
                // Filter Row
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    FilterChip(
                        selected = true,
                        onClick = { /* All trips */ },
                        label = { Text("All", fontSize = 12.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = HeadingBlue,
                            selectedLabelColor = Color.White
                        )
                    )
                    FilterChip(
                        selected = false,
                        onClick = { /* Completed */ },
                        label = { Text("Completed", fontSize = 12.sp) }
                    )
                    FilterChip(
                        selected = false,
                        onClick = { /* Cancelled */ },
                        label = { Text("Cancelled", fontSize = 12.sp) }
                    )
                }
            }

            items(trips) { trip ->
                TripCard(trip = trip)
            }
        }
    }
}

@Composable
fun TripStatCard(
    title: String,
    value: String,
    icon: ImageVector,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = value,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = HeadingBlue
            )
            Text(
                text = title,
                fontSize = 10.sp,
                color = TextBlue
            )
        }
    }
}

@Composable
fun TripCard(trip: Trip) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(12.dp)
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
                            imageVector = Icons.Default.DirectionsCar,
                            contentDescription = null,
                            tint = HeadingBlue,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = trip.route,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = HeadingBlue
                        )
                        Text(
                            text = "ID: ${trip.tripId}",
                            fontSize = 11.sp,
                            color = TextBlue
                        )
                    }
                }
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = if (trip.status == "Completed")
                            Color(0xFF4CAF50).copy(alpha = 0.1f)
                        else
                            ButtonRed.copy(alpha = 0.1f)
                    ),
                    shape = CircleShape
                ) {
                    Text(
                        text = trip.status,
                        fontSize = 10.sp,
                        color = if (trip.status == "Completed") Color(0xFF4CAF50) else ButtonRed,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TripInfoItem(icon = Icons.Default.CalendarToday, text = trip.date)
                TripInfoItem(icon = Icons.Default.AccessTime, text = trip.time)
                TripInfoItem(icon = Icons.Default.AltRoute, text = trip.distance)
                TripInfoItem(icon = Icons.Default.CurrencyRupee, text = trip.amount)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { /* View details */ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = HeadingBlue
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "View Details",
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
fun TripInfoItem(icon: ImageVector, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = TextBlue,
            modifier = Modifier.size(12.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            fontSize = 10.sp,
            color = TextBlue
        )
    }
}

data class Trip(
    val tripId: String,
    val route: String,
    val date: String,
    val time: String,
    val distance: String,
    val amount: String,
    val status: String
)
