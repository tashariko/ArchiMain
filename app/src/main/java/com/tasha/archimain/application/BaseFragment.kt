package com.tasha.archimain.application

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

open abstract class BaseFragment : Fragment() {

    lateinit var mContext: Context
    protected lateinit var rootview: View

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    protected fun showToast(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }


    abstract fun handleIncomingIntent()
    abstract fun bindAndSetupUI()
    abstract fun vmListeners()
    abstract fun viewlisteners()


}