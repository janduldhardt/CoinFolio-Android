package com.example.coinfolio.data.models

import java.math.BigDecimal

data class UserCryptoCurrencyViewModel(
    val cryptoCurrencyName: String,
    val logoUrl: String,
    val coinSymbol: String,
    val amount: BigDecimal,
    val usdAmount: BigDecimal
) {
}