package com.example.coinfolio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.coinfolio.data.models.app.CryptoCurrency
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
        val testObserver = Observer<List<CryptoCurrency>> {
            test_textView.text = it?.get(0)?.name

        }

        viewModel.mAllCryptoCurrencies.observe(this, testObserver)

        btn_test.setOnClickListener {
           var test = 10
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