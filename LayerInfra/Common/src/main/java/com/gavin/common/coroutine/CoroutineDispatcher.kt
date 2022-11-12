package com.gavin.common.coroutine

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class UnconfinedDispatcher

@InstallIn(SingletonComponent::class)
@Module
internal object CoroutineDispatcherModule {

    @DefaultDispatcher
    @Provides
    fun providerDefaultDispatcher() : CoroutineDispatcher = Dispatchers.Default

    @IoDispatcher
    @Provides
    fun providerIoDispatcher() : CoroutineDispatcher = Dispatchers.IO

    @MainDispatcher
    @Provides
    fun providerMainDispatcher() : CoroutineDispatcher = Dispatchers.Main

    @UnconfinedDispatcher
    @Provides
    fun providerUnconfinedDispatcher() : CoroutineDispatcher = Dispatchers.Unconfined

}