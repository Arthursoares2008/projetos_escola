package com.example.jogodavelha

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.jogodavelha.databinding.AcivityInicioBinding
import com.example.jogodavelha.databinding.ActivityMainBinding

class Inicio : AppCompatActivity() {

    // Declaração da propriedade binding com late-initialization
    // Será usada para acessar os elementos da interface do usuário
    private lateinit var binding: ActivityInicioBinding

    // Método onCreate é chamado quando a atividade é criada
    override fun onCreate(savedInstanceState: Bundle?) {
        // Inicializa o objeto binding com o layout inflater
        // Permite acessar os elementos de layout associados ao ActivityInicioBinding
        binding = ActivityInicioBinding.inflate(layoutInflater)

        // Chama o método onCreate da classe pai (AppCompatActivity)
        // Isso deve ser feito após a inicialização do binding para garantir que a atividade seja configurada corretamente
        super.onCreate(savedInstanceState)

        // Define o layout da atividade para o root do binding
        // Isso conecta a hierarquia de visualização com a atividade
        setContentView(binding.root)

        // Configura um clique para o botão nivel1
        binding.nivel1.setOnClickListener {
            // Cria uma ação para iniciar a MainActivity
            val intent = Intent(this, MainActivity::class.java)

            // Inicia a atividade especificada pela intenção
            startActivity(intent)
        }

        // Configura um clique para o botão nivel2
        binding.nivel2.setOnClickListener {
            // Cria uma ação para iniciar a MainActivity2
            val intent = Intent(this, MainActivity2::class.java)

            // Inicia a atividade especificada pela ação
            startActivity(intent)
        }
    }
}
