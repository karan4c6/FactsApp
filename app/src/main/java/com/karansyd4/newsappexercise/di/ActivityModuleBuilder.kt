package com.karansyd4.newsappexercise.di

import com.karansyd4.newsappexercise.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityModuleBuilder {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}