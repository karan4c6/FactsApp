package com.karansyd4.newsappexercise

import android.app.Activity
import androidx.multidex.MultiDexApplication
import com.facebook.stetho.Stetho
import com.karansyd4.newsappexercise.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import java.lang.ref.WeakReference
import javax.inject.Inject

class App : MultiDexApplication(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        app = WeakReference(this)

        when {
            BuildConfig.DEBUG -> Stetho.initializeWithDefaults(this)
        }

        DaggerAppComponent.builder().application(this).build().inject(this)

    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector

    companion object {
        private lateinit var app: WeakReference<App>
        fun getInstance(): App? {
            return app.get()
        }
    }
}