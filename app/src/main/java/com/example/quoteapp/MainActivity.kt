package com.example.quoteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var recycler: RecyclerView
    lateinit var adapterQuote: AdapterClass
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler = findViewById(R.id.recylerView)

        val builder = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = builder.getQuotes()
        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                var responseBody = response.body()
                var quotesList = responseBody?.quotes!!

                adapterQuote = AdapterClass(this@MainActivity, quotesList)
                recycler.adapter = adapterQuote
                recycler.layoutManager = LinearLayoutManager(this@MainActivity)

            }

            override fun onFailure(call: Call<MyData?>, t: Throwable) {
                Log.d("Main Activity", "onFailure"+ t.message)
            }
        })
    }
}