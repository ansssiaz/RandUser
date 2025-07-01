package com.ansssiaz.randuser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ansssiaz.randuser.presentation.screens.ListOfUsersScreen
import com.ansssiaz.randuser.ui.theme.RandUserTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RandUserTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "listOfUsersScreen") {
                    composable("listOfUsersScreen") {
                        ListOfUsersScreen(
                            {
                                navController.navigate("userInformationScreen")
                            }
                        )
                    }
                    /*composable("userInformationScreen") {
                        UserInformationScreen {
                            navController.popBackStack()
                        }
                    }*/
                }
            }
        }
    }
}