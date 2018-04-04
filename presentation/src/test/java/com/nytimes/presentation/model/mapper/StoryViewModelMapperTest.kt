package com.nytimes.presentation.model.mapper

import com.nytimes.presentation.BaseTest
import com.nytimes.presentation.model.StoryViewModel
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.instanceOf
import org.hamcrest.Matchers.`is` as _is
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class StoryViewModelMapperTest : BaseTest() {

    private val storyViewModelMapper = StoryViewModelMapper

    @Test
    fun testTransformStoryViewModel() {
        val story = makeStory()

        val storyViewModel = storyViewModelMapper.transform(story)

        assertThat(storyViewModel, _is(instanceOf(StoryViewModel::class.java)))
        assertThat(storyViewModel.title, _is(story.title))
    }
}