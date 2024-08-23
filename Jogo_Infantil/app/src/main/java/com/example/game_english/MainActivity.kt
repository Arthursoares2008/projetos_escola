package com.example.game_english

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.game_english.databinding.ActivityMainBinding
import kotlin.random.Random
import android.graphics.Color

class MainActivity : AppCompatActivity() {

    // Variável de binding para acesso às views do layout.
    private lateinit var binding: ActivityMainBinding

    // Mapeamento de cores em português para inglês.
    private val colorMap = mapOf(
        "Vermelho" to "Red",
        "Azul" to "Blue",
        "Verde" to "Green",
        "Amarelo" to "Yellow",
        "Preto" to "Black",
        "Branco" to "White"
    )

    // Variáveis para a cor atual e a resposta correta.
    private lateinit var currentColorInPortuguese: String
    private lateinit var correctAnswer: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inicializa o binding com o layout da atividade.
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupOptionButtons() // Configura os botões de opção.
        selectRandomColor()  // Seleciona uma cor aleatória ao iniciar.
    }

    private fun setupOptionButtons() {
        // Configura listeners de clique para cada botão de opção para verificar a resposta.
        binding.btnOption1.setOnClickListener { checkAnswer(binding.btnOption1.text.toString()) }
        binding.btnOption2.setOnClickListener { checkAnswer(binding.btnOption2.text.toString()) }
        binding.btnOption3.setOnClickListener { checkAnswer(binding.btnOption3.text.toString()) }
        binding.btnOption4.setOnClickListener { checkAnswer(binding.btnOption4.text.toString()) }
    }

    private fun selectRandomColor() {
        // Seleciona uma cor aleatória em português e obtém a tradução correta.
        val colors = colorMap.keys.toList()
        currentColorInPortuguese = colors[Random.nextInt(colors.size)]
        correctAnswer = colorMap[currentColorInPortuguese]!!

        // Exibe a cor atual e limpa o resultado anterior.
        binding.tvColorInPortuguese.text = currentColorInPortuguese
        binding.tvResult.text = ""

        // Gera opções de resposta aleatórias, garantindo que uma delas seja a resposta correta.
        val options = mutableListOf(correctAnswer)
        while (options.size < 4) {
            val randomColorInEnglish = colorMap.values.random()
            if (!options.contains(randomColorInEnglish)) {
                options.add(randomColorInEnglish)
            }
        }
        options.shuffle() // Embaralha as opções para variar a ordem dos botões.

        // Define o texto dos botões com as opções geradas.
        binding.btnOption1.text = options[0]
        binding.btnOption2.text = options[1]
        binding.btnOption3.text = options[2]
        binding.btnOption4.text = options[3]
    }

    private fun checkAnswer(selectedAnswer: String) {
        // Verifica se a resposta selecionada é a correta e atualiza o texto do resultado.
        if (selectedAnswer == correctAnswer) {
            binding.tvResult.text = "Acertou! Você é uma criança muito esperta!"
        } else {
            binding.tvResult.text = "Errado! A resposta certa é $correctAnswer."
        }
        binding.tvResult.setTextColor(Color.WHITE) // Define a cor do texto do resultado.

        // Aguarda 1,5 segundos antes de selecionar uma nova cor para jogar novamente.
        binding.tvResult.postDelayed({
            selectRandomColor()
        }, 1500)
    }
}
