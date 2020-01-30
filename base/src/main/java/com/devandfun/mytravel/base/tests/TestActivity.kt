package com.devandfun.mytravel.base.tests

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.devandfun.mytravel.base.R

class TestActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_layout)
    }
}