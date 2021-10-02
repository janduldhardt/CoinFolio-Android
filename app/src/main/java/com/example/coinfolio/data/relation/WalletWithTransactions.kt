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

//    val cryptoCurrencyAmountsMap by lazy { getCryptoCurrenciesAmountsMap() }

    fun getTotalAmountFiat(): BigDecimal {
        return getCryptoCurrenciesAmountsMap().map { it.value.amountFiat }.sumOf { it }
    }

    fun getTotalCost() : BigDecimal{
        return transactions.sumOf { it.transactionDTO.priceAtTransaction }
    }

    fun getTotalProfitLoss() : BigDecimal{
        return getTotalAmountFiat() - getTotalCost()
    }

    private fun getCryptoCurrenciesSet(): Set<CryptoCurrencyDTO> {
        return transactions.map { it.cryptoCurrencyDTO }.toSet()
    }

    fun getCryptoCurrenciesAmountsMap(): Map<String, CryptoCurrencyAmountTupleWrapper> {
        val mapAbbreviationCryptoCurrency = mutableMapOf<String, CryptoCurrencyDTO>()
        transactions.map {
            mapAbbreviationCryptoCurrency[it.cryptoCurrencyDTO.abbreviation] = it.cryptoCurrencyDTO
        }

        val output = hashMapOf<String, CryptoCurrencyAmountTupleWrapper>()

        for (transactionWithCrypto in transactions) {
            val transaction = transactionWithCrypto.transactionDTO
            val abbreviation = transactionWithCrypto.cryptoCurrencyDTO.abbreviation

            if (!output.containsKey(abbreviation)) {
                output[abbreviation] = CryptoCurrencyAmountTupleWrapper(
                    mapAbbreviationCryptoCurrency[abbreviation]!!,
                    BigDecimal(0),
                    BigDecimal(0)
                )
            }
            val tupleWrapper = output[abbreviation]!!

            if (transaction.transferType == TransferTypeEnum.DEPOSIT) {
                tupleWrapper.amountCoins =
                    tupleWrapper.amountCoins.plus(transactionWithCrypto.transactionDTO.amount)
            } else if (transaction.transferType == TransferTypeEnum.WITHDRAWL) {
                tupleWrapper.amountCoins =
                    tupleWrapper.amountCoins.subtract(transactionWithCrypto.transactionDTO.amount)
            }

            tupleWrapper.amountFiat =
                tupleWrapper.amountCoins.multiply(tupleWrapper.cryptoCurrency.price)

        }
        return output
    }

    class CryptoCurrencyAmountTupleWrapper(
        val cryptoCurrency: CryptoCurrencyDTO,
        var amountFiat: BigDecimal,
        var amountCoins: BigDecimal
    )


}