package com.gavin.common.utils

import com.gavin.common.ApplicationGlobal

fun getVersionCode(): Long {
    return runCatching {
        ApplicationGlobal.applicationContext.run {
            packageManager.getPackageInfo(packageName, 0).longVersionCode
        }
    }.onFailure {
        it.printStackTrace()
    }.getOrDefault(0)
}

fun getVersionName(): String {
    return runCatching {
        ApplicationGlobal.applicationContext.run {
            packageManager.getPackageInfo(packageName, 0).versionName
        }
    }.onFailure {
        it.printStackTrace()
    }.getOrDefault("")
}

fun getPackageName(): String {
    return ApplicationGlobal.applicationContext.packageName
}