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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
fun VehicleStatusScreen(navController: NavController) {
    var fuelLevel by remember { mutableStateOf(75) }
    var vehicleStatus by remember { mutableStateOf("Active") }
    var lastMaintenance by remember { mutableStateOf("10 Jan 2024") }
    var nextMaintenance by remember { mutableStateOf("10 Feb 2024") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Vehicle Status",
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
                    IconButton(onClick = { /* Refresh */ }) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Refresh",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Vehicle Overview Card
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
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .clip(CircleShape)
                            .background(ButtonRed.copy(alpha = 0.1f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.DirectionsCar,
                            contentDescription = null,
                            tint = ButtonRed,
                            modifier = Modifier.size(40.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(20.dp))

                    Column {
                        Text(
                            text = "Ambulance #VH-001",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = HeadingBlue
                        )
                        Text(
                            text = "Maruti Suzuki Eeco",
                            fontSize = 14.sp,
                            color = TextBlue
                        )
                        Text(
                            text = "Basic Life Support",
                            fontSize = 12.sp,
                            color = TextBlue
                        )
                    }
                }
            }

            // Status Indicators
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                StatusCard(
                    title = "Status",
                    value = vehicleStatus,
                    icon = Icons.Default.CheckCircle,
                    color = Color(0xFF4CAF50),
                    modifier = Modifier.weight(1f)
                )
                StatusCard(
                    title = "Fuel Level",
                    value = "$fuelLevel%",
                    icon = Icons.Default.LocalGasStation,
                    color = if (fuelLevel > 50) HeadingBlue else ButtonRed,
                    modifier = Modifier.weight(1f)
                )
            }

            // Fuel Level Progress
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
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Fuel Level",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = HeadingBlue
                        )
                        Text(
                            text = "$fuelLevel%",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (fuelLevel > 50) HeadingBlue else ButtonRed
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    LinearProgressIndicator(
                        progress = { fuelLevel / 100f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        color = if (fuelLevel > 50) HeadingBlue else ButtonRed,
                        trackColor = TextBlue.copy(alpha = 0.2f)
                    )
                }
            }

            // Maintenance Info
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
                    Text(
                        text = "Maintenance Schedule",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = HeadingBlue,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        MaintenanceItem(
                            title = "Last Maintenance",
                            value = lastMaintenance,
                            icon = Icons.Default.History
                        )
                        MaintenanceItem(
                            title = "Next Due",
                            value = nextMaintenance,
                            icon = Icons.Default.Event
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { /* Schedule maintenance */ },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = HeadingBlue
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Schedule Maintenance",
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }
                }
            }

            // Vehicle Details
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
                    Text(
                        text = "Vehicle Details",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = HeadingBlue,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    DetailRow("Registration", "UP16 AB 1234")
                    DetailRow("Model", "Maruti Suzuki Eeco 2023")
                    DetailRow("Type", "Basic Life Support")
                    DetailRow("Insurance", "Valid until 31 Dec 2024")
                    DetailRow("Last Serviced", "10 Jan 2024 - 5,000 km")
                    DetailRow("Total Distance", "12,450 km")
                }
            }

            // Equipment Status
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
                    Text(
                        text = "Equipment Status",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = HeadingBlue,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    EquipmentItem("Oxygen Cylinder", "Full", true)
                    EquipmentItem("Stretcher", "Operational", true)
                    EquipmentItem("First Aid Kit", "Complete", true)
                    EquipmentItem("Defibrillator", "Operational", true)
                    EquipmentItem("ECG Monitor", "Operational", true)
                    EquipmentItem("Ventilator", "Needs Calibration", false)
                }
            }
        }
    }
}

@Composable
fun StatusCard(
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
                    text = title,
                    fontSize = 11.sp,
                    color = TextBlue
                )
                Text(
                    text = value,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = HeadingBlue
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
fun MaintenanceItem(
    title: String,
    value: String,
    icon: ImageVector
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = TextBlue,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = title,
                fontSize = 11.sp,
                color = TextBlue
            )
            Text(
                text = value,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = HeadingBlue
            )
        }
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            color = TextBlue
        )
        Text(
            text = value,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = HeadingBlue
        )
    }
}

@Composable
fun EquipmentItem(name: String, status: String, isGood: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            fontSize = 13.sp,
            color = HeadingBlue
        )
        Card(
            colors = CardDefaults.cardColors(
                containerColor = if (isGood) Color(0xFF4CAF50).copy(alpha = 0.1f) else ButtonRed.copy(alpha = 0.1f)
            ),
            shape = CircleShape
        ) {
            Text(
                text = status,
                fontSize = 10.sp,
                color = if (isGood) Color(0xFF4CAF50) else ButtonRed,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}