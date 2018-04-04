package com.nytimes.presentation.model

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.nytimes.R
import com.nytimes.util.CircleTransform

abstract class BaseViewModel {

    companion object {
        @JvmStatic
        @BindingAdapter(value = ["url", "circled"], requireAll = false)
        fun loadImage(view: ImageView, url: String?, circled: Boolean) {
            url?.let {
                var picasso = Picasso.with(view.context)
                    .load(it)

                picasso = if (circled) {
                    picasso.placeholder(R.drawable.circle)
                        .error(R.drawable.circle)
                        .transform(CircleTransform())
                } else {
                    picasso.placeholder(R.drawable.square)
                        .error(R.drawable.square)
                }

                picasso.into(view)
            }
        }
    }
}