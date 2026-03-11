package com.example.jeevan_rakshak.ui.drawer


import androidx.compose.foundation.BorderStroke
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


import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import com.example.jeevan_rakshak.viewmodels.ButtonRed
import com.example.jeevan_rakshak.viewmodels.HeadingBlue
import com.example.jeevan_rakshak.viewmodels.TextBlue
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressScreen(navController: NavController) {
    // Sample addresses
    val addresses = remember {
        mutableStateListOf(
            Address("Home", "John Doe", "+91 9876543210", "123, Sector 62, Noida, UP - 201309", true),
            Address("Office", "John Doe", "+91 9876543210", "456, Sector 16, Noida, UP - 201301", false),
            Address("Work", "John Doe", "+91 9876543210", "789, Sector 125, Noida, UP - 201303", false)
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "My Addresses",
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
                    IconButton(onClick = { /* Add new address */ }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add",
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
            items(addresses) { address ->
                AddressCard(
                    address = address,
                    onSetDefault = {
                        addresses.forEach { it.isDefault = false }
                        address.isDefault = true
                    },
                    onEdit = { /* Edit address */ },
                    onDelete = { addresses.remove(address) }
                )
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { /* Add new address */ },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = HeadingBlue
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Add New Address",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
fun AddressCard(
    address: Address,
    onSetDefault: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (address.isDefault) HeadingBlue.copy(alpha = 0.05f) else Color.White
        ),
        elevation = CardDefaults.cardElevation(if (address.isDefault) 4.dp else 2.dp),
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
                            .background(ButtonRed.copy(alpha = 0.1f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = when(address.type) {
                                "Home" -> Icons.Default.Home
                                "Office" -> Icons.Default.Business
                                else -> Icons.Default.LocationOn
                            },
                            contentDescription = null,
                            tint = ButtonRed,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = address.type,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = HeadingBlue
                        )
                        Text(
                            text = "${address.name} • ${address.phone}",
                            fontSize = 11.sp,
                            color = TextBlue
                        )
                    }
                }

                if (address.isDefault) {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF4CAF50).copy(alpha = 0.1f)
                        ),
                        shape = CircleShape
                    ) {
                        Text(
                            text = "Default",
                            fontSize = 10.sp,
                            color = Color(0xFF4CAF50),
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = address.fullAddress,
                fontSize = 13.sp,
                color = TextBlue,
                modifier = Modifier.padding(start = 52.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 52.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (!address.isDefault) {
                    OutlinedButton(
                        onClick = onSetDefault,
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = HeadingBlue
                        ),
                        border = BorderStroke(1.dp, HeadingBlue), // Fixed
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Set Default",
                            fontSize = 11.sp
                        )
                    }
                    }
                }

            OutlinedButton(
                onClick = onEdit,
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = TextBlue
                ),
                border = BorderStroke(1.dp, TextBlue), // Fixed
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Edit",
                    fontSize = 11.sp
                )
            }
                }

        OutlinedButton(
            onClick = onDelete,
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = ButtonRed
            ),
            border = BorderStroke(1.dp, ButtonRed), // Fixed
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = null,
                modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Delete",
                fontSize = 11.sp
            )
        }
                }
            }



data class Address(
    val type: String,
    val name: String,
    val phone: String,
    val fullAddress: String,
    var isDefault: Boolean
)