package com.example.coinfolio.data.relation

import androidx.room.DatabaseView
import androidx.room.Embedded
import androidx.room.Relation
import com.example.coinfolio.data.dto.CryptoCurrencyDTO
import com.example.coinfolio.data.dto.TransactionDTO

class TransactionWithCryptoCurrency(
    @Embedded
    val transactionDTO : TransactionDTO,
    @Relation(
        parentColumn = "cryptoCurrency",
        entityColumn = "abbreviation"
    )
    val cryptoCurrencyDTO : CryptoCurrencyDTO,
)

