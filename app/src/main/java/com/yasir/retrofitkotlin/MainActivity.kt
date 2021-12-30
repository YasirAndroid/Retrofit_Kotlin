package com.yasir.retrofitkotlin

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.yasir.retrofitkotlin.repo.NetworkClient
import kotlinx.coroutines.*
import retrofit2.awaitResponse
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    lateinit var btnRefresh: ImageButton
    lateinit var tvFact: TextView
    lateinit var tvDate: TextView
    lateinit var progress: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUi()

        btnRefresh.setOnClickListener {
            getData()
        }
    }

    fun getData() {

        progress.show()
        try {
            GlobalScope.launch(Dispatchers.IO) {
                val response = NetworkClient.api.getData().awaitResponse()
                if (response.isSuccessful && response.body() != null) {
                    withContext(Dispatchers.Main){
                        tvFact.text = response.body()!!.text
                        progress.dismiss()
                    }

                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Server Problem",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        } catch (e: Exception) {
            Toast.makeText(
                this@MainActivity,
                "Server Problem",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun initUi() {
        btnRefresh = findViewById(R.id.btn_refresh)
        tvFact = findViewById(R.id.tv_fact)
        tvDate = findViewById(R.id.tv_date)
        progress = ProgressDialog(this)
        progress.setTitle("Getting Fact")
        progress.setMessage("Please wait...")
        getData()
    }
}