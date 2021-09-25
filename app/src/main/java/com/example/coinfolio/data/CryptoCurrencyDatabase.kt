package com.example.coinfolio.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.coinfolio.data.dao.CryptoCurrencyDao
import com.example.coinfolio.data.models.app.CryptoCurrency

@Database(entities = [CryptoCurrency::class], version = 1)
abstract class CryptoCurrencyDatabase : RoomDatabase() {
    abstract fun cryptoCurrencyDao(): CryptoCurrencyDao
}