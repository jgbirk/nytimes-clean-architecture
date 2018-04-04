package com.nytimes.data.model

import com.google.gson.annotations.SerializedName

class StoryResponse(
    @SerializedName("title")
    val title: String,
    @SerializedName("abstract")
    val summary: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("published_date")
    val publishedDate: String,
    @SerializedName("multimedia")
    private val multimedia: List<Multimedia>) {

    class Multimedia(
        @SerializedName("url")
        val url: String,
        @SerializedName("width")
        val width: Int = 0,
        @SerializedName("height")
        val height: Int = 0
    )

    fun getUrlToImage(): String? {
        // Search for a good size image (height = 140 and width = 210)
        val filtered = multimedia.filter { it.height == 140 && it.width == 210 }

        return if (filtered.isNotEmpty()) filtered[0].url else null
    }
}