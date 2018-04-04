package com.nytimes.data.model

import com.google.gson.annotations.SerializedName

class StoriesResponse(
    @SerializedName("results")
    val results: List<StoryResponse>)