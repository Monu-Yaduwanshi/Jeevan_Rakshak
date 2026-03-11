package com.example.jeevan_rakshak.ui.drawer

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jeevan_rakshak.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsScreen(navController: NavController) {
    // Sample notifications
    val notifications = listOf(
        Notification(
            "Order Confirmed",
            "Your order #ORD001 has been confirmed and will be delivered soon.",
            "2 min ago",
            Icons.Default.CheckCircle,
            false
        ),
        Notification(
            "Ambulance Arriving",
            "Your ambulance is 5 minutes away. Please be ready.",
            "15 min ago",
            Icons.Default.AirportShuttle,
            false
        ),
        Notification(
            "Prescription Reminder",
            "Time to take your medicine: Paracetamol 500mg",
            "1 hour ago",
            Icons.Default.MedicalServices,
            false
        ),
        Notification(
            "Appointment Reminder",
            "You have an appointment with Dr. Sharma tomorrow at 10 AM",
            "3 hours ago",
            Icons.Default.CalendarToday,
            true
        ),
        Notification(
            "Special Offer",
            "Get 20% off on medicines. Use code HEALTH20",
            "5 hours ago",
            Icons.Default.LocalOffer,
            true
        ),
        Notification(
            "Blood Donation Camp",
            "Blood donation camp at City Hospital tomorrow",
            "1 day ago",
            Icons.Default.Bloodtype,
            true
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Notifications",
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
                },
                actions = {
                    IconButton(onClick = { /* Mark all as read */ }) {
                        Icon(
                            imageVector = Icons.Default.DoneAll,
                            contentDescription = "Mark all read",
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
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                // Filter Tabs
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                ) {
                    FilterChip(
                        selected = true,
                        onClick = { /* Show all */ },
                        label = { Text("All", fontSize = 12.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = HeadingBlue,
                            selectedLabelColor = Color.White
                        )
                    )
                    FilterChip(
                        selected = false,
                        onClick = { /* Show unread */ },
                        label = { Text("Unread", fontSize = 12.sp) }
                    )
                    FilterChip(
                        selected = false,
                        onClick = { /* Show read */ },
                        label = { Text("Read", fontSize = 12.sp) }
                    )
                }
            }

            items(notifications) { notification ->
                NotificationCard(notification = notification)
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = TextBlue,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "No more notifications",
                            color = TextBlue,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun NotificationCard(notification: Notification) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (notification.isRead) Color.White else HeadingBlue.copy(alpha = 0.05f)
        ),
        elevation = CardDefaults.cardElevation(if (notification.isRead) 1.dp else 2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Icon
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(
                        if (!notification.isRead) ButtonRed.copy(alpha = 0.1f)
                        else TextBlue.copy(alpha = 0.1f)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = notification.icon,
                    contentDescription = null,
                    tint = if (!notification.isRead) ButtonRed else TextBlue,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Content
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = notification.title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = HeadingBlue
                    )

                    if (!notification.isRead) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(ButtonRed)
                        )
                    }
                }

                Text(
                    text = notification.message,
                    fontSize = 13.sp,
                    color = TextBlue,
                    modifier = Modifier.padding(top = 4.dp)
                )

                Text(
                    text = notification.time,
                    fontSize = 11.sp,
                    color = TextBlue.copy(alpha = 0.7f),
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

data class Notification(
    val title: String,
    val message: String,
    val time: String,
    val icon: ImageVector,
    val isRead: Boolean
)