package com.example.coinfolio.data.relation

import androidx.room.DatabaseView
import androidx.room.Embedded
import androidx.room.Relation
import com.example.coinfolio.data.dto.CryptoCurrencyDTO
import com.example.coinfolio.data.dto.TransactionDTO
import com.example.coinfolio.data.dto.WalletDTO

class WalletWithTransactions(
    @Embedded val walletDTO: WalletDTO,
    @Relation(
        parentColumn = "walletId",
        entityColumn = "walletId",
        entity = TransactionDTO::class
    )
    val transactions: List<TransactionWithCryptoCurrency>,
)