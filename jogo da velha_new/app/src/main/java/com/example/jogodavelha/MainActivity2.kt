package com.example.jogodavelha

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.jogodavelha.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity2 : AppCompatActivity() {
    // Declaração de uma variável para o binding da Activity
    private lateinit var binding: ActivityMainBinding

    // Vetor bidimensional que representará o tabuleiro de jogo
    val tabuleiro = arrayOf(
        arrayOf("", "", ""),
        arrayOf("", "", ""),
        arrayOf("", "", "")
    )

    // Qual jogador está jogando ("X" ou "O")
    var jogadorAtual = "X"

    // Método chamado ao criar a Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inicializa o binding com a visualização inflada do layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    // Função associada com todos os botões, onde 'view' é o botão clicado
    fun buttonClick(view: View) {
        // O botão clicado é associado a uma constante
        val buttonSelecionado = view as Button

        // De acordo com o botão clicado, a posição receberá o jogador atual
        when (buttonSelecionado.id) {
            binding.buttonZero.id -> tabuleiro[0][0] = jogadorAtual
            binding.buttonUm.id -> tabuleiro[0][1] = jogadorAtual
            binding.buttonDois.id -> tabuleiro[0][2] = jogadorAtual
            binding.buttonTres.id -> tabuleiro[1][0] = jogadorAtual
            binding.buttonQuatro.id -> tabuleiro[1][1] = jogadorAtual
            binding.buttonCinco.id -> tabuleiro[1][2] = jogadorAtual
            binding.buttonSeis.id -> tabuleiro[2][0] = jogadorAtual
            binding.buttonSete.id -> tabuleiro[2][1] = jogadorAtual
            binding.buttonOito.id -> tabuleiro[2][2] = jogadorAtual
        }

        // Muda a aparência do botão clicado e o desabilita
        buttonSelecionado.setBackgroundResource(R.drawable.x)
        buttonSelecionado.isEnabled = false

        // Verifica se há um vencedor após a jogada
        var vencedor = verificaVencedor(tabuleiro)
        if (!vencedor.isNullOrBlank()) {
            // Exibe uma mensagem de vencedor e reinicia a Activity
            Toast.makeText(this, "Vencedor: $vencedor", Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        // Movimento do computador usando algoritmo Minimax
        if (vencedor == null) {
            val melhorMovimento = encontrarMelhorMovimento(tabuleiro)
            tabuleiro[melhorMovimento.first][melhorMovimento.second] = "O"
            atualizarBotao(melhorMovimento.first, melhorMovimento.second)
        }

        // Verifica novamente se há um vencedor após a jogada do "O"
        vencedor = verificaVencedor(tabuleiro)
        if (!vencedor.isNullOrBlank()) {
            // Exibe uma mensagem de vencedor e reinicia a Activity
            Toast.makeText(this, "Vencedor: $vencedor", Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    // Função para atualizar a aparência do botão baseado na posição no tabuleiro
    fun atualizarBotao(x: Int, y: Int) {
        val posicao = x * 3 + y
        when (posicao) {
            0 -> {
                binding.buttonZero.setBackgroundResource(R.drawable.o)
                binding.buttonZero.isEnabled = false
            }
            1 -> {
                binding.buttonUm.setBackgroundResource(R.drawable.o)
                binding.buttonUm.isEnabled = false
            }
            2 -> {
                binding.buttonDois.setBackgroundResource(R.drawable.o)
                binding.buttonDois.isEnabled = false
            }
            3 -> {
                binding.buttonTres.setBackgroundResource(R.drawable.o)
                binding.buttonTres.isEnabled = false
            }
            4 -> {
                binding.buttonQuatro.setBackgroundResource(R.drawable.o)
                binding.buttonQuatro.isEnabled = false
            }
            5 -> {
                binding.buttonCinco.setBackgroundResource(R.drawable.o)
                binding.buttonCinco.isEnabled = false
            }
            6 -> {
                binding.buttonSeis.setBackgroundResource(R.drawable.o)
                binding.buttonSeis.isEnabled = false
            }
            7 -> {
                binding.buttonSete.setBackgroundResource(R.drawable.o)
                binding.buttonSete.isEnabled = false
            }
            8 -> {
                binding.buttonOito.setBackgroundResource(R.drawable.o)
                binding.buttonOito.isEnabled = false
            }
        }
    }

    // Função para verificar se há um vencedor no tabuleiro
    fun verificaVencedor(tabuleiro: Array<Array<String>>): String? {
        // Verifica linhas e colunas
        for (i in 0 until 3) {
            if (tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][1] == tabuleiro[i][2] && tabuleiro[i][0].isNotEmpty()) {
                return tabuleiro[i][0]
            }
            if (tabuleiro[0][i] == tabuleiro[1][i] && tabuleiro[1][i] == tabuleiro[2][i] && tabuleiro[0][i].isNotEmpty()) {
                return tabuleiro[0][i]
            }
        }
        // Verifica diagonais
        if (tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2] && tabuleiro[0][0].isNotEmpty()) {
            return tabuleiro[0][0]
        }
        if (tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][0] && tabuleiro[0][2].isNotEmpty()) {
            return tabuleiro[0][2]
        }
        // Verifica empate
        if (tabuleiro.all { linha -> linha.all { it.isNotEmpty() } }) {
            return "Empate"
        }
        // Nenhum vencedor
        return null
    }

    // Função para encontrar o melhor movimento usando o algoritmo Minimax
    fun encontrarMelhorMovimento(tabuleiro: Array<Array<String>>): Pair<Int, Int> {
        var melhorVal = -1000
        var melhorMovimento = Pair(-1, -1)

        for (i in tabuleiro.indices) {
            for (j in tabuleiro[i].indices) {
                if (tabuleiro[i][j].isEmpty()) {
                    tabuleiro[i][j] = "O"
                    val movimentoVal = minimax(tabuleiro, 0, false)
                    tabuleiro[i][j] = ""

                    if (movimentoVal > melhorVal) {
                        melhorMovimento = Pair(i, j)
                        melhorVal = movimentoVal
                    }
                }
            }
        }
        return melhorMovimento
    }

    // Função Minimax para calcular o valor do movimento
    fun minimax(tabuleiro: Array<Array<String>>, profundidade: Int, isMax: Boolean): Int {
        val score = avaliar(tabuleiro)

        // Se o jogador "O" vence
        if (score == 10) return score - profundidade
        // Se o jogador "X" vence
        if (score == -10) return score + profundidade
        // Se não há mais movimentos disponíveis
        if (tabuleiro.all { linha -> linha.all { it.isNotEmpty() } }) return 0

        if (isMax) {
            var melhor = -1000

            for (i in tabuleiro.indices) {
                for (j in tabuleiro[i].indices) {
                    if (tabuleiro[i][j].isEmpty()) {
                        tabuleiro[i][j] = "O"
                        melhor = maxOf(melhor, minimax(tabuleiro, profundidade + 1, false))
                        tabuleiro[i][j] = ""
                    }
                }
            }
            return melhor
        } else {
            var melhor = 1000

            for (i in tabuleiro.indices) {
                for (j in tabuleiro[i].indices) {
                    if (tabuleiro[i][j].isEmpty()) {
                        tabuleiro[i][j] = "X"
                        melhor = minOf(melhor, minimax(tabuleiro, profundidade + 1, true))
                        tabuleiro[i][j] = ""
                    }
                }
            }
            return melhor
        }
    }

    // Função para avaliar o tabuleiro e retornar uma pontuação
    fun avaliar(tabuleiro: Array<Array<String>>): Int {
        for (i in 0 until 3) {
            if (tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][1] == tabuleiro[i][2]) {
                if (tabuleiro[i][0] == "O") return 10
                else if (tabuleiro[i][0] == "X") return -10
            }
            if (tabuleiro[0][i] == tabuleiro[1][i] && tabuleiro[1][i] == tabuleiro[2][i]) {
                if (tabuleiro[0][i] == "O") return 10
                else if (tabuleiro[0][i] == "X") return -10
            }
        }
        if (tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2]) {
            if (tabuleiro[0][0] == "O") return 10
            else if (tabuleiro[0][0] == "X") return -10
        }
        if (tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][0]) {
            if (tabuleiro[0][2] == "O") return 10
            else if (tabuleiro[0][2] == "X") return -10
        }
        return 0
    }
}
