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
fun MyPatientsScreen(navController: NavController) {
    // Sample patients data
    val patients = listOf(
        Patient("PT001", "Rahul Sharma", "45", "Male", "Diabetes", "Last: 15 Jan", "High"),
        Patient("PT002", "Priya Patel", "32", "Female", "Hypertension", "Last: 14 Jan", "Medium"),
        Patient("PT003", "Amit Kumar", "28", "Male", "Asthma", "Last: 12 Jan", "Low"),
        Patient("PT004", "Neha Singh", "35", "Female", "Thyroid", "Last: 10 Jan", "Medium"),
        Patient("PT005", "Rajesh Gupta", "52", "Male", "Heart Disease", "Last: 08 Jan", "Critical"),
        Patient("PT006", "Sunita Sharma", "41", "Female", "Arthritis", "Last: 05 Jan", "Low")
    )

    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "My Patients",
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
                // Search Bar
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Search patients...", color = TextBlue) },
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
                    shape = RoundedCornerShape(12.dp)
                )
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
                        onClick = { /* All patients */ },
                        label = { Text("All", fontSize = 12.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = HeadingBlue,
                            selectedLabelColor = Color.White
                        )
                    )
                    FilterChip(
                        selected = false,
                        onClick = { /* Critical */ },
                        label = { Text("Critical", fontSize = 12.sp) }
                    )
                    FilterChip(
                        selected = false,
                        onClick = { /* Recent */ },
                        label = { Text("Recent", fontSize = 12.sp) }
                    )
                }
            }

            items(patients) { patient ->
                PatientCard(patient = patient)
            }
        }
    }
}

@Composable
fun PatientCard(patient: Patient) {
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
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Patient Avatar
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(HeadingBlue.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = patient.name.take(1),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = HeadingBlue
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Patient Details
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = patient.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = HeadingBlue
                )
                Text(
                    text = "${patient.age} years • ${patient.gender} • ID: ${patient.id}",
                    fontSize = 11.sp,
                    color = TextBlue
                )
                Row(
                    modifier = Modifier.padding(top = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = when(patient.risk) {
                                "Critical" -> ButtonRed.copy(alpha = 0.1f)
                                "High" -> Color(0xFFFF9800).copy(alpha = 0.1f)
                                "Medium" -> HeadingBlue.copy(alpha = 0.1f)
                                else -> TextBlue.copy(alpha = 0.1f)
                            }
                        ),
                        shape = CircleShape
                    ) {
                        Text(
                            text = patient.condition,
                            fontSize = 10.sp,
                            color = when(patient.risk) {
                                "Critical" -> ButtonRed
                                "High" -> Color(0xFFFF9800)
                                "Medium" -> HeadingBlue
                                else -> TextBlue
                            },
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = patient.lastVisit,
                        fontSize = 10.sp,
                        color = TextBlue
                    )
                }
            }

            // Risk Indicator
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(
                        when(patient.risk) {
                            "Critical" -> ButtonRed
                            "High" -> Color(0xFFFF9800)
                            "Medium" -> HeadingBlue
                            else -> TextBlue
                        }
                    )
            )
        }
    }
}

data class Patient(
    val id: String,
    val name: String,
    val age: String,
    val gender: String,
    val condition: String,
    val lastVisit: String,
    val risk: String
)