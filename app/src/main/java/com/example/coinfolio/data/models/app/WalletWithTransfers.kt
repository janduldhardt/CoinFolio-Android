package com.example.coinfolio.data.models.app

import androidx.room.Embedded
import androidx.room.Relation

class WalletWithTransfers(
    @Embedded val wallet : Wallet,
    @Relation(
        parentColumn = "walletId",
        entityColumn = "transferId"
    )
    val transfers : List<CryptoCurrencyTransfer>
)