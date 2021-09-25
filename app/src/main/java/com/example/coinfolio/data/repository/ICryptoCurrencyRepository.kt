package com.example.coinfolio.data.repository

import com.example.coinfolio.data.models.app.CryptoCurrency

interface ICryptoCurrencyRepository {
    fun getTopNCryptoCurrencies() : List<CryptoCurrency>
}