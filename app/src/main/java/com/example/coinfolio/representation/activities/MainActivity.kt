package com.example.coinfolio.representation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.coinfolio.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(TopCryptoCurrenciesActivity())

    }
    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .commit()
    }
}