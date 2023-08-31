package com.francisgonzales.kotlinfirebaseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.francisgonzales.KotlinFirebaseAuth.R
import com.google.firebase.auth.FirebaseAuth


class HomeActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var tvUserEmail : TextView
    private lateinit var btnLogout : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        tvUserEmail = findViewById(R.id.tvUserEmail)
        btnLogout = findViewById(R.id.btnLogout)
        auth = FirebaseAuth.getInstance()

        val currentUser = auth.currentUser
        if (currentUser != null) {
            val userEmail = currentUser.email
            tvUserEmail.text = userEmail
        }

        btnLogout.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }





    }
}