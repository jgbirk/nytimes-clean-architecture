package com.nytimes.presentation

import com.nytimes.domain.model.Story

open class BaseTest {

    fun makeStory() =
        Story(
            "title",
            "summary",
            "url",
            "urlToImage",
            "publishedDate")
}