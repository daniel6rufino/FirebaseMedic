package com.example.firebasemedic

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel : ViewModel() {

    private val auth : FirebaseAuth = FirebaseAuth.getInstance()

}

sealed class AuthState{
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
}