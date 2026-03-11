package com.example.jeevan_rakshak.ui.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jeevan_rakshak.navigation.Screen
import com.example.jeevan_rakshak.ui.theme.*
import com.example.jeevan_rakshak.viewmodels.ButtonRed
import com.example.jeevan_rakshak.viewmodels.HeadingBlue
import com.example.jeevan_rakshak.viewmodels.TextBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingHistoryScreen(navController: NavController) {
    // Sample booking data
    val bookings = listOf(
        Booking("AMB001", "City Hospital", "2024-01-15", "10:30 AM", "Completed", "₹450"),
        Booking("AMB002", "Red Cross", "2024-01-14", "2:15 PM", "Completed", "₹380"),
        Booking("AMB003", "Life Support", "2024-01-12", "8:45 PM", "Cancelled", "₹0"),
        Booking("AMB004", "Quick Response", "2024-01-10", "11:20 AM", "Completed", "₹520"),
        Booking("AMB005", "MediCare", "2024-01-08", "3:30 PM", "Completed", "₹410"),
        Booking("AMB006", "City Hospital", "2024-01-05", "9:00 AM", "Completed", "₹480")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Booking History",
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
                // Stats Cards
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    StatCardSmall(
                        title = "Total",
                        value = "24",
                        icon = Icons.Default.ShoppingBag,
                        modifier = Modifier.weight(1f)
                    )
                    StatCardSmall(
                        title = "Completed",
                        value = "18",
                        icon = Icons.Default.CheckCircle,
                        modifier = Modifier.weight(1f)
                    )
                    StatCardSmall(
                        title = "Cancelled",
                        value = "6",
                        icon = Icons.Default.Cancel,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            item {
                // Filter Chips
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    FilterChip(
                        selected = true,
                        onClick = { /* Filter all */ },
                        label = { Text("All", fontSize = 12.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = HeadingBlue,
                            selectedLabelColor = Color.White
                        )
                    )
                    FilterChip(
                        selected = false,
                        onClick = { /* Filter completed */ },
                        label = { Text("Completed", fontSize = 12.sp) }
                    )
                    FilterChip(
                        selected = false,
                        onClick = { /* Filter cancelled */ },
                        label = { Text("Cancelled", fontSize = 12.sp) }
                    )
                }
            }

            items(bookings) { booking ->
                BookingCard(booking = booking)
            }
        }
    }
}

@Composable
fun BookingCard(booking: Booking) {
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
                            imageVector = Icons.Default.AirportShuttle,
                            contentDescription = null,
                            tint = HeadingBlue,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = booking.provider,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = HeadingBlue
                        )
                        Text(
                            text = "ID: ${booking.bookingId}",
                            fontSize = 12.sp,
                            color = TextBlue
                        )
                    }
                }
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = when(booking.status) {
                            "Completed" -> Color(0xFF4CAF50).copy(alpha = 0.1f)
                            "Cancelled" -> ButtonRed.copy(alpha = 0.1f)
                            else -> TextBlue.copy(alpha = 0.1f)
                        }
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = booking.status,
                        fontSize = 11.sp,
                        color = when(booking.status) {
                            "Completed" -> Color(0xFF4CAF50)
                            "Cancelled" -> ButtonRed
                            else -> TextBlue
                        },
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
                InfoRow(icon = Icons.Default.CalendarToday, text = booking.date)
                InfoRow(icon = Icons.Default.AccessTime, text = booking.time)
                InfoRow(icon = Icons.Default.CurrencyRupee, text = booking.amount)
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
fun StatCardSmall(
    title: String,
    value: String,
    icon: ImageVector,
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
                tint = ButtonRed,
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = value,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = HeadingBlue
            )
            Text(
                text = title,
                fontSize = 11.sp,
                color = TextBlue
            )
        }
    }
}

@Composable
fun InfoRow(icon: ImageVector, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = TextBlue,
            modifier = Modifier.size(14.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            fontSize = 11.sp,
            color = TextBlue
        )
    }
}

data class Booking(
    val bookingId: String,
    val provider: String,
    val date: String,
    val time: String,
    val status: String,
    val amount: String
)