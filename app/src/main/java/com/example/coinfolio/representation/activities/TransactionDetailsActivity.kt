package com.example.coinfolio.representation.activities

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.coinfolio.CoinFolioApp
import com.example.coinfolio.R
import com.example.coinfolio.data.dto.CryptoCurrencyDTO
import com.example.coinfolio.representation.viewmodels.TransactionDetailsViewModel
import com.example.coinfolio.representation.viewmodels.TransactionDetailsViewModelFactory
import kotlinx.android.synthetic.main.activity_transaction_details.*
import java.math.BigDecimal

class TransactionDetailsActivity : AppCompatActivity() {
    private val viewModel: TransactionDetailsViewModel by lazy {
        val app = application as CoinFolioApp
        val viewModelProviderFactory =
            TransactionDetailsViewModelFactory(
                app,
                intent
            )
        ViewModelProvider(
            this,
            viewModelProviderFactory
        )[TransactionDetailsViewModel::class.java]
    }

    //    private val walletId = SharedPreferencesUtil.readFromSharedPreferences(this, Constants.WALLET_ID)!!
    private val walletId = "1234567890"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()
        setContentView(R.layout.activity_transaction_details)


        val coinListObserver = Observer<List<CryptoCurrencyDTO>> {
            val cryptoCoinStringList = it.map { it.abbreviation }
            val arrayAdapter =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, cryptoCoinStringList)
            spinner_transaction_details.adapter = arrayAdapter
        }

        viewModel.mAllCryptoCurrenciesDTO.observe(this, coinListObserver)

        btn_close_transaction_details.setOnClickListener {
            onBackPressed()
        }

        btn_save_transaction_details.setOnClickListener {
            // validate input
            if (!isInputValid()) {
                Toast.makeText(applicationContext, R.string.CheckInvalidFields, Toast.LENGTH_SHORT)
                    .show()
            }
            viewModel.saveTransaction(
                spinner_transaction_details.selectedItem.toString(),
                edit_amount.text.toString(),
                edit_price.text.toString(),
                walletId
            )
            onBackPressed()
        }

    }

    private fun isInputValid(): Boolean {
        var amountValid = false
        var priceValid = false
        try {
            BigDecimal(edit_amount.text.toString())
            amountValid = true
        } catch (e: Exception) {
            edit_amount.error = "Enter a decimal"
        }

        try {
            BigDecimal(edit_price.text.toString())
            priceValid = true
        } catch (e: Exception) {
            edit_price.error = "Enter a decimal"
        }


        return amountValid && priceValid
    }
}