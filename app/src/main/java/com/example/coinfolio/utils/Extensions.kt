package com.example.coinfolio.utils

import com.example.coinfolio.data.models.UserCryptoCurrencyViewModel
import com.example.coinfolio.data.relation.WalletWithTransactions

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
