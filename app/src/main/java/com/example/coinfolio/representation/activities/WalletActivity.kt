package com.example.coinfolio.representation.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.coinfolio.CoinFolioApp
import com.example.coinfolio.R
import com.example.coinfolio.representation.viewmodels.TopCryptoCurrenciesViewModel
import com.example.coinfolio.representation.viewmodels.TopCryptoCurrenciesViewModelProviderFactory
import com.example.coinfolio.representation.viewmodels.WalletViewModel
import com.example.coinfolio.representation.viewmodels.WalletViewModelFactory
import kotlinx.android.synthetic.main.activity_wallet.*

class WalletActivity : AppCompatActivity() {

    private val viewModel: WalletViewModel by lazy {
        val app = application as CoinFolioApp
        val viewModelProviderFactory =
            WalletViewModelFactory(
                app,
                intent
            )
        ViewModelProvider(
            this,
            viewModelProviderFactory
        )[WalletViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet)


        button_test1.setOnClickListener {
            viewModel.retrieveTestWallet()
        }

        trackButton.setOnClickListener {
            viewModel.createTestWallet()
        }

        addButton.setOnClickListener {
            openTransactionDetails()
        }

    }

    private fun openTransactionDetails() {
        val intent = Intent(this, TransactionDetailsActivity::class.java)
        startActivity(intent)
    }
}