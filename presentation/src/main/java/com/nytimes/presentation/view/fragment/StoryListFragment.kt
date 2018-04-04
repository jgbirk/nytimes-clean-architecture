package com.nytimes.presentation.view.fragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nytimes.R
import com.nytimes.databinding.FragmentListStoriesBinding
import com.nytimes.presentation.presenter.BaseFragment
import com.nytimes.presentation.model.StoryViewModel
import com.nytimes.presentation.view.adapter.StoryAdapter

class StoryListFragment:
    BaseFragment<StoryListContract.View, StoryListContract.Presenter>(), StoryListContract.View {

    override var presenter: StoryListContract.Presenter = StoryListPresenter()
    private lateinit var binding: FragmentListStoriesBinding
    private lateinit var adapter: StoryAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private var firstVisibleItemPosition = 0
    private var snackbar: Snackbar? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        firstVisibleItemPosition = savedInstanceState?.getInt("firstVisibleItemPosition") ?: 0

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_stories, container, false)

        binding.swipe.setOnRefreshListener {
            snackbar?.dismiss()
            firstVisibleItemPosition = 0
            presenter.loadStories(true)
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = StoryAdapter(context)
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        with(binding.storyList) {
            adapter = this@StoryListFragment.adapter
            layoutManager = this@StoryListFragment.layoutManager
        }

        presenter.loadStories()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("firstVisibleItemPosition", layoutManager.findFirstVisibleItemPosition())
    }

    override fun showError(message: String) {
        binding.swipe.isRefreshing = false

        snackbar = Snackbar.make(
            binding.root,
            message,
            Snackbar.LENGTH_INDEFINITE)
                .setAction(
                    R.string.retry,
                    { presenter.loadStories() })

        snackbar!!.show()
    }

    override fun showStories(stories: List<StoryViewModel>) {
        adapter.stories = stories
        adapter.notifyDataSetChanged()

        binding.storyList.scrollToPosition(firstVisibleItemPosition)
        binding.swipe.isRefreshing = false
    }

    override fun showLoading() {
        binding.swipe.isRefreshing = true
    }

    override fun hideLoading() {
        binding.swipe.isRefreshing = false
    }
}