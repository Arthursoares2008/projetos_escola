package com.example.game_english

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.game_english.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity() {

    // Variável de binding para acessar as views do layout.
    private lateinit var binding: ActivityFirstBinding

    // Método chamado quando a atividade é criada.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Infla o layout usando View Binding e configura o conteúdo da atividade.
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configura o clique do botão para iniciar MainActivity.
        binding.btnStartGame.setOnClickListener {
            // Cria um Intent para iniciar MainActivity.
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)  // Inicia MainActivity.
        }
    }
}
