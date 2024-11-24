package com.example.firebasemedic.pages

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.firebasemedic.AuthViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

@Composable
fun EditScreen(modifier: Modifier = Modifier, navController: NavController, authViewModel: AuthViewModel, db : FirebaseFirestore, consultaId: String) {
    var nome by remember {
        mutableStateOf("")
    }

    var telefone by remember {
        mutableStateOf("")
    }

    var nascimento by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var datahorario by remember {
        mutableStateOf("")
    }

    var espmedica by remember {
        mutableStateOf("")
    }

    var causa by remember {
        mutableStateOf("")
    }

    val user = Firebase.auth.currentUser

    remember {
        db.collection("consultas")
            .document(consultaId)
            .get()
            .addOnSuccessListener { document ->
                if(document != null){
                    nome = document.getString("nome") ?: ""
                    telefone = document.getString("telefone") ?: ""
                    nascimento = document.getString("nascimento") ?: ""
                    email = document.getString("email") ?: ""
                    datahorario = document.getString("datahorario") ?: ""
                    espmedica = document.getString("espmedica") ?: ""
                    causa = document.getString("causa") ?: ""
                }
            }
            .addOnFailureListener { exception ->
                Log.e("EditScreen", "Erro ao carregar dados da consulta", exception)
            }
    }

    Column(
        Modifier
            .background(Color(0xFF9FDBD2))
            .fillMaxSize(),
        // verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(32.dp))
        Text("Editar Consulta",
            style = TextStyle(color = Color(0xFFDBA0DB),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text("nome")
        TextField(
            value = nome,
            onValueChange = { nome = it }
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text("data de nascimento")
        TextField(
            value = nascimento,
            onValueChange = { nascimento = it }
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text("Telefone")
        TextField(
            value = telefone,
            onValueChange = { telefone = it }
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text("e-mail")
        TextField(
            value = email,
            onValueChange = { email = it }
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text("data e horário da consulta")
        TextField(
            value = datahorario,
            onValueChange = { datahorario = it }
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text("Especialidade Médica")
        TextField(
            value = espmedica,
            onValueChange = { espmedica = it }
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text("Causa/sintomas")
        TextField(
            value = causa,
            onValueChange = { causa = it }
        )
        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            val consultaAtualizada = hashMapOf(
                "userid" to (user?.uid ?: ""),
                "nome" to nome,
                "nascimento" to nascimento,
                "telefone" to telefone,
                "email" to email,
                "datahorario" to datahorario,
                "espmedica" to espmedica,
                "causa" to causa
            )

            db.collection("consultas")
                .document(consultaId)
                .set(consultaAtualizada)
                .addOnSuccessListener { documentReference ->
                    Log.d("TESTE", "Consulta Atualizada com sucesso")
                    navController.navigate("read")
                }
                .addOnFailureListener { e ->
                    Log.w("Teste", "Erro ao atualizar consulta", e)
                }
        }){
            Text("Salvar Alterações",
                style = TextStyle(color = Color(0xFFDBA0DB),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = {
            navController.navigate("home")
        }) {
            Text("cancelar edição",
                style = TextStyle(color = Color(0xFFDBA0DB),
                    fontSize = 12.sp
                )
            )
        }
    }
}