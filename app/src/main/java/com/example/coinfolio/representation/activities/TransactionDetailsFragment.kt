package com.example.coinfolio.representation.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.coinfolio.CoinFolioApp
import com.example.coinfolio.Constants
import com.example.coinfolio.R
import com.example.coinfolio.data.dto.CryptoCurrencyDTO
import com.example.coinfolio.representation.viewmodels.MainViewModel
import com.example.coinfolio.representation.viewmodels.TransactionDetailsViewModel
import com.example.coinfolio.representation.viewmodels.TransactionDetailsViewModelFactory
import com.example.coinfolio.utils.SharedPreferencesUtil
import kotlinx.android.synthetic.main.activity_transaction_details.*
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
    private val parentViewModel: MainViewModel = (activity as MainActivity).viewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.activity_transaction_details, container, false).apply {


        val coinListObserver = Observer<List<CryptoCurrencyDTO>> {
            val cryptoCoinStringList = it.map { it.abbreviation }
            val arrayAdapter =
                ArrayAdapter(context, android.R.layout.simple_spinner_item, cryptoCoinStringList)
            spinner_transaction_details.adapter = arrayAdapter
        }

            parentViewModel.mAllCryptoCurrenciesDTO.observe(viewLifecycleOwner, coinListObserver)

        btn_close_transaction_details.setOnClickListener {
//            onBackPressed()
        }

        btn_save_transaction_details.setOnClickListener {
            // validate input
            if (!isInputValid()) {
                Toast.makeText(context, R.string.CheckInvalidFields, Toast.LENGTH_SHORT)
                    .show()
            }
//            parentViewModel.saveTransaction(
//                spinner_transaction_details.selectedItem.toString(),
//                edit_amount.text.toString(),
//                edit_price.text.toString()
//            )
//            onBackPressed()
        }

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