package com.example.coinfolio.data.models.app

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity
data class CryptoCurrency(
    @PrimaryKey(autoGenerate = false)
    val abbreviation: String,

    @ColumnInfo
    val name: String,

    @ColumnInfo
    val imageUri: String,

    @ColumnInfo
    var price: String
)
{
}