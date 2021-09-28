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
import com.example.coinfolio.databinding.DetailsLayoutBinding
import com.example.coinfolio.databinding.FragmentWalletBinding
import com.example.coinfolio.representation.adapter.WalletCoinListAdapter
import com.example.coinfolio.representation.viewmodels.MainViewModel

class WalletFragment : Fragment() {

    private var _binding: FragmentWalletBinding? = null
    private val binding get() = _binding!!

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
        _binding = FragmentWalletBinding.inflate(inflater, container, false)

        parentViewModel = (activity as MainActivity).viewModel

        val rv = findViewById<RecyclerView>(R.id.RecyclerView_activity_wallet)
        rv.layoutManager = LinearLayoutManager(context)

        val coinListObserver = Observer<WalletWithTransactions> {
            mCoinListAdapter = WalletCoinListAdapter(it.getCryptoCurrencies()) {}
            rv.adapter = mCoinListAdapter
        }

        parentViewModel.mWalletWithTransactions.observe(viewLifecycleOwner, coinListObserver)

        binding.btnOpenTransactionDetails?.setOnClickListener {
            openTransactionDetails()
        }

        val view = binding.root
        return view

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun openTransactionDetails() {
        (activity as MainActivity?)?.openTransactionDetailsFragment()
    }
}