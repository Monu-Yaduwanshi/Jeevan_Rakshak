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
import com.example.jeevan_rakshak.ui.screens.bottomNavBar.AssistChip
import com.example.jeevan_rakshak.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicalRecordsScreen(navController: NavController) {
    // Sample medical records
    val records = listOf(
        MedicalRecord("Blood Test Report", "Pathology", "15 Jan 2024", "Dr. Sharma", "4.5 MB"),
        MedicalRecord("Chest X-Ray", "Radiology", "10 Jan 2024", "Dr. Gupta", "8.2 MB"),
        MedicalRecord("Prescription - Antibiotics", "Prescription", "05 Jan 2024", "Dr. Verma", "1.2 MB"),
        MedicalRecord("ECG Report", "Cardiology", "28 Dec 2023", "Dr. Singh", "3.7 MB"),
        MedicalRecord("Vaccination Certificate", "Immunization", "20 Dec 2023", "City Hospital", "2.1 MB"),
        MedicalRecord("Discharge Summary", "Hospital", "15 Dec 2023", "Apollo Hospital", "5.3 MB")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Medical Records",
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
                    IconButton(onClick = { /* Upload */ }) {
                        Icon(
                            imageVector = Icons.Default.Upload,
                            contentDescription = "Upload",
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
                // Categories
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    CategoryChip("All", Icons.Default.Folder, true)
                    CategoryChip("Reports", Icons.Default.Description, false)
                    CategoryChip("Prescriptions", Icons.Default.MedicalServices, false)
                    CategoryChip("X-Rays", Icons.Default.Image, false)
                }
            }

            items(records) { record ->
                MedicalRecordCard(record = record)
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = HeadingBlue.copy(alpha = 0.1f)
                    ),
                    shape = RoundedCornerShape(12.dp)
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
                            text = "Your medical records are securely stored",
                            fontSize = 13.sp,
                            color = HeadingBlue
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryChip(name: String, icon: ImageVector, selected: Boolean) {
    AssistChip(
        onClick = { /* Filter by category */ },
        label = {
            Text(
                text = name,
                fontSize = 12.sp,
                color = if (selected) Color.White else HeadingBlue
            )
        },
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (selected) Color.White else ButtonRed,
                modifier = Modifier.size(16.dp)
            )
        },
        colors = AssistChipDefaults.assistChipColors(
            containerColor = if (selected) ButtonRed else Color.White
        ),
        border = AssistChipDefaults.assistChipBorder(
            borderColor = if (selected) ButtonRed else HeadingBlue.copy(alpha = 0.3f)
        )
    )
}

@Composable
fun MedicalRecordCard(record: MedicalRecord) {
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
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(
                        when(record.category) {
                            "Pathology" -> ButtonRed.copy(alpha = 0.1f)
                            "Radiology" -> HeadingBlue.copy(alpha = 0.1f)
                            "Prescription" -> Color(0xFFFF9800).copy(alpha = 0.1f)
                            else -> TextBlue.copy(alpha = 0.1f)
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = when(record.category) {
                        "Pathology" -> Icons.Default.Science
                        "Radiology" -> Icons.Default.Image
                        "Prescription" -> Icons.Default.MedicalServices
                        else -> Icons.Default.Description
                    },
                    contentDescription = null,
                    tint = when(record.category) {
                        "Pathology" -> ButtonRed
                        "Radiology" -> HeadingBlue
                        "Prescription" -> Color(0xFFFF9800)
                        else -> TextBlue
                    },
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = record.title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = HeadingBlue
                )
                Text(
                    text = "${record.doctor} • ${record.date}",
                    fontSize = 11.sp,
                    color = TextBlue
                )
                Text(
                    text = "${record.category} • ${record.fileSize}",
                    fontSize = 11.sp,
                    color = TextBlue
                )
            }

            Column(
                horizontalAlignment = Alignment.End
            ) {
                IconButton(
                    onClick = { /* Download */ }
                ) {
                    Icon(
                        imageVector = Icons.Default.Download,
                        contentDescription = "Download",
                        tint = ButtonRed,
                        modifier = Modifier.size(20.dp)
                    )
                }
                IconButton(
                    onClick = { /* Share */ }
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share",
                        tint = TextBlue,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

data class MedicalRecord(
    val title: String,
    val category: String,
    val date: String,
    val doctor: String,
    val fileSize: String
)