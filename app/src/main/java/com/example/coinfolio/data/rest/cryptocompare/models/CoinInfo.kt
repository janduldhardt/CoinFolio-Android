import com.example.coinfolio.Constants
import com.google.gson.annotations.SerializedName

/*
Copyright (c) 2021 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


data class CoinInfo (

	@SerializedName("Id") val id : Int,
	@SerializedName("Name") val name : String,
	@SerializedName("FullName") val fullName : String,
	@SerializedName("Internal") val internal : String,
	@SerializedName("ImageUrl") val imageUrl : String,
	@SerializedName("Url") val url : String,
//	@SerializedName("Algorithm") val algorithm : String,
//	@SerializedName("ProofType") val proofType : String,
//	@SerializedName("Rating") val rating : Rating,
//	@SerializedName("NetHashesPerSecond") val netHashesPerSecond : Double,
//	@SerializedName("BlockNumber") val blockNumber : Int,
//	@SerializedName("BlockTime") val blockTime : String,
//	@SerializedName("BlockReward") val blockReward : Double,
//	@SerializedName("AssetLaunchDate") val assetLaunchDate : String,
//	@SerializedName("MaxSupply") val maxSupply : Double,
//	@SerializedName("Type") val type : Int,
//	@SerializedName("DocumentType") val documentType : String
)
{
	val fullImageUrl : String
	get() = Constants.CRYPTOCOMPARE_IMAGE_BASE_URL + imageUrl
}