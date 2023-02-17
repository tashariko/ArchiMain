package com.tasha.archimain.application

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

open abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

    }

    protected fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun showSnackbar(view: View) {
        //Snackbar.make(view, getString(R.string.generic_error_message), Snackbar.LENGTH_SHORT).show()
    }

    fun closeWithError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    abstract fun handleIncomingIntent()
    abstract fun bindAndSetupUI()
    abstract fun vmListeners()
    abstract fun viewlisteners()
}