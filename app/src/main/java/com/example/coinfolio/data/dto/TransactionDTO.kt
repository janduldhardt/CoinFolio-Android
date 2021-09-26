package com.example.coinfolio.data.dto

import androidx.room.*
import com.example.coinfolio.utils.Converters
import java.math.BigDecimal
import java.sql.Timestamp
import java.time.ZonedDateTime

@Entity(primaryKeys = ["zonedDateTimeString","walletId"],
    foreignKeys = [ForeignKey(
        entity = CryptoCurrencyDTO::class,
        parentColumns = arrayOf("abbreviation"),
        childColumns = arrayOf("cryptoCurrency")
    ), ForeignKey(
        entity = WalletDTO::class,
        parentColumns = arrayOf("walletId"),
        childColumns = arrayOf("walletId")
    )]
)
//@Entity
@TypeConverters(Converters::class)
class TransactionDTO(
    @ColumnInfo
    val transactionId: Int,
    @ColumnInfo
    val walletId: String,
    @ColumnInfo
    val cryptoCurrency: String,
    @ColumnInfo
    val transferType: TransferTypeEnum,
    @ColumnInfo
    val amount: BigDecimal,
    @ColumnInfo
    val priceAtTransaction: BigDecimal,
    @ColumnInfo
    val zonedDateTimeString: String
)
{
    val zonedDateTime : ZonedDateTime
            get() = Converters().stringToZonedDateTime(zonedDateTimeString)
}



enum class TransferTypeEnum {
    WITHDRAWL,
    DEPOSIT
}
