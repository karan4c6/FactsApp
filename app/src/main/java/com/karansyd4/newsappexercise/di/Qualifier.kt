package com.karansyd4.newsappexercise.di

import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class NewAPI

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class CoroutineScropeIO