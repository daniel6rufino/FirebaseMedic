package com.example.firebasemedic.pages

@Composable
fun CadastrarScreen(modifier: Modifier = Modifier, navController: NavController, authViewModel: AuthViewModel){
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

    Column(
        Modifier
            .background(Color(0xFF9FDBD2))
            .fillMaxSize(),
        // verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Agendar Consulta",
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
    }
}