package com.example.coinfolio.utils

import androidx.room.TypeConverter
import com.example.coinfolio.data.models.app.TransferTypeEnum
import java.math.BigDecimal

class Converters {
    @TypeConverter
    fun transferEnumToString(value: TransferTypeEnum?): String {
        return value.toString()
    }

    @TypeConverter
    fun bigDecimalToString(input: BigDecimal?): String {
        return input?.toPlainString() ?: ""
    }

    @TypeConverter
    fun stringToBigDecimal(input: String?): BigDecimal {
        if (input.isNullOrBlank()) return BigDecimal.valueOf(0.0)
        return input.toBigDecimalOrNull() ?: BigDecimal.valueOf(0.0)
    }
}