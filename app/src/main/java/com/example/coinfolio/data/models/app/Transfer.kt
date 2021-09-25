package com.example.coinfolio.data.models.app

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.coinfolio.utils.Converters
import java.math.BigDecimal

@Entity
@TypeConverters(Converters::class)
class Transfer(
    @PrimaryKey(autoGenerate = true)
    val transferId : Int,
    @ColumnInfo
    val amount : BigDecimal,
    @ColumnInfo
    val transferType: TransferTypeEnum,
)

enum class TransferTypeEnum{
    WITHDRAWL,
    DEPOSIT
}
