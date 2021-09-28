package com.example.coinfolio.representation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.coinfolio.R
import com.example.coinfolio.data.dto.CryptoCurrencyDTO
import com.example.coinfolio.data.dto.TransferTypeEnum
import com.example.coinfolio.databinding.FragmentTransactionDetailsBinding
import com.example.coinfolio.representation.activities.MainActivity
import com.example.coinfolio.representation.viewmodels.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.skydoves.powerspinner.*
import java.math.BigDecimal

class TransactionDetailsFragment : Fragment() {
    private lateinit var parentViewModel: MainViewModel

    private lateinit var navBar: NavigationBarView

    private lateinit var spinnerItemList: MutableList<IconSpinnerItem>

    private var _binding: FragmentTransactionDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_transaction_details, container, false).apply {

        _binding = FragmentTransactionDetailsBinding.inflate(inflater, container, false)

        parentViewModel = (activity as MainActivity).viewModel

        val coinListObserver = Observer<List<CryptoCurrencyDTO>> {
            setCoinSpinner(it)
        }

        //TODO: PowerSpinner for type
//        spinner_transaction_type.adapter = ArrayAdapter<TransferTypeEnum>(
//            context,
//            android.R.layout.simple_spinner_item,
//            TransferTypeEnum.values()
//        )

        parentViewModel.mAllCryptoCurrenciesDTO.observe(viewLifecycleOwner, coinListObserver)
        navBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navBar.visibility = View.GONE

        binding.btnCloseDetails.setOnClickListener {
            navigateBackToWallet()
        }

        binding.btnSaveDetails.setOnClickListener {
            // validate input
            if (!isInputValid()) {
                Toast.makeText(context, R.string.CheckInvalidFields, Toast.LENGTH_SHORT)
                    .show()
            }
            val selectedCoin = spinnerItemList[binding.spinnerTransactionDetails.selectedIndex]
            parentViewModel.saveTransaction(
                selectedCoin.text.toString(),
                binding.editAmount.text.toString(),
                binding.editPrice.text.toString(),
                binding.spinnerTransactionType.selectedItem as TransferTypeEnum
            )
            navigateBackToWallet()
        }

        val view = binding.root
        return view

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setCoinSpinner(coins: List<CryptoCurrencyDTO>) {
        spinnerItemList = mutableListOf<IconSpinnerItem>()
        for (coin in coins) {
            val newItem = IconSpinnerItem(text = coin.abbreviation)
            spinnerItemList.add(newItem)
        }
        spinnerItemList.sortBy { it.text.toString() }

        binding.spinnerTransactionDetails.apply {
            setSpinnerAdapter(IconSpinnerAdapter(this))
            setItems(
                spinnerItemList
            )
            getSpinnerRecyclerView().layoutManager = GridLayoutManager(context, 2)
            selectItemByIndex(0) // select an item initially.
            lifecycleOwner = viewLifecycleOwner
        }
    }

    private fun navigateBackToWallet() {
        (activity as MainActivity?)?.openWalletFragment()
        navBar.visibility = View.VISIBLE
    }

    private fun isInputValid(): Boolean {
        var amountValid = false
        var priceValid = false
        try {
            BigDecimal(binding.editAmount.text.toString())
            amountValid = true
        } catch (e: Exception) {
            binding.editAmount.error = "Enter a decimal"
        }

        try {
            BigDecimal(binding.editPrice.text.toString())
            priceValid = true
        } catch (e: Exception) {
            binding.editPrice.error = "Enter a decimal"
        }

        return amountValid && priceValid
    }
}