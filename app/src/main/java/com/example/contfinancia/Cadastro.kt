package com.example.contfinancia

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Cadastro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cadastro)

        val nextButton: Button = findViewById(R.id.buttonCadastrar)
        val campo1: EditText = findViewById(R.id.editTextDataNascimento)
        val campo2: EditText = findViewById(R.id.editTextSenha)
        val campo3: EditText = findViewById(R.id.editTextEmail)
        val campo4: EditText = findViewById(R.id.editTextNome)

        nextButton.setOnClickListener {

            val campo1 = campo1.text.trim()
            val campo2 = campo2.text.trim()
            val campo3 = campo3.text.trim()
            val campo4 = campo4.text.trim()

            if(campo1.isEmpty() or campo2.isEmpty() or campo3.isEmpty() or campo4.isEmpty()){
                Toast.makeText(this, "Preencha todos os campos para continuar", Toast.LENGTH_SHORT).show()
            }else{
                // Proceed to the next Activity
                val intent = Intent(this, VisaoGeral::class.java)
                startActivity(intent)
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}