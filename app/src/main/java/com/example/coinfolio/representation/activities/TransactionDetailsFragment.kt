package com.example.coinfolio.representation.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.coinfolio.R
import com.example.coinfolio.data.dto.CryptoCurrencyDTO
import com.example.coinfolio.data.dto.TransferTypeEnum
import com.example.coinfolio.representation.viewmodels.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.skydoves.powerspinner.*
import kotlinx.android.synthetic.main.details_layout.*
import kotlinx.android.synthetic.main.details_layout.view.*
import java.math.BigDecimal

class TransactionDetailsFragment : Fragment() {
    //    private val viewModel: TransactionDetailsViewModel by lazy {
//        val app = application as CoinFolioApp
//        val viewModelProviderFactory =
//            TransactionDetailsViewModelFactory(
//                app,
//                intent
//            )
//        ViewModelProvider(
//            this,
//            viewModelProviderFactory
//        )[TransactionDetailsViewModel::class.java]
//    }
    private lateinit var parentViewModel: MainViewModel

    private lateinit var navBar: NavigationBarView

    private lateinit var spinnerItemList: MutableList<IconSpinnerItem>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.details_layout, container, false).apply {

        parentViewModel = (activity as MainActivity).viewModel

        val coinListObserver = Observer<List<CryptoCurrencyDTO>> {
            setCoinSpinner(it)
        }

        spinner_transaction_type.adapter = ArrayAdapter<TransferTypeEnum>(
            context,
            android.R.layout.simple_spinner_item,
            TransferTypeEnum.values()
        )

        parentViewModel.mAllCryptoCurrenciesDTO.observe(viewLifecycleOwner, coinListObserver)
        navBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navBar.visibility = View.GONE

        btn_close_details.setOnClickListener {
            navigateBackToWallet()
        }

        btn_save_details.setOnClickListener {
            // validate input
            if (!isInputValid()) {
                Toast.makeText(context, R.string.CheckInvalidFields, Toast.LENGTH_SHORT)
                    .show()
            }
            val selectedCoin = spinnerItemList[spinner_transaction_details.selectedIndex]
            parentViewModel.saveTransaction(
                selectedCoin.text.toString(),
                edit_amount.text.toString(),
                edit_price.text.toString(),
                spinner_transaction_type.selectedItem as TransferTypeEnum
            )
            navigateBackToWallet()
        }

    }

    private fun setCoinSpinner(coins: List<CryptoCurrencyDTO>) {
        spinnerItemList = mutableListOf<IconSpinnerItem>()
        for (coin in coins) {
            val newItem = IconSpinnerItem(text = coin.abbreviation)
            spinnerItemList.add(newItem)
        }
        spinnerItemList.sortBy { it.text.toString() }

        spinner_transaction_details.apply {
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