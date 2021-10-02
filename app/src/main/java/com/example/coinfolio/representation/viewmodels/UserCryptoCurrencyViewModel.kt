package com.example.coinfolio.representation.viewmodels

import java.math.BigDecimal

data class UserCryptoCurrencyViewModel(
    val cryptoCurrencyName: String,
    val logoUrl: String,
    val coinSymbol: String,
    val amount: BigDecimal,
    val usdAmount: BigDecimal,
    val profit : BigDecimal
) {
    fun getProfitInPercent(): BigDecimal {
        return profit / usdAmount * BigDecimal(100)
    }
}