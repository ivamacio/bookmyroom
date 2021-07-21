package com.bookmyroom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import com.bookmyroom.ui.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.container, HomeFragment()).commit()
    }
}