package com.liam.quizvocabulary

import android.R
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
    private val questionanswerList : MutableMap<String, String> = mutableMapOf(
        "Blue" to "Biru",
        "Purple" to "Ungu",
        "Green" to "Hijau",
        "Orange" to "Jingga",
        "Yellow" to "Kuning",
        "Pink" to "Merah muda",
        "Black" to "Hitam",
        "White" to "Putih"
    )

    private var theAnswer : String = ""
//    private val listWithoutCorrectAnswerInIt  = listOf (
//        questionanswerList.entries.removeIf { it.key == theAnswer }
//        return questionanswerList
//    )


//    fun listWithoutCorrectAnswerInIt(): MutableMap<String, String> {
//        questionanswerList.entries.removeIf { it.value == theAnswer }
//        return questionanswerList                                         ini langsung ke map efeknya langsung di hapus di mapnya langsung, solusinya buat copy dlu baru pake copy itu
//    }

    fun listWithoutCorrectAnswerInIt(): MutableMap<String, String> {
        val copyOfQuestionanswerlist = questionanswerList.toMutableMap()
        copyOfQuestionanswerlist.entries.removeIf { it.value == theAnswer }
        return copyOfQuestionanswerlist
    }

    // tipe data harus sama kalo mau remove, ga tau gpt tadi bilang bs, tp ini bisaa hojfarwifpbfgjihhfe



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Log.e("Main act", "this is not included with corect aswer ${listWithoutCorrectAnswerInIt()} with $theAnswer as correct answer")

        generateQuestion()

    }



    private fun changeQuestion(){
        soalke++
        binding.total.text = soalke.toString()
//        randomQuestionwithAnswer()
        generateQuestion()
    }


    private fun generateQuestion(){
        theAnswer = questionanswerList.values.random() // di atas deklasrasi aja, ga usah di isi
        println("theAnswer = [$theAnswer]")
//        println("values     = ${questionanswerList.values.map { "[$it]" }}")

        var randomAnswerWithoutTrueAnswer = listWithoutCorrectAnswerInIt().values.take(3).shuffled().toMutableList() // harus mutable dulu, soanya kalo list biasa ga bisa di apa apain
        randomAnswerWithoutTrueAnswer.add(theAnswer)
        randomAnswerWithoutTrueAnswer.shuffle()

        Log.e("Main activity", "kocok ambil per index $randomAnswerWithoutTrueAnswer")

//        var questionlala =
//        Log.e("Main activity", "soal: $questionlala")


        binding.soal.text = questionanswerList.entries.first { it.value == theAnswer }.key
        binding.jawaban1.text = randomAnswerWithoutTrueAnswer[0]
        binding.jawaban2.text =randomAnswerWithoutTrueAnswer[1]
        binding.jawaban3.text = randomAnswerWithoutTrueAnswer[2]
        binding.jawaban4.text =randomAnswerWithoutTrueAnswer[3]




        binding.kartujawaban1.setOnClickListener {
            saveHereToCheckLater = randomAnswerWithoutTrueAnswer[0]
            check()
            Log.e("Main activity", "kartu jawaban1")
        }
        binding.kartujawaban2.setOnClickListener {
            saveHereToCheckLater = randomAnswerWithoutTrueAnswer[1]
            check()
            Log.e("Main activity", "kartu jawaban2")
        }
        binding.kartujawaban3.setOnClickListener{
            saveHereToCheckLater = randomAnswerWithoutTrueAnswer[2]
            check()
            Log.e("Main activity","Kartu jawaban3")
        }
        binding.kartujawaban4.setOnClickListener {
            saveHereToCheckLater = randomAnswerWithoutTrueAnswer[3]
            check()
            Log.e("Main activity", "kartu jawaban4")
        }

    }
    private fun check(){
        if (saveHereToCheckLater == theAnswer){
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

}