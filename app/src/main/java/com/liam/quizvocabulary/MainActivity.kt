package com.liam.quizvocabulary

import android.R
import android.os.Bundle
import android.support.v4.app.INotificationSideChannel
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.liam.quizvocabulary.databinding.ActivityMainBinding
import java.lang.invoke.WrongMethodTypeException
import kotlin.collections.get

//Good code = kode yang mudah dibaca, mudah dipelihara, bersih, jelas, aman, dan efisien.
// Create a QuizManager Class. This class will hold the question list, manage the score, pick the next question, and decide if the quiz is over

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
//    private var saveHereToCheckLater : String = ""
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
//        return questionanswerList   ini langsung ke map efeknya langsung di hapus di mapnya langsung, solusinya buat copy dlu baru pake copy itu
//    }

    fun listWithoutCorrectAnswerInIt(): MutableMap<String, String> {
        val copyOfQuestionanswerlist = questionanswerList.toMutableMap()
        copyOfQuestionanswerlist.entries.removeIf { it.value == theAnswer }
        return copyOfQuestionanswerlist
    }

    // tipe data harus sama kalo mau remove, ga tau gpt tadi bilang bs, tp ini bisaa hojfarwifpbfgjihhfe

    private fun generateQuestion(): MutableList<String> {
        theAnswer = questionanswerList.values.random()
        var randomAnswerWithoutTrueAnswer = listWithoutCorrectAnswerInIt().values.take(3).toMutableList() // harus mutable dulu, soanya kalo list biasa ga bisa di apa apain
        randomAnswerWithoutTrueAnswer.add(theAnswer)
        randomAnswerWithoutTrueAnswer.shuffled()
//        val randomAnswerWithTrueAnswer = mutableListOf<String>(randomAnswerWithoutTrueAnswer
        Log.e("Main act", "this is not included with corect aswer ${listWithoutCorrectAnswerInIt()} with $theAnswer as correct answer")
        Log.e("Main activity", "kocok ambil per index $randomAnswerWithoutTrueAnswer")
        return (randomAnswerWithoutTrueAnswer)
//        binding.jawaban1.text = randomAnswerWithoutTrueAnswer[1]
//        binding.jawaban2.text = randomAnswerWithoutTrueAnswer[0]
//        binding.jawaban3.text = randomAnswerWithoutTrueAnswer[1]
//        binding.jawaban4.text = randomAnswerWithoutTrueAnswer[1]

    }


    private var rightbener = 0
    private var wrong = 0
    private var soalke = 0



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


        updateUI()
        var listOfAnswer : List<String> = generateQuestion().toList()

//        val listOfAnswer = generateQuestion().toList()
        Log.e("main activity", "ini adalah list answr yg udah naik ke on create $listOfAnswer")

//        binding.bener.text = right.toString()
//        binding.salah.text = wrong.toString()
//        binding.total.text = soalke.toString()

        binding.soal.text = questionanswerList.entries.first { it.value == theAnswer }.key
        // pake apply bisa langsung setText sama setonclick
        binding.jawaban1.apply {
            text = listOfAnswer[0]
            setOnClickListener {
                check(text.toString())
                Log.e("Main activity", "kartu jawaban1")
            }
        }
        binding.jawaban2.apply {
            text = listOfAnswer[1]
            setOnClickListener {
                check(text.toString())
                Log.e("Main activity", "kartu jawaban2")
            }
        }
        binding.jawaban3.apply {
            text = listOfAnswer[2]
            setOnClickListener {
                check(text.toString())
                Log.e("Main activity", "kartu jawaban3")
            }
        }
        binding.jawaban4.apply {
            text = listOfAnswer[3]
            setOnClickListener {
                check(text.toString())
                Log.e("Main activity", "kartu jawaban4")
            }
        }
        binding.restart.apply {  // jangan lupa habis variabel di set ke 0 di update lagi bindingnya
            setOnClickListener {
                rightbener = 0
                wrong = 0
                soalke = 0
                updateUI()

            }
        }
    }

//    private fun restart(right: Int, wrong: Int, soalKe : Int){
//        right = 0
//        wrong = 0
//        soalke = 1
//    }

    // harus buat yang pertanyaan seterusnya selain di update. jadi update view nya manual, contoh variabel listofAnswer itu sebenernya berubah, cuma emg belum di update viewnua
    // view nya harus di update juga dong hehe
    private fun updateUI() {
        val listOfAnswer = generateQuestion()
        listOfAnswer.shuffle()
        Log.e("main activity", "list shuffler dr fun $listOfAnswer")
        binding.soal.text = questionanswerList.entries.first { it.value == theAnswer }.key
        binding.jawaban1.text = listOfAnswer[0]
        binding.jawaban2.text = listOfAnswer[1]
        binding.jawaban3.text = listOfAnswer[2]
        binding.jawaban4.text = listOfAnswer[3]

        binding.total.text = soalke.toString()
        binding.bener.text = rightbener.toString()
        binding.salah.text = wrong.toString()



        Log.e("Main activtiy","mr cheese face the camera, right: $rightbener, wrong: $wrong, soalke: $soalke")
    }

    private fun check(jawabanUser: String){
        if (jawabanUser == theAnswer){
            Toast.makeText(this, "Jawaban kau bener", Toast.LENGTH_SHORT).show()
            Log.e("Main activity", "sampe sini")
            rightbener++
            Log.e("MainAct","ini variabel right = {$rightbener}")
            binding.bener.text = rightbener.toString()

        } else if (jawabanUser == null){
            Toast.makeText(this, "Silahkan jawab soal ini", Toast.LENGTH_SHORT).show()
        } else{
            Toast.makeText(this, "Salah jir", Toast.LENGTH_SHORT).show()
            wrong++
            Log.e("MainAct","ini variabel wrong = {$wrong}")
            binding.salah.text = wrong.toString()
        }
        soalke++
        binding.total.text = soalke.toString()
        generateQuestion()
        updateUI()
    }
}

// bedakan shuffle sama shuffled