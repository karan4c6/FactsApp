package com.karansyd4.newsappexercise

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.karansyd4.newsappexercise.ui.base.BaseActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : BaseActivity(), HasSupportFragmentInjector {

    companion object {

        @JvmStatic
        fun createIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector
    override val layoutId: Int
        get() = R.layout.activity_main

    override fun initializeViewModel() {
    }
}
