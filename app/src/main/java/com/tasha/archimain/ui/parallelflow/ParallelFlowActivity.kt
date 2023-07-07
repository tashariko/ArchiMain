package com.tasha.archimain.ui.parallelflow

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Orientation
import com.tasha.archimain.application.AppConstants
import com.tasha.archimain.data.ApiResult
import com.tasha.archimain.databinding.ActivityParallelflowBinding
import com.tasha.archimain.ui.login.LoginViewModel
import com.tasha.archimain.util.UtilityHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ParallelFlowActivity: AppCompatActivity() {
    val TAG = "ParallelFlowActivity"
    lateinit var binding: ActivityParallelflowBinding
    private val viewModel: ParallelFlowViewModel by viewModels()

    val movieAdapter by lazy{
        ParallelRecyclerViewTemp()
    }

    val tvAdapter by lazy{
        ParallelRecyclerViewTemp()
    }
    companion object {
        fun launchScreen(context: Context) {
            var intent = Intent(context, ParallelFlowActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            context.startActivity(Intent(context, ParallelFlowActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParallelflowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView1.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        binding.recyclerView1.adapter = tvAdapter

        binding.recyclerView2.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        binding.recyclerView2.adapter = movieAdapter

        viewModel.tvTrendingLiveData.observe(this) {
            when (it.status) {
                ApiResult.Status.LOADING -> {
                    Log.i(TAG, "onCreate: loading")
                }
                ApiResult.Status.SUCCESS -> {
                    tvAdapter.swapData(it.data!!)
                }
                ApiResult.Status.ERROR -> if (UtilityHelper.showDataInError()) {
                    Log.i(TAG, "onCreate: error")
                } else {
                    Log.i(TAG, "onCreate: error")
                }
            }
        }

        viewModel.movieTrendingLiveData.observe(this) {
            when (it.status) {
                ApiResult.Status.LOADING -> {
                    Log.i(TAG, "onCreate: loading")
                }
                ApiResult.Status.SUCCESS -> {
                    movieAdapter.swapData(it.data!!)
                }
                ApiResult.Status.ERROR -> if (UtilityHelper.showDataInError()) {
                    Log.i(TAG, "onCreate: error")
                } else {
                    Log.i(TAG, "onCreate: error")
                }
            }
        }

        viewModel.fetchList()
    }
}