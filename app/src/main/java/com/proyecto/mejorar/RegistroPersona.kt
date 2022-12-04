package com.proyecto.mejorar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.firestore.FirebaseFirestore

class RegistroPersona : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_persona)
        val bundle:Bundle?=intent.extras
        val email:String?=bundle?.getString("email")
        setup(email?:"")
    }
    private fun setup(email: String){
        title="Registar perfil"
        var agregar = findViewById<Button>(R.id.btnRegistroDatos) as Button
        var emailhome = findViewById<View>(R.id.txtcorreoperfil) as TextView
        var Nombre = findViewById<View>(R.id.txtNombreDatos) as TextView
        var Apellido = findViewById<View>(R.id.txtApellidoDatos) as TextView
        var Dni = findViewById<View>(R.id.txtDniDatos) as TextView
        emailhome.text=email
        agregar.setOnClickListener {
            val nombre = Nombre.text.toString().trim()
            val apellido= Apellido.text.toString().trim()
            val dni= Dni.text.toString().trim()

            if (nombre.isEmpty()){
                Nombre.error="Ingrese su Nombre"
                Nombre.requestFocus()
                return@setOnClickListener
            }
            if (apellido.isEmpty()){
                Apellido.error="Ingrese su Apellido"
                Apellido.requestFocus()
                return@setOnClickListener
            }
            if (dni.isEmpty()){
                Dni.error="Ingrese su Dni"
                Dni.requestFocus()
                return@setOnClickListener
            }

            db.collection("Persona").document(email).set(
                hashMapOf(
                    "Nombre" to Nombre.text.toString(),
                    "Apellido" to Apellido.text.toString(),
                    "Dni" to Dni.text.toString())
            )

            val url = "http://192.168.1.5/ffcf/crearpersona.php"
            val queue = Volley.newRequestQueue(this)
            var resultadoPost = object : StringRequest(Request.Method.POST, url,
                Response.Listener<String> { response ->
                    Toast.makeText(this, "BUENA , AHORA YA ESTAS REGISTRADO", Toast.LENGTH_LONG).show()
                }, Response.ErrorListener { error ->
                    Toast.makeText(this, "ERROR DE REGISTRO $error", Toast.LENGTH_LONG)
                }) {
                override fun getParams(): MutableMap<String, String>? {
                    val parametros=HashMap<String,String>()
                    parametros.put("NOMBRE",Nombre?.text.toString())
                    parametros.put("APELLIDO",Apellido?.text.toString())
                    parametros.put("DNI",Dni?.text.toString())
                    parametros.put("CORREO",emailhome?.text.toString())

                    return parametros
                }
            }
            queue.add(resultadoPost)
            val homeIntent= Intent(this,Interfaz::class.java).apply {
                putExtra("email",email)
            }
            startActivity(homeIntent)
        }
    }
}