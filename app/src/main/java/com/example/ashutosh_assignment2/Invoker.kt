package com.example.ashutosh_assignment2

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class Invoker : AppCompatActivity() {

    lateinit var adapter: NewsAdapter
    lateinit var recylclerview : RecyclerView

    fun getNews() {
        val news: Call<News> = NewsService.newsInstance.getHeadlines("in",1)
        news.enqueue(object: Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {

                val news : News? = response.body()
                if(news!=null){
                    adapter=NewsAdapter(this@Invoker,news.articles)
                    recylclerview=findViewById(R.id.NewsList);
                    recylclerview.adapter=adapter;
                    recylclerview.layoutManager = LinearLayoutManager(this@Invoker)
                    Log.d("Done", "onResponse: No Error ")
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("Done", "onResponse:  Error fetching"+t.message.toString())
            }
        })
    }
}