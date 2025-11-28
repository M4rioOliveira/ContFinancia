package com.example.contfinancia

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class VisaoGeral : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tela_incial)

        val addRecebi: Button = findViewById(R.id.buttonRecebi)
        val addGastei: Button = findViewById(R.id.buttonGastei)
        val buttonIa: Button = findViewById(R.id.buttonIA)
        val relatorio: ImageView = findViewById(R.id.graficoPizza)

        addRecebi.setOnClickListener {
            val intent = Intent(this, RegistroRecebido::class.java)
            startActivity(intent);
        }
        addGastei.setOnClickListener {
            val intent = Intent(this, RegistroGastos::class.java)
            startActivity(intent);
        }
        buttonIa.setOnClickListener {
            val intent = Intent(this, AcessoAoChatbot::class.java)
            startActivity(intent);
        }
        relatorio.setOnClickListener {
            val intent = Intent(this, RelatorioSimples::class.java)
            startActivity(intent);
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}