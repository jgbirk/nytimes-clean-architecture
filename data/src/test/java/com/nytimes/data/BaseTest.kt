package com.nytimes.data

import com.nytimes.data.model.StoryEntity
import com.nytimes.data.model.StoryResponse

abstract class BaseTest {

    fun makeStoryResponse() =
        StoryResponse(
            "title",
            "summary",
            "url",
            "urlToImage",
            listOf(makeMultimedia()))

    private fun makeMultimedia() =
        StoryResponse.Multimedia("url", 0, 0)

    fun makeStoryEntity() =
        makeStoryEntity(System.currentTimeMillis())

    fun makeStoryEntity(lastUpdated: Long) =
        StoryEntity(
            1,
            "title",
            "summary",
            "url",
            "urlToImage",
            "publishedDate",
            lastUpdated)
}