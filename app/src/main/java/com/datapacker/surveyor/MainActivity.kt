package com.datapacker.surveyor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.datapacker.surveyor.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, HomeFragment.newInstance())
                .commitNow()
        }


    }
}