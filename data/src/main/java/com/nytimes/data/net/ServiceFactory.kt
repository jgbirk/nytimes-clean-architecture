package com.nytimes.data.net

import com.nytimes.data.net.service.StoryService

object ServiceFactory {
    val storyService: StoryService = RetrofitHelper.retrofit.create(StoryService::class.java)
}