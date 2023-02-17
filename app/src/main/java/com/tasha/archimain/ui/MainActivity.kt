package com.tasha.archimain.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.tasha.archimain.R
import com.tasha.archimain.application.BaseActivity
import com.tasha.archimain.databinding.ActivityMainBinding
import com.tasha.archimain.ui.trending.TrendingFragment
import com.tasha.archimain.ui.user.UserFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity(), SetMainTitle {

    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var trendingFragment: TrendingFragment

    @Inject
    lateinit var userFragment: UserFragment

    companion object {
        fun launchScreen(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
            (context as BaseActivity).finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarView)
        handleIncomingIntent()
        bindAndSetupUI()
        vmListeners()
        viewlisteners()
    }

    override fun handleIncomingIntent() {

    }

    override fun bindAndSetupUI() {

        trendingFragment = TrendingFragment()
        userFragment = UserFragment()

        setCurrentFragment(trendingFragment)

        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navigation_trending->setCurrentFragment(trendingFragment)
                R.id.navigation_user->setCurrentFragment(userFragment)

            }
            true
        }
    }

    override fun vmListeners() {
        binding.changeThemeView.setOnClickListener {

        }
    }

    override fun viewlisteners() {

    }

    override fun setTitle(title: String) {
        binding.titleView.text = title
    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer,fragment)
            commit()
        }

}


interface SetMainTitle {
    fun setTitle(title: String)
}
