package com.example.coinfolio.data.models.app

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Wallet(
    @PrimaryKey
    val walletId: String,
)

{

}