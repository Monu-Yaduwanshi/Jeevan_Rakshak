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
fun DoctorScheduleScreen(navController: NavController) {
    // Sample schedule data
    val scheduleItems = listOf(
        ScheduleItem("09:00 AM", "10:00 AM", "Patient: Rahul Sharma", "General Checkup", "Confirmed"),
        ScheduleItem("10:30 AM", "11:30 AM", "Patient: Priya Patel", "Cardiology", "Confirmed"),
        ScheduleItem("12:00 PM", "01:00 PM", "Patient: Amit Kumar", "Follow-up", "Pending"),
        ScheduleItem("02:00 PM", "03:00 PM", "Patient: Neha Singh", "Consultation", "Confirmed"),
        ScheduleItem("03:30 PM", "04:30 PM", "Patient: Rajesh Gupta", "Diabetes Check", "Pending"),
        ScheduleItem("05:00 PM", "06:00 PM", "Patient: Sunita Sharma", "General Checkup", "Confirmed")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Today's Schedule",
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
                    IconButton(onClick = { /* Calendar view */ }) {
                        Icon(
                            imageVector = Icons.Default.CalendarToday,
                            contentDescription = "Calendar",
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
                // Date Selector
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
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { /* Previous day */ }) {
                            Icon(
                                imageVector = Icons.Default.ChevronLeft,
                                contentDescription = "Previous",
                                tint = HeadingBlue
                            )
                        }

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Monday, 15 Jan 2024",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = HeadingBlue
                            )
                            Text(
                                text = "6 appointments today",
                                fontSize = 12.sp,
                                color = TextBlue
                            )
                        }

                        IconButton(onClick = { /* Next day */ }) {
                            Icon(
                                imageVector = Icons.Default.ChevronRight,
                                contentDescription = "Next",
                                tint = HeadingBlue
                            )
                        }
                    }
                }
            }

            item {
                // Stats Cards
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ScheduleStatCard(
                        title = "Total",
                        value = "6",
                        icon = Icons.Default.Event,
                        color = HeadingBlue,
                        modifier = Modifier.weight(1f)
                    )
                    ScheduleStatCard(
                        title = "Completed",
                        value = "4",
                        icon = Icons.Default.CheckCircle,
                        color = Color(0xFF4CAF50),
                        modifier = Modifier.weight(1f)
                    )
                    ScheduleStatCard(
                        title = "Pending",
                        value = "2",
                        icon = Icons.Default.Schedule,
                        color = ButtonRed,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            item {
                Text(
                    text = "Today's Appointments",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = HeadingBlue,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            items(scheduleItems) { item ->
                ScheduleCard(item = item)
            }
        }
    }
}

@Composable
fun ScheduleStatCard(
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
                    fontSize = 11.sp,
                    color = TextBlue
                )
            }
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun ScheduleCard(item: ScheduleItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Time Column
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(70.dp)
            ) {
                Text(
                    text = item.startTime,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = HeadingBlue
                )
                Text(
                    text = "-",
                    fontSize = 12.sp,
                    color = TextBlue
                )
                Text(
                    text = item.endTime,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextBlue
                )
            }

            VerticalDivider(
                modifier = Modifier
                    .height(60.dp)
                    .padding(horizontal = 8.dp),
                color = TextBlue.copy(alpha = 0.3f),
                thickness = 1.dp
            )

            // Details Column
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.patient,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = HeadingBlue
                )
                Text(
                    text = item.type,
                    fontSize = 13.sp,
                    color = TextBlue
                )
                Row(
                    modifier = Modifier.padding(top = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = if (item.status == "Confirmed")
                                Color(0xFF4CAF50).copy(alpha = 0.1f)
                            else
                                ButtonRed.copy(alpha = 0.1f)
                        ),
                        shape = CircleShape
                    ) {
                        Text(
                            text = item.status,
                            fontSize = 10.sp,
                            color = if (item.status == "Confirmed") Color(0xFF4CAF50) else ButtonRed,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }

            // Action Button
            IconButton(
                onClick = { /* View details */ }
            ) {
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = "View",
                    tint = ButtonRed
                )
            }
        }
    }
}

data class ScheduleItem(
    val startTime: String,
    val endTime: String,
    val patient: String,
    val type: String,
    val status: String
)