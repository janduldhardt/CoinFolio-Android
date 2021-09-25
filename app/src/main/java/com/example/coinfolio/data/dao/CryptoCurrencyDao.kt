package com.example.coinfolio.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.coinfolio.data.dto.CryptoCurrencyDTO

@Dao
interface CryptoCurrencyDao {

    @Query("SELECT * FROM cryptocurrencydto")
    suspend fun getAllCryptoCurrencies(): List<CryptoCurrencyDTO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createCryptoCurrency(vararg cryptoCurrencyDTO: CryptoCurrencyDTO)
}