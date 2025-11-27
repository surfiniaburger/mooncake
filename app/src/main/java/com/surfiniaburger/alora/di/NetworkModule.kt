package com.surfiniaburger.alora.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.sse.EventSource
import okhttp3.sse.EventSources
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(0, TimeUnit.MILLISECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideEventSourceFactory(client: OkHttpClient): EventSource.Factory {
        return EventSources.createFactory(client)
    }

    @Provides
    @Singleton
    fun provideNetworkMonitor(@dagger.hilt.android.qualifiers.ApplicationContext context: android.content.Context): com.surfiniaburger.alora.utils.NetworkMonitor {
        return com.surfiniaburger.alora.utils.NetworkMonitor(context)
    }
}
