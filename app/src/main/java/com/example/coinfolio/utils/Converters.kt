package com.example.coinfolio.utils

import androidx.room.TypeConverter
import com.example.coinfolio.data.dto.TransferTypeEnum
import java.math.BigDecimal
import java.sql.Timestamp
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class Converters {
    @TypeConverter
    fun transferEnumToString(value: TransferTypeEnum?): String {
        return value.toString()
    }

    @TypeConverter
    fun stringToTransferEnum(value: String?): TransferTypeEnum {
        if(value.equals("DEPOSIT")){
            return TransferTypeEnum.DEPOSIT
        }
        return TransferTypeEnum.WITHDRAWL
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

    @TypeConverter
    fun zonedDateTimeToString(input: ZonedDateTime): String {
        val formattedDateTime = input.truncatedTo(ChronoUnit.SECONDS)
        val output =  DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(formattedDateTime)
        return output
    }

    @TypeConverter
    fun stringToZonedDateTime(input: String?): ZonedDateTime {
        if (input.isNullOrBlank()) return ZonedDateTime.of(0,0,0,0,0,0,0, ZoneId.systemDefault())
        return ZonedDateTime.parse(input)
    }
}