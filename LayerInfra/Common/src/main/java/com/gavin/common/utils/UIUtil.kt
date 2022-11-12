package com.gavin.common.utils

import android.os.Looper
import android.view.View
import android.widget.Toast
import com.gavin.common.ApplicationGlobal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun CharSequence.showToast() {
    if (Thread.currentThread() == Looper.getMainLooper().thread) {
        Toast.makeText(ApplicationGlobal.applicationContext, this, Toast.LENGTH_SHORT).show()
    } else {
        ApplicationGlobal.applicationEntryPoint.applicationScope().launch(Dispatchers.Main) {
            Toast.makeText(
                ApplicationGlobal.applicationContext,
                this@showToast,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

fun CharSequence.showToastLong() {
    if (Thread.currentThread() == Looper.getMainLooper().thread) {
        Toast.makeText(ApplicationGlobal.applicationContext, this, Toast.LENGTH_SHORT).show()
    } else {
        ApplicationGlobal.applicationEntryPoint.applicationScope().launch(Dispatchers.Main) {
            Toast.makeText(
                ApplicationGlobal.applicationContext,
                this@showToastLong,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

