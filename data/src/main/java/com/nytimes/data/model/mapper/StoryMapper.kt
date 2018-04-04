package com.nytimes.data.model.mapper

import com.nytimes.data.model.StoriesResponse
import com.nytimes.data.model.StoryEntity
import com.nytimes.data.model.StoryResponse
import com.nytimes.domain.model.Story

object StoryMapper {

    fun transform(entity: StoryEntity) =
        Story(entity.title, entity.summary, entity.url, entity.urlToImage, entity.publishedDate)

    fun transform(response: StoryResponse) =
        StoryEntity(
            title = response.title,
            summary = response.summary,
            url = response.url,
            urlToImage = response.getUrlToImage(),
            publishedDate = response.publishedDate,
            lastUpdated = System.currentTimeMillis())

    fun transform(entities: List<StoryEntity>): List<Story> {
        val stories = ArrayList<Story>()

        entities.forEach {
            stories.add(transform(it))
        }

        return stories
    }

    fun transform(response: StoriesResponse): List<StoryEntity> {
        val entities = ArrayList<StoryEntity>()

        response.results.forEach {
            entities.add(transform(it))
        }

        return entities
    }
}