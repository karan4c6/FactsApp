package com.karansyd4.factsappexercise.di

import androidx.lifecycle.ViewModel
import com.karansyd4.factsappexercise.ui.factslist.FactsListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(FactsListViewModel::class)
    abstract fun factsListViewModel(viewModel: FactsListViewModel): ViewModel


}