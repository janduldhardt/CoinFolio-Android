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
            c.component1().name,
            c.component1().imageUri,
            c.component1().symbol,
            c.component2(),
            c.component1().price.multiply(c.component2())
        )
        output.add(vm)
    }
    return output
}

fun BigDecimal.toString8Decimals() : String{
    return DecimalFormat("0.00000000").format(this)
}

fun BigDecimal.toCurrencyString() : String{
    val numberFormat = NumberFormat.getInstance()
    val currency: Currency = Currency.getInstance(Locale.getDefault())
    numberFormat.minimumFractionDigits = currency.defaultFractionDigits
    return numberFormat.format(this.toLong())
}