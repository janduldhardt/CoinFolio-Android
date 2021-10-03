package com.example.coinfolio.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.coinfolio.data.dao.CryptoCurrencyDao
import com.example.coinfolio.data.dao.TransactionDao
import com.example.coinfolio.data.dao.WalletDao
import com.example.coinfolio.data.dto.CryptoCurrencyDTO
import com.example.coinfolio.data.dto.TransactionDTO
import com.example.coinfolio.data.dto.WalletDTO

@Database(entities = [CryptoCurrencyDTO::class, TransactionDTO::class, WalletDTO::class], version = 1)
abstract class CryptoCurrencyDatabase : RoomDatabase() {
    abstract fun cryptoCurrencyDao(): CryptoCurrencyDao
    abstract fun walletDao(): WalletDao
    abstract fun transferDao(): TransactionDao
}