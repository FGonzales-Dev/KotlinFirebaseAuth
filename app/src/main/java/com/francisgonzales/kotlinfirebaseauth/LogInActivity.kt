package com.francisgonzales.KotlinFirebaseAuth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.francisgonzales.kotlinfirebaseauth.HomeActivity
import com.francisgonzales.kotlinfirebaseauth.SignUpActivity
import com.google.firebase.auth.FirebaseAuth

class LogInActivity : AppCompatActivity() {

    private lateinit var etEmailAddress : EditText
    private lateinit var etPassowrd: EditText
    private lateinit var btnLogin : Button
    private lateinit var btnSignUp : Button
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        etEmailAddress = findViewById(R.id.etEmailAddress)
        etPassowrd = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnSignUp = findViewById(R.id.btnSignUp)
        auth = FirebaseAuth.getInstance()

        btnLogin.setOnClickListener {
            login()
        }
        btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun login(){
        var email = etEmailAddress.text.toString()
        var password = etPassowrd.text.toString()

        if (email.isBlank() || password.isBlank()){
            Toast.makeText(this, "Email or password should not be blank", Toast.LENGTH_LONG).show()
            return
        }

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Successfully LoggedIn", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else
                Toast.makeText(this, "Log In failed ", Toast.LENGTH_SHORT).show()
        }
    }
}