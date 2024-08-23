package com.example.game_english

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.game_english.databinding.ActivityMainBinding
import kotlin.random.Random
import android.graphics.Color

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val colorMap = mapOf(
        "Vermelho" to "Red",
        "Azul" to "Blue",
        "Verde" to "Green",
        "Amarelo" to "Yellow",
        "Preto" to "Black",
        "Branco" to "White"
    )

    private lateinit var currentColorInPortuguese: String
    private lateinit var correctAnswer: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupOptionButtons()
        selectRandomColor()
    }

    private fun setupOptionButtons() {
        binding.btnOption1.setOnClickListener { checkAnswer(binding.btnOption1.text.toString()) }
        binding.btnOption2.setOnClickListener { checkAnswer(binding.btnOption2.text.toString()) }
        binding.btnOption3.setOnClickListener { checkAnswer(binding.btnOption3.text.toString()) }
        binding.btnOption4.setOnClickListener { checkAnswer(binding.btnOption4.text.toString()) }
    }

    private fun selectRandomColor() {
        val colors = colorMap.keys.toList()
        currentColorInPortuguese = colors[Random.nextInt(colors.size)]
        correctAnswer = colorMap[currentColorInPortuguese]!!

        binding.tvColorInPortuguese.text = currentColorInPortuguese
        binding.tvResult.text = ""

        // Gera opções de resposta, garantindo que uma delas é a correta
        val options = mutableListOf(correctAnswer)
        while (options.size < 4) {
            val randomColorInEnglish = colorMap.values.random()
            if (!options.contains(randomColorInEnglish)) {
                options.add(randomColorInEnglish)
            }
        }
        options.shuffle()

        // Atribui as opções aos botões
        binding.btnOption1.text = options[0]
        binding.btnOption2.text = options[1]
        binding.btnOption3.text = options[2]
        binding.btnOption4.text = options[3]
    }

    private fun checkAnswer(selectedAnswer: String) {
        if (selectedAnswer == correctAnswer) {
            binding.tvResult.text = "Acertou! Você é uma criança muito esperta!"
            binding.tvResult.setTextColor(Color.WHITE)
        } else {
            binding.tvResult.text = "Errado! A resposta certa é $correctAnswer."
            binding.tvResult.setTextColor(Color.WHITE)
        }

        // Aguarda um breve momento antes de selecionar a próxima cor
        binding.tvResult.postDelayed({
            selectRandomColor()
        }, 1500) // Aguarda 1,5 segundos antes de selecionar a próxima cor
    }
}