package com.nytimes.presentation.model

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Parcelable
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.view.View
import android.widget.ImageView
import com.nytimes.presentation.navigation.Navigator
import kotlinx.android.parcel.Parcelize

@Parcelize
class StoryViewModel(
    val title: String,
    val summary: String,
    private val url: String,
    val publishedDate: String,
    val urlToImage: String?): BaseViewModel(), Parcelable {

    fun onClick(image: ImageView, context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val pair = Pair.create(image as View, image.transitionName)

            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity, pair)

            Navigator.navigateTo(Navigator.storyDetailActivity(context, this), options.toBundle())
        } else {
            Navigator.navigateTo(Navigator.storyDetailActivity(context, this))
        }
    }

    fun readMore(context: Context) {
        Navigator.navigateTo(Navigator.webUrl(context, url))
    }
}