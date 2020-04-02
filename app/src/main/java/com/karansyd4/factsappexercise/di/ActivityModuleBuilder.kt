package com.karansyd4.factsappexercise.di

import com.karansyd4.factsappexercise.ui.facts.FactsActivity
import com.karansyd4.factsappexercise.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityModuleBuilder {

    @ContributesAndroidInjector
    abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): FactsActivity
}