package com.example.coinfolio.data.repository

import android.content.Context
import com.example.coinfolio.data.dao.CryptoCurrencyDao
import com.example.coinfolio.data.dao.TransactionDao
import com.example.coinfolio.data.dao.WalletDao
import com.example.coinfolio.data.dto.TransactionDTO
import com.example.coinfolio.data.dto.WalletDTO
import com.example.coinfolio.data.relation.WalletWithTransactions
import com.example.coinfolio.data.services.WalletService

class WalletRepository(
    private val context: Context,
    private val walletDao: WalletDao,
    private val transactionDao: TransactionDao,
    private val cryptoCurrencyDao: CryptoCurrencyDao,
    private val walletService: WalletService
) {

    suspend fun getWallet(walletId: String) = walletDao.getWalletById(walletId)
    suspend fun getWalletWithTransactions(walletId: String) =
        walletDao.getWalletWithTransfersById(walletId)

    suspend fun getAllTransactions() = transactionDao.getAllTransactions()

    suspend fun createTransaction(transactionDTO: TransactionDTO) {
        transactionDao.createTransaction(transactionDTO)
        walletService.uploadTransaction(transactionDTO)
    }


    suspend fun createWallet(walletDTO: WalletDTO) = walletDao.createWallet(walletDTO)

    fun addValueEventListenerFirebase(walletId: String) {
//        walletService.addValueEventListenerFirebase(walletId, transactionDao)
    }

}
