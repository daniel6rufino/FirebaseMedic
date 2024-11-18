const express = require('express')
const app = express()
const handlebars = require('handlebars').engine
const bodyParser = require('body-parse')
const { initializeApp, applicationDefault, cert } = require('firebase-admin/app')
const { getFirestore, Timestamp, FieldValue } = require('firebase-admin/firestore')

const serviceAccount = require('')
const db = require('./firebase/firebase.js')

initializeApp({
    credential: cert(serviceAccount)
})

var {doc, setDoc, addDoc, collection, getDocs, getDoc, updateDoc, deleteDoc} = require("firebase/firestore")

//configurando o handlebars como view engine (motor de templates), e o main como layout padrão
app.engine("handlebars", handlebars({defaultLayout: "main"}))
app.set("view engine", "handlebars")

//utiliza o bodyParser para tratar dados passados por fomulários (urlencoded) e configura para receber JSONs
app.use(bodyParser.urlencoded({extended: false}))
app.use(bodyParser.json())


app.get("/", function(req, res){
    res.render("home_page")
})

app.get("/agendamento", function(req, res){
    res.render("agendamento")
})

app.post("/cadastrarAgendamento", function(req, res){
    addDoc(collection(db, "consultas"), { //adiciona um novo documento (addDoc) para a coleção (consultas)
        nome: req.body.nome,
        nascimento: req.body.nascimento,
        telefone: req.body.telefone,
        email: req.body.email,
        datahorario: req.body.datahorario,
        espmedica: req.body.espmedica,
        causa: req.body.causa
    }).then((data)=> { //(então) redireciona para a home ('/')
        res.redirect('/')
    }).catch(function(erro){
        console.log("Ocorreu um erro: "+ erro)
    })
})

app.post("/atualizarAgendamento", function(req, res){
    const refAgen = doc(db, "consultas", req.body.id) //cria a referencia do documento (consulta) desejado
    updateDoc(refAgen, { //usa o updateDoc, recebendo a referencia (refAgen) e o objeto {}, para atualizar o documento desejado
        nome: req.body.nome,
        nascimento: req.body.nascimento,
        telefone: req.body.telefone,
        email: req.body.email,
        datahorario: req.body.datahorario,
        espmedica: req.body.espmedica,
        causa: req.body.causa
    }).then((data)=> { //(então) redireciona para a home ('/')
        res.redirect('/consultaAgendamentos')
    }).catch(function(erro){
        console.log("Ocorreu um erro: "+ erro)
    })
})

app.get("/consultaAgendamentos", function(req, res){
    getDocs(collection(db, "consultas")).then((data) => { //recupera todos os documentos do (consultas)
        var consultas = [] //array vazio

        data.forEach((docs) => { //percorre o primise(data) 
            consultas.push({id: docs.id, data: docs.data()}) //a cada iteração, adiciona-se um objeto dentro do array consultas
        })

        res.render('listaAgendamentos', {consultas: consultas})
    }).catch(function(erro){
        console.log("Ocorreu um erro: "+ erro)
    })
})

app.get("/excluir/:id", function(req, res){
    deleteDoc(doc(db, "consultas",req.params.id)).then(() => { //deleta o documento na coleção consutlas, usando o id passado por parametro
        res.redirect("/consultaAgendamentos")
    }).catch(function(erro){
        console.log("Ocorreu um erro: "+ erro)
    })
})

app.listen(8081, function(){
    console.log("Servidor ativo!")
})

