package com.example.coinfolio.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.coinfolio.data.dto.TransactionDTO

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transactiondto")
    fun getAllTransactions() : LiveData<List<TransactionDTO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createTransaction(transaction : TransactionDTO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createTransfers(vararg transactionDTO: TransactionDTO)
}