package com.example.coinfolio.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class WalletDTO(
    @PrimaryKey
    val walletId: String,
)

{

}