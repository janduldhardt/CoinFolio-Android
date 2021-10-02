package com.example.coinfolio.utils

import com.example.coinfolio.data.models.UserCryptoCurrencyViewModel
import com.example.coinfolio.data.relation.WalletWithTransactions
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

fun WalletWithTransactions.toUserCryptoCurrencyViewModelList(): ArrayList<UserCryptoCurrencyViewModel> {
    val currenciesAndAmountMap = getCryptoCurrenciesAmountsMap()
    val output = arrayListOf<UserCryptoCurrencyViewModel>()
    for (c in currenciesAndAmountMap) {
        val vm = UserCryptoCurrencyViewModel(
            c.value.cryptoCurrency.name,
            c.value.cryptoCurrency.imageUri,
            c.value.cryptoCurrency.symbol,
            c.value.amountCoins,
            c.value.amountFiat
        )
        output.add(vm)
    }
    return output
}

fun BigDecimal.toString8Decimals() : String{
    return DecimalFormat("0.00000000").format(this)
}

fun BigDecimal.toCurrencyString() : String{
    return DecimalFormat("0.00").format(this)
//    val numberFormat = NumberFormat.getInstance()
//    val currency: Currency = Currency.getInstance(Locale.getDefault())
//    numberFormat.minimumFractionDigits = currency.defaultFractionDigits
//    return numberFormat.format(this.toLong())
}