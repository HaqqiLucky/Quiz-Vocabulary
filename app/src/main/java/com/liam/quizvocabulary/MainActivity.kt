package com.liam.quizvocabulary

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.liam.quizvocabulary.databinding.ActivityMainBinding

//Good code = kode yang mudah dibaca, mudah dipelihara, bersih, jelas, aman, dan efisien.

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var right = 0
    private var wrong = 0
    private var soalke = 1
    private var saveHereToCheckLater : String = ""
    private val questionanswerList : Map <String, String> = mapOf(
        "Blue" to "Biru",
        "Purple" to "Ungu",
        "Green" to "Hijau",
        "Orange" to "Jingga",
        "Yellow" to "Kuning",
        "Pink" to "Merah muda",
        "Black" to "Hitam",
        "White" to "Putih"
    )
    private var randomQuestionwithAnswer = questionanswerList.entries.random()
    private var valueOfRandomQuestionwithAnswer = randomQuestionwithAnswer.value


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        enableEdgeToEdge()
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        generateQuestion()
    }

//    questionansw
//    questionanswerList = // key to value

//    )



    private fun changeQuestion(){
        soalke++
        binding.total.text = soalke.toString()
        generateQuestion()
    }

    private fun check(){
        if (saveHereToCheckLater == valueOfRandomQuestionwithAnswer){
            Toast.makeText(this, "Jawaban kau bener", Toast.LENGTH_SHORT).show()
            Log.e("Main activity", "sampe sini")
            right++
            Log.e("MainAct","ini variabel right = {$right}")
            binding.bener.text = right.toString()
            changeQuestion()
        } else if (saveHereToCheckLater == null){
            Toast.makeText(this, "Silahkan jawab soal ini", Toast.LENGTH_SHORT).show()
        } else{
            Toast.makeText(this, "Salah jir", Toast.LENGTH_SHORT).show()
            wrong++
            Log.e("MainAct","ini variabel wrong = {$wrong}")
            binding.salah.text = wrong.toString()
            changeQuestion()
        }

    }

    private fun generateQuestion(){

        val falseAnswer = questionanswerList.values.shuffled().take(3).toMutableList()

        var keyOfRandomQuestionwithAnswer = randomQuestionwithAnswer.key

        var kocok = mutableListOf<String>(
            valueOfRandomQuestionwithAnswer , falseAnswer[1], falseAnswer[0], falseAnswer[2]
        )
        kocok.shuffle()

        binding.soal.text = keyOfRandomQuestionwithAnswer // harus di .text dlu biar bisa di bawa ke view
        binding.jawaban1.text = kocok[0]
        binding.jawaban2.text = kocok[1]
        binding.jawaban3.text = kocok[2]
        binding.jawaban4.text = kocok[3]

        binding.kartujawaban1.setOnClickListener {
            saveHereToCheckLater = kocok[0]
            check()
            Log.e("Main activity", "kartu jawaban1")
        }
        binding.kartujawaban2.setOnClickListener {
            saveHereToCheckLater = kocok[1]
            check()
            Log.e("Main activity", "kartu jawaban2")
        }
        binding.kartujawaban3.setOnClickListener{
            saveHereToCheckLater = kocok[2]
            check()
            Log.e("Main activity","Kartu jawaban3")
        }
        binding.kartujawaban4.setOnClickListener {
            saveHereToCheckLater = kocok[3]
            check()
            Log.e("Main activity", "kartu jawaban4")
        }
    }

}