package com.example.coinfolio.representation.activities

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coinfolio.R
import com.example.coinfolio.data.relation.WalletWithTransactions
import com.example.coinfolio.representation.adapter.WalletCoinListAdapter
import com.example.coinfolio.representation.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.fragment_wallet.*
import kotlinx.android.synthetic.main.fragment_wallet.btn_open_transaction_details
import kotlinx.android.synthetic.main.fragment_wallet.view.*

class WalletFragment : Fragment() {

//    private val viewModel: WalletViewModel by lazy {
//        val app = application as CoinFolioApp
//        val viewModelProviderFactory =
//            WalletViewModelFactory(
//                app,
//                intent
//            )
//        ViewModelProvider(
//            this,
//            viewModelProviderFactory
//        )[WalletViewModel::class.java]
//    }

    private lateinit var mCoinListAdapter: WalletCoinListAdapter

    private lateinit var parentViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_wallet, container, false).apply {

        parentViewModel = (activity as MainActivity).viewModel

        val rv = findViewById<RecyclerView>(R.id.RecyclerView_activity_wallet)
        rv.layoutManager = LinearLayoutManager(context)

        val coinListObserver = Observer<WalletWithTransactions> {
            mCoinListAdapter = WalletCoinListAdapter(it.getCryptoCurrencies()) {}
            rv.adapter = mCoinListAdapter
        }

        parentViewModel.mWalletWithTransactions.observe(viewLifecycleOwner, coinListObserver)

        btn_open_transaction_details?.setOnClickListener {
            openTransactionDetails()
        }

    }

    private fun openTransactionDetails() {
        (activity as MainActivity?)?.openTransactionDetailsFragment()
    }
}