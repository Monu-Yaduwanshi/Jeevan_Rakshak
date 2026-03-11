package com.example.jeevan_rakshak.ui.drawer
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jeevan_rakshak.ui.theme.*

import androidx.compose.material.icons.filled.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.jeevan_rakshak.viewmodels.ButtonRed
import com.example.jeevan_rakshak.viewmodels.HeadingBlue
import com.example.jeevan_rakshak.viewmodels.TextBlue


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmergencyVisitScreen(navController: NavController) {
    // Sample emergency requests
    val emergencyRequests = listOf(
        EmergencyRequest("REQ001", "Sector 62, Noida", "2.5 km", "Critical", "Patient unconscious", "2 min ago"),
        EmergencyRequest("REQ002", "Sector 45, Noida", "3.8 km", "Medium", "Chest pain", "5 min ago"),
        EmergencyRequest("REQ003", "Sector 125, Noida", "1.2 km", "Critical", "Accident victim", "1 min ago"),
        EmergencyRequest("REQ004", "Sector 16, Noida", "4.1 km", "Low", "Breathing issue", "10 min ago"),
        EmergencyRequest("REQ005", "Sector 34, Noida", "2.9 km", "Medium", "High fever", "15 min ago")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Emergency Visits",
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
                // Status Summary Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(4.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        StatusIndicator(
                            title = "Active",
                            value = "3",
                            color = ButtonRed
                        )
                        VerticalDivider(
                            modifier = Modifier.height(40.dp),
                            color = TextBlue.copy(alpha = 0.3f),
                            thickness = 1.dp
                        )
                        StatusIndicator(
                            title = "Completed",
                            value = "12",
                            color = Color(0xFF4CAF50)
                        )
                        VerticalDivider(
                            modifier = Modifier.height(40.dp),
                            color = TextBlue.copy(alpha = 0.3f),
                            thickness = 1.dp
                        )
                        StatusIndicator(
                            title = "Total",
                            value = "15",
                            color = HeadingBlue
                        )
                    }
                }
            }

            item {
                Text(
                    text = "Active Emergency Requests",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = HeadingBlue,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            items(emergencyRequests) { request ->
                EmergencyRequestCard(request = request)
            }
        }
    }
}

@Composable
fun StatusIndicator(
    title: String,
    value: String,
    color: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = color
        )
        Text(
            text = title,
            fontSize = 12.sp,
            color = TextBlue
        )
    }
}

@Composable
fun EmergencyRequestCard(request: EmergencyRequest) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (request.priority == "Critical") ButtonRed.copy(alpha = 0.05f) else Color.White
        ),
        elevation = CardDefaults.cardElevation(if (request.priority == "Critical") 4.dp else 2.dp),
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
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(
                                when(request.priority) {
                                    "Critical" -> ButtonRed.copy(alpha = 0.1f)
                                    "Medium" -> Color(0xFFFF9800).copy(alpha = 0.1f)
                                    else -> HeadingBlue.copy(alpha = 0.1f)
                                }
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Emergency,
                            contentDescription = null,
                            tint = when(request.priority) {
                                "Critical" -> ButtonRed
                                "Medium" -> Color(0xFFFF9800)
                                else -> HeadingBlue
                            },
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            text = "Request #${request.requestId}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = HeadingBlue
                        )
                        Text(
                            text = request.location,
                            fontSize = 13.sp,
                            color = TextBlue
                        )
                    }
                }

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = when(request.priority) {
                            "Critical" -> ButtonRed.copy(alpha = 0.1f)
                            "Medium" -> Color(0xFFFF9800).copy(alpha = 0.1f)
                            else -> HeadingBlue.copy(alpha = 0.1f)
                        }
                    ),
                    shape = CircleShape
                ) {
                    Text(
                        text = request.priority,
                        fontSize = 11.sp,
                        color = when(request.priority) {
                            "Critical" -> ButtonRed
                            "Medium" -> Color(0xFFFF9800)
                            else -> HeadingBlue
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
                InfoRowSmall(icon = Icons.Default.LocationOn, text = request.distance)
                InfoRowSmall(icon = Icons.Default.AccessTime, text = request.time)
                InfoRowSmall(icon = Icons.Default.Info, text = request.description)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { /* Accept request */ },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ButtonRed
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Accept",
                        color = Color.White,
                        fontSize = 12.sp
                    )
                }

                OutlinedButton(
                    onClick = { /* View details */ },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = HeadingBlue
                    ),
                    border = BorderStroke(1.dp, HeadingBlue),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Details",
                        color = HeadingBlue,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

@Composable
fun InfoRowSmall(icon: ImageVector, text: String) {
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

data class EmergencyRequest(
    val requestId: String,
    val location: String,
    val distance: String,
    val priority: String,
    val description: String,
    val time: String
)