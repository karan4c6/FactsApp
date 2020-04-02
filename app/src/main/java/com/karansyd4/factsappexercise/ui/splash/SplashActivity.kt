package com.karansyd4.factsappexercise.ui.splash

import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import com.karansyd4.factsappexercise.ui.facts.FactsActivity
import com.karansyd4.factsappexercise.R
import com.karansyd4.factsappexercise.ui.base.BaseActivity
import com.karansyd4.factsappexercise.util.Constants

class SplashActivity : BaseActivity() {

    override val layoutId: Int
        get() = R.layout.activity_splash

    override fun initializeViewModel() {
    }

    /**
     *  To show Splash UI in Full screen
     *  1) Disabled Title
     *  2) Window Flags is set to
     *      WindowManager.LayoutParams.FLAG_FULLSCREEN and
     *      WindowManager.LayoutParams.FLAG_FULLSCREEN
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        super.onCreate(savedInstanceState)
        navigateToHomeScreen()
    }

    private fun navigateToHomeScreen() {
        Handler().postDelayed({
            startActivity(FactsActivity.createIntent(this))
            finish()
        }, Constants.SPLASH_DELAY.toLong())
    }
}
