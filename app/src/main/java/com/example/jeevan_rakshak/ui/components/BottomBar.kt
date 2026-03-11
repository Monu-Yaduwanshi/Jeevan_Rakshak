package com.example.jeevan_rakshak.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import com.example.jeevan_rakshak.viewmodels.ButtonRed
import com.example.jeevan_rakshak.viewmodels.HeadingBlue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.jeevan_rakshak.navigation.Screen
import com.example.jeevan_rakshak.ui.theme.*

@Composable
fun BottomBar(navController: NavController) {

    val items = listOf(
        BottomNavItem(Screen.Home, "Home", Icons.Default.Home),
        BottomNavItem(Screen.Ambulance, "Ambulanc", Icons.Default.AirportShuttle),
        BottomNavItem(Screen.Pharmacy, "Pharmacy", Icons.Default.LocalPharmacy),
        BottomNavItem(Screen.Hospital, "Hospital", Icons.Default.LocalHospital),
        BottomNavItem(Screen.Profile, "Profile", Icons.Default.Person)
    )

    NavigationBar(
        containerColor = ButtonRed,
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        popUpTo(Screen.Home.route) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = if (currentRoute == item.screen.route)
                            Color(0xFFFFDF91)
                        else
                            Color.White.copy(alpha = 0.6f)
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        color = if (currentRoute == item.screen.route)
                            Color(0xFFFFDF91)
                        else
                            Color.White.copy(alpha = 0.6f)
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.Blue,
                    indicatorColor = HeadingBlue,
                    unselectedIconColor = Color.White.copy(alpha = 0.6f),
                    unselectedTextColor = Color.White.copy(alpha = 0.6f)
                ),
                // Add these two lines
//                interactionSource = remember { MutableInteractionSource() },
//                indication = null
            )
        }
    }
}

data class BottomNavItem(
    val screen: Screen,
    val title: String,
    val icon: ImageVector
)
