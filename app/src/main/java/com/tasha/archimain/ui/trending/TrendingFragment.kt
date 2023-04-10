package com.tasha.archimain.ui.trending

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tasha.archimain.R
import com.tasha.archimain.application.BaseFragment
import com.tasha.archimain.databinding.FragmentTrendingBinding
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.tasha.archimain.application.AppConstants
import com.tasha.archimain.data.ApiResult
import com.tasha.archimain.data.source.local.entity.TrendingItem
import com.tasha.archimain.ui.SetMainTitle
import com.tasha.archimain.util.UtilityHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TrendingFragment @Inject constructor() : BaseFragment() {
    lateinit var binding: FragmentTrendingBinding
    lateinit var delegate: SetMainTitle

    private val viewModel: TrendingViewModel by viewModels()

    private val adapter by lazy{
        TrendingAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrendingBinding.inflate(inflater, container, false)
        rootview = binding.root
        bindAndSetupUI()
        vmListeners()
        return rootview
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            delegate = context as SetMainTitle
        } catch (e: ClassCastException) {
            throw ClassCastException(activity.toString() + " must implement SetMainTitle")
        }
    }

    override fun handleIncomingIntent() {
        TODO("Not yet implemented")
    }

    override fun bindAndSetupUI() {
        delegate.setTitle(getString(R.string.fragment_trending_title))
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.adapter = adapter
        binding.errorLoadingContainerView.addDataView(binding.swipeRefreshLayout, javaClass.simpleName)
    }

    override fun vmListeners() {
        viewModel.trendingListLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                ApiResult.Status.LOADING -> {
                    if (it.data == null || it.data.isNotEmpty()) {
                        configureView(AppConstants.LOADING_LAYOUT, AppConstants.VIEW_FROM_LOADING)
                    } else {
                        configureView(AppConstants.DATA_LAYOUT, AppConstants.VIEW_FROM_LOADING)
                        updateUI(it.data)
                    }
                }
                ApiResult.Status.SUCCESS -> {
                    updateUI(it.data!!)

                    configureView(AppConstants.DATA_LAYOUT, AppConstants.VIEW_FROM_API)
                }
                ApiResult.Status.ERROR -> if (UtilityHelper.showDataInError()) {
                    if (it.data == null) {
                        configureView(AppConstants.ERROR_LAYOUT, AppConstants.VIEW_FROM_ERROR)
                    } else {
                        configureView(AppConstants.DATA_LAYOUT, AppConstants.VIEW_FROM_ERROR)
                        updateUI(it.data)
                    }
                } else {
                    configureView(AppConstants.ERROR_LAYOUT, AppConstants.VIEW_FROM_ERROR)
                }
            }
        }

        viewModel.fetchTrendingList()
    }

    override fun viewlisteners() {
        TODO("Not yet implemented")
    }

    private fun configureView(errorLayout: String, attribute: String) {
        binding.emptyView.visibility = View.GONE
        when (errorLayout) {
            AppConstants.NO_DATA_LAYOUT -> {
                binding.emptyView.visibility = View.VISIBLE
                binding.errorLoadingContainerView.hideView()
            }
            AppConstants.ERROR_LAYOUT -> {
                binding.errorLoadingContainerView.showDefaultErrorView(attribute)
            }
            AppConstants.LOADING_LAYOUT -> {
                binding.errorLoadingContainerView.showDefaultLoadingView(attribute)
            }
            AppConstants.DATA_LAYOUT -> {
                binding.errorLoadingContainerView.showDataView(attribute)
            }
        }
    }

    private fun updateUI(data: ArrayList<TrendingItem>) {
        adapter.updateData(data)
    }
}