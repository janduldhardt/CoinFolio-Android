package com.example.coinfolio.data.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.coinfolio.data.dto.CryptoCurrencyDTO
import com.example.coinfolio.data.dto.TransactionDTO
import com.example.coinfolio.data.dto.TransferTypeEnum
import com.example.coinfolio.data.dto.WalletDTO
import java.math.BigDecimal

class WalletWithTransactions(
    @Embedded val walletDTO: WalletDTO,
    @Relation(
        parentColumn = "walletId",
        entityColumn = "walletId",
        entity = TransactionDTO::class
    )
    val transactions: List<TransactionWithCryptoCurrency>,
) {
//    fun getCryptoCurrencies() : List<CryptoCurrencyDTO>{
//        return transactions.map { it.cryptoCurrencyDTO }.toSet().toList()
//    }

    fun getCryptoCurrenciesSet(): Set<CryptoCurrencyDTO> {
        return transactions.map { it.cryptoCurrencyDTO }.toSet()
    }

    fun getCryptoCurrenciesAmountsMap(): Map<CryptoCurrencyDTO, BigDecimal> {
        val output = hashMapOf<CryptoCurrencyDTO, BigDecimal>()

        for (transactionWithCrypto in transactions) {
            val transaction = transactionWithCrypto.transactionDTO
            val crypto = transactionWithCrypto.cryptoCurrencyDTO

            if (!output.containsKey(crypto)) {
                output[crypto] = BigDecimal(0)
            }
            val currentAmount = output[crypto]

            if (transaction.transferType == TransferTypeEnum.DEPOSIT) {
                output[crypto] = (currentAmount!!.plus(transaction.amount))
            }

            else if (transaction.transferType == TransferTypeEnum.WITHDRAWL) {
                output[crypto] = (currentAmount!!.subtract(transaction.amount))
            }
        }
        return output
    }

    fun getTotalAmountFiat(): BigDecimal {
       val map = getCryptoCurrenciesAmountsMap()
        var output = BigDecimal(0)
        map.forEach{
            output.plus(it.component2())
        }
        return output
    }

}