package com.nytimes.data.net.service

import com.nytimes.data.model.StoriesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface StoryService {

    @GET("topstories/v2/home.json")
    fun list(@Query("api_key") apiKey: String): Observable<StoriesResponse>
}