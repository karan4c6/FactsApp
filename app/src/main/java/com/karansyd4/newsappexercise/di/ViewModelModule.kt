package com.karansyd4.newsappexercise.di

import androidx.lifecycle.ViewModel
import com.karansyd4.newsappexercise.ui.newslist.NewsListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewsListViewModel::class)
    abstract fun newsListViewModel(viewModel: NewsListViewModel): ViewModel


}