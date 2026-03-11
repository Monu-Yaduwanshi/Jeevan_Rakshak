package com.example.jeevan_rakshak.ui.screens.bottomNavBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jeevan_rakshak.ui.components.BottomBar
import com.example.jeevan_rakshak.ui.theme.*
import com.example.jeevan_rakshak.viewmodels.ButtonRed
import com.example.jeevan_rakshak.viewmodels.HeadingBlue
import com.example.jeevan_rakshak.viewmodels.TextBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PharmacyScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Generic Medicine Store",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF062d66)
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
                    IconButton(onClick = { /* Navigate to cart */ }) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Cart",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) { paddingValues ->
        // Sample medicine data
        val medicines = listOf(
            Medicine("Paracetamol 500mg", "₹45", "Cipla", "20 tablets", "4.5"),
            Medicine("Amoxicillin 250mg", "₹120", "Sun Pharma", "10 capsules", "4.3"),
            Medicine("Vitamin C Tablets", "₹180", "Himalaya", "30 tablets", "4.7"),
            Medicine("Cetrizine 10mg", "₹35", "Dr. Reddy's", "15 tablets", "4.2"),
            Medicine("Azithromycin 500mg", "₹150", "Pfizer", "6 tablets", "4.6"),
            Medicine("Paracetamol 500mg", "₹45", "Cipla", "20 tablets", "4.5"),
            Medicine("Amoxicillin 250mg", "₹120", "Sun Pharma", "10 capsules", "4.3"),
            Medicine("Vitamin C Tablets", "₹180", "Himalaya", "30 tablets", "4.7"),
            Medicine("Cetrizine 10mg", "₹35", "Dr. Reddy's", "15 tablets", "4.2"),
            Medicine("Azithromycin 500mg", "₹150", "Pfizer", "6 tablets", "4.6")
        )

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
                    placeholder = { Text("Search medicines...", color = TextBlue) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = TextBlue
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = { /* Filter */ }) {
                            Icon(
                                imageVector = Icons.Default.FilterList,
                                contentDescription = "Filter",
                                tint = TextBlue
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedBorderColor = HeadingBlue,
                        unfocusedBorderColor = TextBlue
                    ),
                    shape = MaterialTheme.shapes.medium
                )
            }

            item {
                // Categories
                Text(
                    text = "Categories",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = HeadingBlue,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            item {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CategoryChip("Pain Relief", Icons.Default.MedicalServices)
                    CategoryChip("Antibiotics", Icons.Default.MedicalServices)
                    CategoryChip("Vitamins", Icons.Default.MedicalServices)
                }
            }

            item {
                Text(
                    text = "Popular Medicines",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = HeadingBlue,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            items(medicines) { medicine ->
                MedicineCard(medicine = medicine)
            }
        }
    }
}

@Composable
fun CategoryChip(name: String, icon: ImageVector) {
    AssistChip(
        onClick = { /* Filter by category */ },
        label = { Text(name, fontSize = 12.sp, color = HeadingBlue) },
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = ButtonRed,
                modifier = Modifier.size(16.dp)
            )
        },
        colors = AssistChipDefaults.assistChipColors(
            containerColor = Color.White
        ),
        border = AssistChipDefaults.assistChipBorder(
            borderColor = HeadingBlue.copy(alpha = 0.3f)
        )
    )
}

fun AssistChip(
    onClick: () -> Unit,
    label: @Composable () -> Unit,
    leadingIcon: @Composable () -> Unit,
    colors: androidx.compose.material3.ChipColors,
    border: androidx.compose.material3.ChipBorder
) {
}

@Composable
fun MedicineCard(medicine: Medicine) {
    Card(
        modifier = Modifier.fillMaxWidth().safeClickable { /* Handle card click */ }, // Fixed
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Medicine Icon
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(
                            color = ButtonRed.copy(alpha = 0.1f),
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.LocalPharmacy,
                        contentDescription = null,
                        tint = ButtonRed,
                        modifier = Modifier.size(28.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = medicine.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = HeadingBlue
                    )
                    Text(
                        text = "${medicine.manufacturer} • ${medicine.packSize}",
                        fontSize = 12.sp,
                        color = TextBlue
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = Color(0xFFFFC107),
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = " ${medicine.rating}",
                            fontSize = 12.sp,
                            color = TextBlue
                        )
                    }
                }
            }

            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = medicine.price,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = ButtonRed
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { /* Add to cart */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = HeadingBlue
                    ),
                    shape = CircleShape,
                    modifier = Modifier.height(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Add",
                        color = Color.White,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

data class Medicine(
    val name: String,
    val price: String,
    val manufacturer: String,
    val packSize: String,
    val rating: String
)