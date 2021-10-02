package com.example.coinfolio.representation.fragments

import android.R.string
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coinfolio.R
import com.example.coinfolio.data.relation.WalletWithTransactions
import com.example.coinfolio.databinding.FragmentWalletBinding
import com.example.coinfolio.representation.activities.MainActivity
import com.example.coinfolio.representation.adapter.WalletCoinListAdapter
import com.example.coinfolio.representation.viewmodels.MainViewModel
import com.example.coinfolio.utils.toUserCryptoCurrencyViewModelList
import android.R.string.no
import com.example.coinfolio.utils.toCurrencyString
import java.math.BigDecimal


class WalletFragment : Fragment() {

    private var _binding: FragmentWalletBinding? = null
    private val binding get() = _binding!!

    private lateinit var mCoinListAdapter: WalletCoinListAdapter

    private lateinit var parentViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_wallet, container, false).apply {
        _binding = FragmentWalletBinding.inflate(inflater, container, false)

        parentViewModel = (activity as MainActivity).viewModel

        binding.recyclerViewWallet.layoutManager = LinearLayoutManager(context)

        val coinListObserver = Observer<WalletWithTransactions> {
            val sortedList = it.toUserCryptoCurrencyViewModelList().sortedBy { it.cryptoCurrencyName }
            mCoinListAdapter = WalletCoinListAdapter(sortedList) {}
            binding.recyclerViewWallet.adapter = mCoinListAdapter
            binding.textviewWalletTotalAmountFiat.text = "${it.getTotalAmountFiat().toCurrencyString()}"
            val profit = it.getTotalProfitLoss()
            binding.textviewWalletTotalAmountFiatProfit.text = profit.toCurrencyString()

            val textColor = if (profit > BigDecimal(0))  context.getColor((R.color.textColorGreen)) else context.getColor((R.color.textColorRed))
                binding.textviewWalletTotalAmountFiatProfit.setTextColor(textColor)
        }

        parentViewModel.mWalletWithTransactions.observe(viewLifecycleOwner, coinListObserver)

        binding.addTransaction.setOnClickListener {
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