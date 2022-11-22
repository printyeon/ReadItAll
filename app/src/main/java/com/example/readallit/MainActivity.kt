package com.example.readallit

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    //private lateinit var binding = ActivityMainBinding.inflate(layoutInflater)   //lateinit var profileAdapter: ProfileAdapter
    val itemList = mutableListOf<List<Book>>()// 아이템 배열
   // val listAdapter = ProfileAdapter(itemList)     // 어댑터

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_item)




        val retrofit = Retrofit.Builder()
            .baseUrl("https://book.interpark.com")
            .addConverterFactory(
                GsonConverterFactory.create()
            ).build()

        val apiService = retrofit.create(BookAPIService::class.java)

        val apiCallForData = apiService.getBestSellerBooks("45B4DD127DD3DD5D8A37DCB8810027A5266CC3A45E174E40D8E0A6117008717E", "100")
        apiCallForData.enqueue(object : Callback<BestBookSearchResponse> {
            @SuppressLint("StringFormatInvalid")
            override fun onResponse(
                call: Call<BestBookSearchResponse>,
                response: Response<BestBookSearchResponse>
            ) {
                val data = response.body()!!
                Log.d("mytag", data.toString())


                val listView = findViewById<RecyclerView>(R.id.main_list)
                listView.setHasFixedSize(false)
                listView.layoutManager = LinearLayoutManager(this@MainActivity)
                listView.adapter = ProfileAdapter(data.item)







            }


            override fun onFailure(call: Call<BestBookSearchResponse>, t: Throwable) {

            }

        })



    }

}