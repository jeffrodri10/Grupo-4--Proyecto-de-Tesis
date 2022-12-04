package com.proyecto.mejorar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlin.math.E

class Interfazadmin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interfazadmin)
        setup()
    }
    private fun setup() {
        var inicio = findViewById<Button>(R.id.interfazinicio) as Button
        var mensaje = findViewById<Button>(R.id.interfazmensaje) as Button
        var lugares = findViewById<Button>(R.id.interfazlugares) as Button
        var perfil = findViewById<Button>(R.id.interfazperfil) as Button


        inicio.setOnClickListener() {
            val i = Intent(this, EditPerfil::class.java)
            this.startActivity(i)
        }
        mensaje.setOnClickListener() {
            val i = Intent(this, Mensaje::class.java)
            this.startActivity(i)
        }
        lugares.setOnClickListener() {
            val i = Intent(this, Lugares::class.java)
            this.startActivity(i)
        }
        perfil.setOnClickListener() {
            val i = Intent(this, HomeActivity::class.java)
            this.startActivity(i)
            finish()
        }
    }
}