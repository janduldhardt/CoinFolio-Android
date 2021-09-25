package com.example.coinfolio.data.dto

import androidx.room.Embedded
import androidx.room.Relation

class CryptoCurrencyTransferDTO(
    @Embedded val transfer : Transfer,
    @Relation(
        parentColumn = "transferId",
        entityColumn = "abbreviation"
    )
    val cryptoCurrencyDTOS : List<CryptoCurrencyDTO>
)

