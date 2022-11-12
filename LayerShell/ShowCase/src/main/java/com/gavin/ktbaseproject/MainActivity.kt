package com.gavin.ktbaseproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gavin.common.utils.showToast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        "凯撒给".showToast()
    }
}