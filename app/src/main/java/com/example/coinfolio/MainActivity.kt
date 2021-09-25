package com.example.coinfolio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val viewModel: CreateCryptoCurrencyViewModel by lazy {
        val app = application as CoinFolioApp
        val viewModelProviderFactory =
            CreateCryptoCurrencyViewModelProviderFactory(
                app,
                intent
            )
        ViewModelProvider(
            this,
            viewModelProviderFactory
        )[CreateCryptoCurrencyViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val reminderEditText: EditText = findViewById(R.id.reminderEditTextView)
//        val createReminderButton: Button = findViewById(R.id.createReminderButton)

        btn_test.setOnClickListener {
            viewModel.loadCryptoCurrencies()
        }
    }

//    private fun createReminder(text: String) {
//        if (text.isEmpty()) {
//            showToast(message = "Reminder text field is empty")
//        } else {
//            viewModel.createReminder(text = text)
//        }
//    }
//
//    private fun showToast(message: String) {
//        ...
//    }

}