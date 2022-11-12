package com.gavin.common

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal class CommonHiltModule {

    @Singleton
    @Provides
    fun gson() : Gson = GsonBuilder().create()

}