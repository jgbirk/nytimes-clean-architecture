package com.nytimes.domain.model

data class Story(
    val title: String,
    val summary: String,
    val url: String,
    val urlToImage: String?,
    val publishedDate: String)