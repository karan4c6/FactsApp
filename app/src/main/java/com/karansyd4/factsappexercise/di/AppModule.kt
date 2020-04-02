package com.karansyd4.factsappexercise.di

import android.app.Application
import com.karansyd4.factsappexercise.data.local.AppDatabase
import com.karansyd4.factsappexercise.data.remote.FactsService
import com.karansyd4.factsappexercise.data.remote.datasource.FactsDataSource
import com.karansyd4.factsappexercise.util.Constants
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module(includes = [ViewModelModule::class, CoreDataModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideNewsService(@NewAPI okhttpClient: OkHttpClient,
                           converterFactory: GsonConverterFactory
    ) = provideService(okhttpClient, converterFactory, FactsService::class.java)

    @Singleton
    @Provides
    fun provideRemoteDataSource(factsService: FactsService)
            = FactsDataSource(factsService)

    @Provides
    @Singleton
    fun provideCoroutineContext(): CoroutineContext {
        return Dispatchers.Main
    }

    @CoroutineScropeIO
    @Provides
    fun provideCoroutineScopeIO() = CoroutineScope(Dispatchers.IO)

    @NewAPI
    @Provides
    fun providePrivateOkHttpClient(
        upstreamClient: OkHttpClient
    ): OkHttpClient {
        return upstreamClient.newBuilder().build()
    }

    @Singleton
    @Provides
    fun provideDb(app: Application) = AppDatabase.getInstance(app)

    @Singleton
    @Provides
    fun provideNewsDao(db: AppDatabase) = db.factsDao()

    private fun createRetrofit(
        okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okhttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    private fun <T> provideService(okhttpClient: OkHttpClient,
                                   converterFactory: GsonConverterFactory, factsClass: Class<T>): T {
        return createRetrofit(okhttpClient, converterFactory).create(factsClass)
    }
}