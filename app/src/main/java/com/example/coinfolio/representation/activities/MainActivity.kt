package com.example.coinfolio.representation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.coinfolio.CoinFolioApp
import com.example.coinfolio.R
import com.example.coinfolio.representation.viewmodels.MainViewModel
import com.example.coinfolio.representation.viewmodels.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val viewModel: MainViewModel by lazy {
        val app = application as CoinFolioApp
        val viewModelProviderFactory =
            MainViewModelFactory(
                app,
                intent
            )
        ViewModelProvider(
            this,
            viewModelProviderFactory
        )[MainViewModel::class.java]
    }

    private lateinit var walletFragment: WalletFragment
    private lateinit var trackFragment: TopCryptoCurrenciesFragment
    private lateinit var detailsFragment: TransactionDetailsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trackFragment = TopCryptoCurrenciesFragment()
        walletFragment = WalletFragment()
        detailsFragment = TransactionDetailsFragment()
        replaceFragment(TopCryptoCurrenciesFragment())

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.trackMenuItem -> replaceFragment(trackFragment)
                R.id.walletMenuItem -> replaceFragment(walletFragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .commit()
    }

    fun openTransactionDetailsFragment(){
        replaceFragment(detailsFragment)
    }

    fun openWalletFragment(){
        replaceFragment(walletFragment)
    }
}