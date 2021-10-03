package com.example.coinfolio.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.coinfolio.utils.Converters
import java.math.BigDecimal

@Entity
@TypeConverters(Converters::class)
data class CryptoCurrencyDTO(
    @PrimaryKey(autoGenerate = false)
    val abbreviation: String,

    @ColumnInfo
    val name: String,

    @ColumnInfo
    val imageUri: String,

    @ColumnInfo
    var price: BigDecimal,

    @ColumnInfo
    val symbol: String,

    @ColumnInfo
    val marketCap: BigDecimal

)