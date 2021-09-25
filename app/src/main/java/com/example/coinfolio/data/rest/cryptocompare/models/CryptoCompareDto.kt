package com.example.coinfolio.data.rest.cryptocompare.models

import Data
import MetaData
import RateLimit
import SponsoredData
import com.example.coinfolio.data.dto.CryptoCurrencyDTO
import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

/*
Copyright (c) 2021 Kotlin com.example.coinfolio.data.rest.cryptocompare.models.Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


data class CryptoCompareResponse(

    @SerializedName("Message") val message: String,
    @SerializedName("Type") val type: Int,
    @SerializedName("MetaData") val metaData: MetaData,
    @SerializedName("SponsoredData") val sponsoredData: List<SponsoredData>,
    @SerializedName("Data") val data: List<Data>,
    @SerializedName("RateLimit") val rateLimit: RateLimit,
    @SerializedName("HasWarning") val hasWarning: Boolean
) {
    fun getCryptoList(): List<CryptoCurrencyDTO> {
        val coins = mutableListOf<CryptoCurrencyDTO>()

        for (d in data) {
            val cinfo = d.coinInfo
            val craw = d.rAW
            if (cinfo == null || craw == null) {
                continue
            }
            val coin =
                CryptoCurrencyDTO(d.rAW.uSD.fROMSYMBOL, cinfo.fullName, cinfo.imageUrl, BigDecimal( craw.uSD.pRICE))
            coins.add(coin)
        }

        return coins
    }
}