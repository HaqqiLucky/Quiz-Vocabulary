package com.liam.quizvocabulary

import android.os.Bundle
import android.util.Log
import android.view.View
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

        viewLalala()
    }



    private fun viewLalala(){
        
        fun gantiSoal(){

            soalke++
            binding.total.text = soalke.toString()
            viewLalala()
        }

        val questionanswer = mapOf( // key to value
            "Blue" to "Biru",
            "Purple" to "Ungu",
            "Green" to "Hijau",
            "Orange" to "Jingga",
            "Yellow" to "Kuning",
            "Pink" to "Merah muda",
            "Black" to "Hitam",
            "White" to "Putih"
        )
        var s : String = ""
        val jawabanrandom = questionanswer.values.random() // ini nanti buat kolom yang ga kepake
        var p = questionanswer.entries.random()
        var q = p.value
        var r = p.key
        val t = jawabanrandom



        fun cek(){


            if (s == q){
                Toast.makeText(this, "Jawaban kau bener", Toast.LENGTH_SHORT).show()
                Log.e("Main activity", "sampe sini")

                right++
                Log.e("MainAct","ini variabel right = {$right}")
                binding.bener.text = right.toString()
                gantiSoal()

            } else if (s == null){
                Toast.makeText(this, "Silahkan jawab soal ini", Toast.LENGTH_SHORT).show()
            } else{
                Toast.makeText(this, "Salah jir", Toast.LENGTH_SHORT).show()
                wrong++
                Log.e("MainAct","ini variabel wrong = {$wrong}")
                binding.salah.text = wrong.toString()
                gantiSoal()
            }
        }



        var kocok = mutableListOf<String>(
            q , t
        )
        kocok.shuffle()

        var aa = kocok[0]
        var bb = kocok[1]

        binding.soal.text = r // harus di .text dlu biar bisa di bawa ke view
        binding.jawaban1.text = aa
        binding.jawaban2.text = bb

        binding.kartujawaban1.setOnClickListener {
            s = aa
            cek()
            Log.e("Main activity", "kartu jawaban1")
        }
        binding.kartujawaban2.setOnClickListener {
            s = bb
            cek()
            Log.e("Main activity", "kartu jawaban2")
        }

    }
}