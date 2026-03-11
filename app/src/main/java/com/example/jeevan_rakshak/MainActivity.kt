//package com.example.jeevan_rakshak
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import com.example.jeevan_rakshak.ui.theme.Jeevan_RakshakTheme
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            Jeevan_RakshakTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    Jeevan_RakshakTheme {
//        Greeting("Android")
//    }
//}
package com.example.jeevan_rakshak

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.jeevan_rakshak.navigation.NavGraph
import com.example.jeevan_rakshak.ui.components.BottomBar
import com.example.jeevan_rakshak.ui.components.AppDrawer
import com.example.jeevan_rakshak.ui.theme.Jeevan_RakshakTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Jeevan_RakshakTheme {
                val navController = rememberNavController()
                NavGraph(navController)
            }
        }
    }
}
//class MainActivity : ComponentActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//
//        setContent {
//            Jeevan_RakshakTheme {
//
//                val navController = rememberNavController()
//                val drawerState = rememberDrawerState(DrawerValue.Closed)
//
//                ModalNavigationDrawer(
//                    drawerState = drawerState,
//                    drawerContent = {
//                        AppDrawer(navController)
//                    }
//                ) {
//
//                    Scaffold(
//                        bottomBar = {
//                            BottomBar(navController)
//                        }
//                    ) { innerPadding ->
//
//                        NavGraph(
//                            navController = navController,
//                            modifier = Modifier.padding(innerPadding)
//                        )
//                    }
//                }
//            }
//        }
//    }
//}