package com.example.ashutosh_assignment2

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
const val Baseurl="https://newsapi.org/"
const val API_KEY="da64718875b0432ca41f6ddca120d43c"

interface NewsInterface {

        @GET("v2/top-headlines?apiKey=$API_KEY")
        fun getHeadlines(@Query("country")country : String, @Query("page")page : Int): Call<News>
        //https://newsapi.org/v2/top-headlines?apiKey=&API_KEY&country=in&page=1

    }
    object NewsService{
        val newsInstance:NewsInterface
        init {
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(Baseurl)
                .addConverterFactory(GsonConverterFactory.create()).build()
            newsInstance = retrofit.create(NewsInterface::class.java)
        }
    }
