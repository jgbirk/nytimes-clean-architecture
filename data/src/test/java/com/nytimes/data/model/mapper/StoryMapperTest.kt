package com.nytimes.data.model.mapper

import com.nytimes.data.BaseTest
import com.nytimes.data.model.StoryEntity
import com.nytimes.domain.model.Story
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is` as _is
import org.hamcrest.Matchers.instanceOf
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class StoryMapperTest : BaseTest() {

    private val storyMapper = StoryMapper

    @Test
    fun testTransformStoryEntity() {
        val storyEntity = makeStoryEntity()

        val story = storyMapper.transform(storyEntity)

        assertThat(story, _is(instanceOf(Story::class.java)))
        assertThat(story.title, _is(storyEntity.title))
    }

    @Test
    fun testTransformStoryResponse() {
        val response = makeStoryResponse()

        val storyEntity = storyMapper.transform(response)

        assertThat(storyEntity, _is(instanceOf(StoryEntity::class.java)))
        assertThat(storyEntity.title, _is(response.title))
    }
}