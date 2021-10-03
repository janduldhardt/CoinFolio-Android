package com.example.coinfolio.data.firebase

import com.example.coinfolio.data.dto.TransactionDTO

data class WalletResponse(
    var key : String,
    var value: List<TransactionDTO>? = null,
    var exception: Exception? = null
)
