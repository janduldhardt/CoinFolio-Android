package com.example.coinfolio.representation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.coinfolio.data.dto.CryptoCurrencyDTO
import com.example.coinfolio.data.dto.TransferTypeEnum
import java.math.BigDecimal

class TransactionDetailsFragmentViewModel : ViewModel() {
    var transactionType: TransferTypeEnum = TransferTypeEnum.DEPOSIT
    var cryptoCurrencyAbbreviation: String = ""
    var cointAmount: String = ""
    var fiatPrice: String = ""

}