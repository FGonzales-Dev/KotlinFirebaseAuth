package com.francisgonzales.kotlinfirebaseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.francisgonzales.KotlinFirebaseAuth.LogInActivity
import com.francisgonzales.KotlinFirebaseAuth.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {
    private lateinit var etEmailAddress: EditText
    private lateinit var etConfirmPassWord: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnSignUp: Button
    private lateinit var  btnLogin : Button

    // create Firebase authentication object
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        etEmailAddress = findViewById(R.id.etEmailAddress)
        etConfirmPassWord = findViewById(R.id.etConfirmPassword)
        etPassword = findViewById(R.id.etPassword)
        btnSignUp = findViewById(R.id.btnSignUp)
        btnLogin = findViewById(R.id.btnLogin)
        auth = Firebase.auth
        btnSignUp.setOnClickListener{
            signUp()
        }
        btnLogin.setOnClickListener {
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun signUp() {
        var email = etEmailAddress.text.toString()
        var password = etPassword.text.toString()
        var confirmPassword = etConfirmPassWord.text.toString()

        if (email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }

        if (password != confirmPassword) {
            Toast.makeText(this, "Password and Confirm Password do not match", Toast.LENGTH_SHORT)
                .show()
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(this, "Successful account creation", Toast.LENGTH_SHORT).show()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
                    Log.d("test", task.exception.toString())
                }
            }


    }
}