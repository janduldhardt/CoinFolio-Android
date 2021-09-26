package com.example.coinfolio.representation.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coinfolio.CoinFolioApp
import com.example.coinfolio.R
import com.example.coinfolio.data.dto.CryptoCurrencyDTO
import com.example.coinfolio.data.relation.WalletWithTransactions
import com.example.coinfolio.representation.adapter.TrackCoinListAdapter
import com.example.coinfolio.representation.adapter.WalletCoinListAdapter
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

    private lateinit var mCoinListAdapter: WalletCoinListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet)

        val rv = findViewById<RecyclerView>(R.id.RecyclerView_activity_wallet)
        rv.layoutManager = LinearLayoutManager(this)

        val coinListObserver = Observer<WalletWithTransactions> {
            mCoinListAdapter = WalletCoinListAdapter(it.getCryptoCurrencies()) {}
            rv.adapter = mCoinListAdapter
        }

        viewModel.mAllCryptoCurrenciesDTO.observe(this, coinListObserver)

        button_test1.setOnClickListener {
            viewModel.retrieveTestWallet()
        }

        trackButton.setOnClickListener {
            onBackPressed()
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