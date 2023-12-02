package com.example.compose_mvvm.api

import com.example.compose_mvvm.models.Tweet
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface TweetAPI {

    @GET("v3/b/656242cf0574da7622cbe919?meta=false")
    suspend fun getTweets(@Header("X-JSON-Path")category: String) : Response<List<Tweet>>

    @GET("v3/b/656242cf0574da7622cbe919?meta=false")
    @Headers("X-JSON-Path:tweets..category")
    suspend fun getCategories() : Response<List<String>>
}