package com.example.jeevan_rakshak.ui.drawer

import com.example.jeevan_rakshak.viewmodels.ButtonRed
import com.example.jeevan_rakshak.viewmodels.FieldBackground
import com.example.jeevan_rakshak.viewmodels.HeadingBlue
import com.example.jeevan_rakshak.viewmodels.TextBlue
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

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
fun ConsultationHistoryScreen(navController: NavController) {
    // Sample consultation data
    val consultations = listOf(
        Consultation("CON001", "Rahul Sharma", "15 Jan 2024", "10:30 AM", "General Checkup", "Completed", "Prescribed"),
        Consultation("CON002", "Priya Patel", "14 Jan 2024", "2:15 PM", "Cardiology", "Completed", "Follow-up needed"),
        Consultation("CON003", "Amit Kumar", "13 Jan 2024", "11:00 AM", "Asthma", "Completed", "Prescribed"),
        Consultation("CON004", "Neha Singh", "12 Jan 2024", "3:30 PM", "Thyroid", "Completed", "Lab tests"),
        Consultation("CON005", "Rajesh Gupta", "11 Jan 2024", "9:45 AM", "Diabetes", "Completed", "Prescribed"),
        Consultation("CON006", "Sunita Sharma", "10 Jan 2024", "4:00 PM", "Arthritis", "Cancelled", "")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Consultation History",
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
                    ConsultationStatCard(
                        title = "Total",
                        value = "156",
                        icon = Icons.Default.MedicalServices,
                        color = HeadingBlue,
                        modifier = Modifier.weight(1f)
                    )
                    ConsultationStatCard(
                        title = "This Month",
                        value = "24",
                        icon = Icons.Default.CalendarToday,
                        color = ButtonRed,
                        modifier = Modifier.weight(1f)
                    )
                    ConsultationStatCard(
                        title = "Avg. Duration",
                        value = "25 min",
                        icon = Icons.Default.AccessTime,
                        color = Color(0xFF4CAF50),
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            item {
                Text(
                    text = "Recent Consultations",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = HeadingBlue,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            items(consultations) { consultation ->
                ConsultationCard(consultation = consultation)
            }
        }
    }
}

@Composable
fun ConsultationStatCard(
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
                fontSize = 18.sp,
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
fun ConsultationCard(consultation: Consultation) {
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
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = HeadingBlue,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = consultation.patientName,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = HeadingBlue
                        )
                        Text(
                            text = "ID: ${consultation.id}",
                            fontSize = 11.sp,
                            color = TextBlue
                        )
                    }
                }
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = if (consultation.status == "Completed")
                            Color(0xFF4CAF50).copy(alpha = 0.1f)
                        else
                            ButtonRed.copy(alpha = 0.1f)
                    ),
                    shape = CircleShape
                ) {
                    Text(
                        text = consultation.status,
                        fontSize = 10.sp,
                        color = if (consultation.status == "Completed") Color(0xFF4CAF50) else ButtonRed,
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
                InfoRowSmall(icon = Icons.Default.CalendarToday, text = consultation.date)
                InfoRowSmall(icon = Icons.Default.AccessTime, text = consultation.time)
                InfoRowSmall(icon = Icons.Default.MedicalServices, text = consultation.type)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Outcome: ${consultation.outcome}",
                    fontSize = 12.sp,
                    color = TextBlue,
                    modifier = Modifier.weight(1f)
                )

                Button(
                    onClick = { /* View details */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = HeadingBlue
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Details",
                        color = Color.White,
                        fontSize = 11.sp
                    )
                }
            }
        }
    }
}

data class Consultation(
    val id: String,
    val patientName: String,
    val date: String,
    val time: String,
    val type: String,
    val status: String,
    val outcome: String
)