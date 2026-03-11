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
fun MyContactsScreen(navController: NavController) {
    // Sample contacts
    val contacts = listOf(
        Contact("John Doe", "+91 9876543210", "Father", Icons.Default.Person),
        Contact("Jane Doe", "+91 9876543211", "Mother", Icons.Default.Person),
        Contact("Dr. Sharma", "+91 9876543212", "Family Doctor", Icons.Default.MedicalServices),
        Contact("City Hospital", "+91 9876543213", "Emergency", Icons.Default.LocalHospital),
        Contact("Red Cross", "+91 9876543214", "Ambulance", Icons.Default.AirportShuttle),
        Contact("Neha Singh", "+91 9876543215", "Sister", Icons.Default.Person),
        Contact("Rahul Verma", "+91 9876543216", "Brother", Icons.Default.Person)
    )

    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Emergency Contacts",
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
                    IconButton(onClick = { /* Add contact */ }) {
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
            item {
                // Search Bar
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Search contacts...", color = TextBlue) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = TextBlue
                        )
                    },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { searchQuery = "" }) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Clear",
                                    tint = TextBlue
                                )
                            }
                        }
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
                // SOS Button
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = ButtonRed
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "SOS - Tap to send emergency alert",
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            item {
                Text(
                    text = "Emergency Contacts",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = HeadingBlue,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            items(contacts) { contact ->
                ContactCard(contact = contact)
            }
        }
    }
}

@Composable
fun ContactCard(contact: Contact) {
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
                        when(contact.relation) {
                            "Emergency" -> ButtonRed.copy(alpha = 0.1f)
                            "Family Doctor" -> HeadingBlue.copy(alpha = 0.1f)
                            else -> TextBlue.copy(alpha = 0.1f)
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = contact.icon,
                    contentDescription = null,
                    tint = when(contact.relation) {
                        "Emergency" -> ButtonRed
                        "Family Doctor" -> HeadingBlue
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
                    text = contact.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = HeadingBlue
                )
                Text(
                    text = "${contact.relation} • ${contact.phone}",
                    fontSize = 12.sp,
                    color = TextBlue
                )
            }

            Column(
                horizontalAlignment = Alignment.End
            ) {
                IconButton(
                    onClick = { /* Call contact */ }
                ) {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = "Call",
                        tint = Color(0xFF4CAF50),
                        modifier = Modifier.size(24.dp)
                    )
                }
                IconButton(
                    onClick = { /* Message contact */ }
                ) {
                    Icon(
                        imageVector = Icons.Default.Message,
                        contentDescription = "Message",
                        tint = HeadingBlue,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

data class Contact(
    val name: String,
    val phone: String,
    val relation: String,
    val icon: ImageVector
)