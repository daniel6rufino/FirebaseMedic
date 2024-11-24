package com.example.firebasemedic

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firebasemedic.pages.CadastrarScreen
import com.example.firebasemedic.pages.EditScreen
import com.example.firebasemedic.pages.HomeScreen
import com.example.firebasemedic.pages.LoginScreen
import com.example.firebasemedic.pages.ReadScreen
import com.example.firebasemedic.pages.SignupScreen
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun Navgation(modifier: Modifier = Modifier, authViewModel: AuthViewModel, db : FirebaseFirestore) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login", builder = {
        composable("login"){
            LoginScreen(modifier, navController, authViewModel, db)
        }
        composable("signup"){
            SignupScreen(modifier, navController, authViewModel, db)
        }
        composable("home"){
            HomeScreen(modifier, navController, authViewModel, db)
        }
        composable("cadastrar"){
            CadastrarScreen(modifier, navController, authViewModel, db)
        }
        composable("read"){
            ReadScreen(modifier, navController, authViewModel, db)
        }
        composable("edit/{consultaId}") { backStackEntrt ->
            val consultaId = backStackEntrt.arguments?.getString("consultaId") ?: ""
            EditScreen(modifier, navController, authViewModel, db, consultaId)
        }
    })
}