package com.nytimes.presentation.view.activity

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import com.nytimes.R
import com.nytimes.databinding.ActivityMainBinding
import com.nytimes.presentation.view.fragment.StoryListFragment
import com.nytimes.presentation.presenter.BaseActivity

class MainActivity: BaseActivity<MainContract.View, MainContract.Presenter>(),
    MainContract.View {

    override var presenter: MainContract.Presenter =
        MainPresenter()

    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: ScreenSlidePagerAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        pagerAdapter = ScreenSlidePagerAdapter(
            supportFragmentManager,
            this
        )

        viewPager = binding.viewPager
        viewPager.adapter = pagerAdapter
    }

    private class ScreenSlidePagerAdapter(fm: FragmentManager, val context: Context): FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> StoryListFragment()
                else -> throw IllegalArgumentException("Invalid position")
            }
        }

        override fun getPageTitle(position: Int): CharSequence {
            return when (position) {
                0 -> context.getString(R.string.top_stories)
                else -> throw IllegalArgumentException("Invalid position")
            }
        }

        override fun getCount(): Int = 1
    }
}