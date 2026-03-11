package com.example.jeevan_rakshak.ui.drawer

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import com.example.jeevan_rakshak.viewmodels.ButtonRed
import com.example.jeevan_rakshak.viewmodels.HeadingBlue
import com.example.jeevan_rakshak.viewmodels.TextBlue

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
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
fun MyOrdersScreen(navController: NavController) {
    // Sample orders data
    val orders = listOf(
        Order("ORD001", "Paracetamol 500mg", "₹45", "2", "₹90", "Delivered", "2024-01-15"),
        Order("ORD002", "Vitamin C Tablets", "₹180", "1", "₹180", "Shipped", "2024-01-14"),
        Order("ORD003", "Amoxicillin 250mg", "₹120", "3", "₹360", "Processing", "2024-01-12"),
        Order("ORD004", "Cetrizine 10mg", "₹35", "5", "₹175", "Delivered", "2024-01-10"),
        Order("ORD005", "Azithromycin 500mg", "₹150", "2", "₹300", "Delivered", "2024-01-08"),
        Order("ORD006", "Paracetamol 500mg", "₹45", "4", "₹180", "Cancelled", "2024-01-05")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "My Orders",
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
                    IconButton(onClick = { /* Filter */ }) {
                        Icon(
                            imageVector = Icons.Default.FilterList,
                            contentDescription = "Filter",
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
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("Search orders...", color = TextBlue) },
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
                // Status Tabs
                ScrollableTabRow(
                    selectedTabIndex = 0,
                    containerColor = Color.Transparent,
                    edgePadding = 0.dp,
                    indicator = { tabPositions ->
                        TabRowDefaults.SecondaryIndicator(
                            modifier = Modifier.tabIndicatorOffset(tabPositions[0]),
                            color = ButtonRed
                        )
                    }
                ) {
                    listOf("All", "Delivered", "Processing", "Cancelled").forEachIndexed { index, title ->
                        Tab(
                            selected = index == 0,
                            onClick = { /* Change tab */ },
                            text = {
                                Text(
                                    text = title,
                                    color = if (index == 0) ButtonRed else TextBlue,
                                    fontSize = 12.sp
                                )
                            }
                        )
                    }
                }
            }

            items(orders) { order ->
                OrderCard(order = order)
            }
        }
    }
}

@Composable
fun OrderCard(order: Order) {
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
                            .background(ButtonRed.copy(alpha = 0.1f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocalPharmacy,
                            contentDescription = null,
                            tint = ButtonRed,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = order.itemName,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = HeadingBlue
                        )
                        Text(
                            text = "Order ID: ${order.orderId}",
                            fontSize = 11.sp,
                            color = TextBlue
                        )
                    }
                }
                Text(
                    text = order.totalAmount,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = ButtonRed
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                InfoRow(icon = Icons.Default.CalendarToday, text = order.date)
                InfoRow(icon = Icons.Default.ShoppingCart, text = "Qty: ${order.quantity}")
                InfoRow(icon = Icons.Default.CurrencyRupee, text = order.pricePerUnit)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = when(order.status) {
                            "Delivered" -> Color(0xFF4CAF50).copy(alpha = 0.1f)
                            "Shipped" -> HeadingBlue.copy(alpha = 0.1f)
                            "Processing" -> Color(0xFFFF9800).copy(alpha = 0.1f)
                            "Cancelled" -> ButtonRed.copy(alpha = 0.1f)
                            else -> TextBlue.copy(alpha = 0.1f)
                        }
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = order.status,
                        fontSize = 11.sp,
                        color = when(order.status) {
                            "Delivered" -> Color(0xFF4CAF50)
                            "Shipped" -> HeadingBlue
                            "Processing" -> Color(0xFFFF9800)
                            "Cancelled" -> ButtonRed
                            else -> TextBlue
                        },
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                Row {
                    Button(
                        onClick = { /* Track order */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = HeadingBlue
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.height(32.dp)
                    ) {
                        Text(
                            text = "Track",
                            color = Color.White,
                            fontSize = 11.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    OutlinedButton(
                        onClick = { /* Reorder */ },
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = ButtonRed
                        ),
                        border = BorderStroke(1.dp, ButtonRed), // Fixed
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.height(32.dp)
                    ) {
                        Text(
                            text = "Reorder",
                            color = ButtonRed,
                            fontSize = 11.sp
                        )
                    }
                    }
                }
            }
        }
    }


data class Order(
    val orderId: String,
    val itemName: String,
    val pricePerUnit: String,
    val quantity: String,
    val totalAmount: String,
    val status: String,
    val date: String
)