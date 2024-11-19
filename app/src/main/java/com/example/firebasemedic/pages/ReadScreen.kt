package com.example.firebasemedic.pages

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.firebasemedic.AuthViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

@Composable
fun ReadScreen(modifier: Modifier = Modifier, navController: NavController, authViewModel: AuthViewModel, db : FirebaseFirestore){
    val user = Firebase.auth.currentUser

    Column {
        val agendamentos = remember { mutableListOf<HashMap<String, String>>() }
        db.collection("consulta")
            .whereEqualTo("userid", (user?.uid ?: ""))
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val lista = hashMapOf(
                        "nome" to "${document.data.get("nome")}",
                        "datahorario" to "${document.data.get("datahorario")}",
                        "espmedica" to "${document.data.get("telefone")}"
                    )
                    agendamentos.add(lista)
                }
            }
            .addOnFailureListener{ exception ->
                Log.w("teste", "erro ao pegar documentos: ", exception)
            }
    }

    Column (
        Modifier
            .background(Color(0xFF9FDBD2))
            .fillMaxSize(),
        // verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column (
            Modifier
                .fillMaxWidth(0.12f)
        ) {
            Text("pasciente: ")
        }

        Column (
            Modifier
                .fillMaxWidth(0.12f)
        ) {

        }

        Column (
            Modifier
                .fillMaxWidth(0.12f)
        ) {
            Text("data de consulta:")
        }

        Column (
            Modifier
                .fillMaxWidth(0.12f)
        ) {

        }

        Column (
            Modifier
                .fillMaxWidth(0.12f)
        ) {
            Text("especilidade médica:")
        }

        Column (
            Modifier
                .fillMaxWidth(0.12f)
        ) {

        }

        Column (
            Modifier
                .fillMaxWidth(0.12f)
        ) {
            Button(onClick = { /*TODO*/ }) {
                Text("Cancelar consulta",
                    style = TextStyle(color = Color(0xFFDBA0DB),
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Bold)
                )
            }
        }

//        LazyColumn(modifier = Modifier.fillMaxWidth()) {
//            items(agendamentos) { cliente ->
//                Row(modifier = Modifier.fillMaxWidth()) {
//                    Column(modifier = Modifier.weight(0.5f)) {
//                        Text(text = cliente["nome"] ?: "--")
//                    }
//                    Column(modifier = Modifier.weight(0.5f)) {
//                        Text(text = cliente["telefone"] ?: "--")
//                    }
//                }
//            }
//        }
    }
}