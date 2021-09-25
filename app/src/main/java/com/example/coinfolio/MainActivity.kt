package com.example.coinfolio

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coinfolio.data.models.app.CryptoCurrency
import com.example.coinfolio.representation.adapter.TrackCoinListAdapter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_track.*

class MainActivity : AppCompatActivity() {
    private val viewModel: TopCryptoCurrenciesViewModel by lazy {
        val app = application as CoinFolioApp
        val viewModelProviderFactory =
            TopCryptoCurrenciesViewModelProviderFactory(
                app,
                intent
            )
        ViewModelProvider(
            this,
            viewModelProviderFactory
        )[TopCryptoCurrenciesViewModel::class.java]
    }

    private lateinit var mCoinListAdapter: TrackCoinListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track)

        // Set RecyclerView
        val rv = findViewById<RecyclerView>(R.id.RecyclerView_activity_track)
        rv.layoutManager = LinearLayoutManager(this)

        val coinListObserver = Observer<List<CryptoCurrency>> {
            mCoinListAdapter = TrackCoinListAdapter(it) {}
            rv.adapter = mCoinListAdapter
        }

        walletButton.setOnClickListener{
            navigateToWallet()
        }

        viewModel.mAllCryptoCurrencies.observe(this, coinListObserver)
    }

    private fun navigateToWallet() {
        val intent = Intent(this, WalletActivity::class.java)
        startActivity(intent)
    }

}