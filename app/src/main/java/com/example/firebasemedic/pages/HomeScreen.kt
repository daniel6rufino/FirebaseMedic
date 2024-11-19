package com.example.firebasemedic.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.firebasemedic.AuthState
import com.example.firebasemedic.AuthViewModel
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun HomeScreen(
//    navToAgendar: () -> Unit,
//    navToConsultar: () -> Unit,
    modifier: Modifier = Modifier, navController: NavController, authViewModel: AuthViewModel, db : FirebaseFirestore
){
    val authState = authViewModel.authState.observeAsState()
    
   LaunchedEffect(authState.value) {
       when(authState.value){
           is AuthState.Unauthenticated -> navController.navigate("login")
           else -> Unit
       }
   }

    Column(
        Modifier
            .background(Color(0xFF9FDBD2))
            .fillMaxSize(),
        // verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text("Firebase Medic",
            style = TextStyle(color = Color(0xFFDBA0DB),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            navController.navigate("cadastrar")
        }){
            Text("Agendar Consulta",
                style = TextStyle(color = Color(0xFFDBA0DB),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {}/*navToConsultar*/){
            Text("Ver Agendamentos",
                style = TextStyle(color = Color(0xFFDBA0DB),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        
        TextButton(onClick = {
            authViewModel.signout()
        }) {
            Text(text = "encerrar sessão")
        }
    }
}