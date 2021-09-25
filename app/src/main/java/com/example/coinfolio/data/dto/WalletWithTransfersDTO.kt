package com.example.coinfolio.data.dto

import androidx.room.Embedded
import androidx.room.Relation

class WalletWithTransfersDTO(
    @Embedded val walletDTO : WalletDTO,
    @Relation(
        parentColumn = "walletId",
        entityColumn = "transferId"
    )
    val transferDTOS : List<CryptoCurrencyTransferDTO>
)