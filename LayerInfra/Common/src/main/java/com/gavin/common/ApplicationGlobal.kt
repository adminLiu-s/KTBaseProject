package com.gavin.common

import android.content.Context
import dagger.hilt.android.EntryPointAccessors

object ApplicationGlobal {

    lateinit var applicationContext: Context

    val applicationEntryPoint by lazy {
        EntryPointAccessors.fromApplication(applicationContext, ApplicationEntryPoint::class.java)
    }
}