package com.ansssiaz.randuser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ansssiaz.randuser.presentation.screens.ListOfUsersScreen
import com.ansssiaz.randuser.presentation.screens.UserInformationScreen
import com.ansssiaz.randuser.presentation.viewmodel.UsersViewModel
import com.ansssiaz.randuser.ui.theme.RandUserTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RandUserTheme {
                val viewModel: UsersViewModel by viewModel()
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "listOfUsersScreen") {
                    composable("listOfUsersScreen") {
                        ListOfUsersScreen(
                            usersViewModel = viewModel,
                            onUserClick = { user ->
                                viewModel.setSelectedUser(user)
                                navController.navigate("userInformationScreen")
                            }
                        )
                    }
                    composable("userInformationScreen") {
                        UserInformationScreen(
                            usersViewModel = viewModel
                        )
                        {
                            viewModel.resetSelectedUser()
                            navController.popBackStack()
                        }
                    }
                }
            }
        }
    }
}