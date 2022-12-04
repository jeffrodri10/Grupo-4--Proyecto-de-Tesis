package com.proyecto.mejorar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val bundle:Bundle?=intent.extras
        val email:String?=bundle?.getString("email")
        setup(email?:"")

    }
    private fun setup(email: String){
        title="Inicio"
        var cerrar = findViewById<Button>(R.id.btnCerrarSesion) as Button
        var emailhome = findViewById<View>(R.id.txtEmailHome) as TextView
        emailhome.text=email

        cerrar.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            super.onBackPressed()
            val i = Intent(this, MainActivity::class.java)
            this.startActivity(i)
            finish()
        }
    }
}