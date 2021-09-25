package com.example.coinfolio.data.repository

import com.example.coinfolio.data.dto.CryptoCurrencyDTO

interface ICryptoCurrencyRepository {
    fun getTopNCryptoCurrencies() : List<CryptoCurrencyDTO>
}