package com.example.firebasemedic

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firebasemedic.pages.HomeScreen
import com.example.firebasemedic.pages.LoginScreen
import com.example.firebasemedic.pages.SignupScreen

@Composable
fun Navgation(modifier: Modifier = Modifier, authViewModel: AuthViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login", builder = {
        composable("login"){
            LoginScreen(modifier, navController, authViewModel)
        }
        composable("signup"){
            SignupScreen(modifier, navController, authViewModel)
        }
        composable("home"){
            HomeScreen(modifier, navController, authViewModel)
        }
    })
}