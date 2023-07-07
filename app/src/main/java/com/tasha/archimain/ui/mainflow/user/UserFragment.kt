package com.tasha.archimain.ui.mainflow.user

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.window.SplashScreen
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.tasha.archimain.R
import com.tasha.archimain.application.BaseFragment
import com.tasha.archimain.databinding.FragmentUserBinding
import com.tasha.archimain.ui.SetMainTitle
import com.tasha.archimain.ui.splash.SplashActivity
import com.tasha.archimain.util.SharedPreferenceHelper
import javax.inject.Inject


class UserFragment @Inject constructor() : BaseFragment() {

    lateinit var binding: FragmentUserBinding
    lateinit var delegate: SetMainTitle

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        rootview = binding.root
        bindAndSetupUI()

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
        delegate.setTitle(getString(R.string.fragment_user_title))
        binding.logoutButton.setOnClickListener {
            MaterialAlertDialogBuilder(context!!)
                .setTitle(getString(R.string.label_logout))
                .setMessage(getString(R.string.text_want_to_log_out))
                .setPositiveButton(
                    getString(R.string.label_yes)
                ) { dialogInterface, i ->
                    SharedPreferenceHelper.clearAll(context!!)
                    SplashActivity.relaunchApp(context!!)
                }
                .setNegativeButton(
                    getString(R.string.label_cancel)
                ) { dialogInterface, i ->

                }
                .show()
        }
    }

    override fun vmListeners() {
        TODO("Not yet implemented")
    }

    override fun viewlisteners() {
        TODO("Not yet implemented")
    }

}