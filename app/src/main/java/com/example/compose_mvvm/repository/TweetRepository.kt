package com.example.compose_mvvm.repository

import com.example.compose_mvvm.api.TweetAPI
import com.example.compose_mvvm.models.Tweet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TweetRepository @Inject constructor(private val tweetAPI: TweetAPI) {

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories : StateFlow<List<String>>
        get() = _categories

    private val _tweets = MutableStateFlow<List<Tweet>>(emptyList())
    val tweets : StateFlow<List<Tweet>>
        get() = _tweets

    suspend fun getCategories(){
        val response = tweetAPI.getCategories()
        if(response.isSuccessful && response.body()!= null){
            _categories.emit(response.body()!!)
        }
    }

    suspend fun getTweets(category : String){
        val response = tweetAPI.getTweets("tweets[?( @.category==\"$category\")]")
        if(response.isSuccessful && response.body()!= null){
            _tweets.emit(response.body()!!)
        }
    }
}