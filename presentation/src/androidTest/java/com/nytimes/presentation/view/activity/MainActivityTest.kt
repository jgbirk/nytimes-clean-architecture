package com.nytimes.presentation.view.activity

import android.support.test.rule.ActivityTestRule

import com.nytimes.R

import org.junit.Rule
import org.junit.Test

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.swipeDown
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.v7.widget.RecyclerView
import com.nytimes.presentation.view.EspressoTestRunner
import org.junit.AfterClass
import org.junit.BeforeClass
import org.hamcrest.Matchers.`is` as _is

class MainActivityTest : EspressoTestRunner() {

    companion object {
        @BeforeClass
        @JvmStatic
        fun setUpClass() {
            Intents.init()
        }

        @AfterClass
        @JvmStatic
        fun tearDownClass() {
            Intents.release()
        }
    }

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testSwipeToRefresh() {
        onView(withId(R.id.swipe)).perform(swipeDown())
    }

    @Test
    fun testStoryListClick() {
        val recyclerView = onView(withId(R.id.story_list))

        recyclerView.perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        intended(hasComponent(StoryDetailActivity::class.java.name))
    }
}
