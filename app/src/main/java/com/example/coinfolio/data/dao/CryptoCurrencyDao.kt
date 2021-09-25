package com.example.coinfolio.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.coinfolio.data.models.app.CryptoCurrency

@Dao
interface CryptoCurrencyDao {

    @Query("SELECT * FROM cryptocurrency")
    suspend fun getAllCryptoCurrencies(): List<CryptoCurrency>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createCryptoCurrency(vararg cryptoCurrency: CryptoCurrency)
}