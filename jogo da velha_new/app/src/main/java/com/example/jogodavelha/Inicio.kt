package com.example.jogodavelha

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.jogodavelha.databinding.AcivityInicioBinding
import com.example.jogodavelha.databinding.ActivityMainBinding

class Inicio : AppCompatActivity() {
    private lateinit var binding:AcivityInicioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = AcivityInicioBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.nivel1.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }
        binding.nivel2.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)

            startActivity(intent)
        }
    }
}