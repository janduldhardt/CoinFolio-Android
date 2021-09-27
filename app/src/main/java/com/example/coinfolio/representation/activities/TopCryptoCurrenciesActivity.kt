package com.example.coinfolio.representation.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coinfolio.*
import com.example.coinfolio.data.dto.CryptoCurrencyDTO
import com.example.coinfolio.representation.adapter.TrackCoinListAdapter
import com.example.coinfolio.representation.viewmodels.TopCryptoCurrenciesViewModel
import com.example.coinfolio.representation.viewmodels.TopCryptoCurrenciesViewModelProviderFactory
import com.example.coinfolio.utils.SharedPreferencesUtil
import kotlinx.android.synthetic.main.activity_track.*

class TopCryptoCurrenciesActivity : AppCompatActivity() {
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

        // TODO: Remove for production
        SharedPreferencesUtil.writeToSharedPreferences(this, "walletId","1234567890")

        // Set RecyclerView
        val rv = findViewById<RecyclerView>(R.id.RecyclerView_activity_track)
        rv.layoutManager = LinearLayoutManager(this)

        val coinListObserver = Observer<List<CryptoCurrencyDTO>> {
            mCoinListAdapter = TrackCoinListAdapter(it) {}
            rv.adapter = mCoinListAdapter
        }

        viewModel.mAllCryptoCurrenciesDTO.observe(this, coinListObserver)

        walletButton.setOnClickListener{
            navigateToWallet()
        }

    }

    private fun navigateToWallet() {
        val intent = Intent(this, WalletActivity::class.java)
        startActivity(intent)
    }

}