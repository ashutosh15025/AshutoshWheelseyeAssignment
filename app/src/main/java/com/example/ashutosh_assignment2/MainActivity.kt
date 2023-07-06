package com.example.ashutosh_assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var adapter: NewsAdapter
    lateinit var recylclerview :RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       getNews()
    }

    private fun getNews() {
        val news: Call<News> = NewsService.newsInstance.getHeadlines("in",1)
        news.enqueue(object: Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {

                val news : News? = response.body()
                if(news!=null){
                   adapter=NewsAdapter(this@MainActivity,news.articles)
                    recylclerview=findViewById(R.id.NewsList);
                    recylclerview.adapter=adapter;
                    recylclerview.layoutManager = LinearLayoutManager(this@MainActivity)
                    Log.d("Done", "onResponse: No Error ")
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("Done", "onResponse:  Error fetching"+t.message.toString())
            }
        })
    }
}