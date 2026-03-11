package com.example.jeevan_rakshak.ui.drawer

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jeevan_rakshak.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PendingAppointmentsScreen(navController: NavController) {

    val appointments = listOf(
        "Rahul - Fever",
        "Anita - Headache",
        "Mohit - Diabetes Check"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pending Appointments") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {

            items(appointments) { item ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = YellowField
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {

                        Text(
                            text = item,
                            fontSize = 16.sp,
                            color = BlueText
                        )

                        Spacer(Modifier.height(8.dp))

                        Button(
                            onClick = { },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = RedButton
                            )
                        ) {
                            Text("Accept", color = androidx.compose.ui.graphics.Color.White)
                        }
                    }
                }
            }
        }
    }
}