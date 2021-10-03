package com.example.coinfolio.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.coinfolio.data.dto.WalletDTO
import com.example.coinfolio.data.relation.WalletWithTransactions

@Dao
interface WalletDao {
    @Query("SELECT * FROM walletdto")
    suspend fun getAllWallets(): List<WalletDTO>

    @Query("SELECT * FROM walletdto WHERE walletId LIKE :id")
    fun getWalletById(id : String): LiveData<List<WalletDTO>>

    @Transaction
    @Query("SELECT * FROM walletdto WHERE walletId LIKE :id LIMIT 1")
    fun getWalletWithTransfersById(id : String): LiveData<WalletWithTransactions>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createWallet(wallet : WalletDTO)
}