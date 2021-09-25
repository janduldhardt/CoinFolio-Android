package com.example.coinfolio.data.models.app

import androidx.room.Embedded
import androidx.room.Relation

class CryptoCurrencyTransfer(
    @Embedded val transfer : Transfer,
    @Relation(
        parentColumn = "transferId",
        entityColumn = "abbreviation"
    )
    val cryptoCurrencies : List<CryptoCurrency>
)

