import com.google.gson.annotations.SerializedName

/*
Copyright (c) 2021 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


data class USD (

	@SerializedName("FROMSYMBOL") val fROMSYMBOL : String,
	@SerializedName("TOSYMBOL") val tOSYMBOL : String,
	@SerializedName("MARKET") val mARKET : String,
	@SerializedName("PRICE") val pRICE : String,
	@SerializedName("LASTUPDATE") val lASTUPDATE : String,
	@SerializedName("LASTVOLUME") val lASTVOLUME : String,
	@SerializedName("LASTVOLUMETO") val lASTVOLUMETO : String,
	@SerializedName("LASTTRADEID") val lASTTRADEID : String,
	@SerializedName("VOLUMEDAY") val vOLUMEDAY : String,
	@SerializedName("VOLUMEDAYTO") val vOLUMEDAYTO : String,
	@SerializedName("VOLUME24HOUR") val vOLUME24HOUR : String,
	@SerializedName("VOLUME24HOURTO") val vOLUME24HOURTO : String,
	@SerializedName("OPENDAY") val oPENDAY : String,
	@SerializedName("HIGHDAY") val hIGHDAY : String,
	@SerializedName("LOWDAY") val lOWDAY : String,
	@SerializedName("OPEN24HOUR") val oPEN24HOUR : String,
	@SerializedName("HIGH24HOUR") val hIGH24HOUR : String,
	@SerializedName("LOW24HOUR") val lOW24HOUR : String,
	@SerializedName("LASTMARKET") val lASTMARKET : String,
	@SerializedName("VOLUMEHOUR") val vOLUMEHOUR : String,
	@SerializedName("VOLUMEHOURTO") val vOLUMEHOURTO : String,
	@SerializedName("OPENHOUR") val oPENHOUR : String,
	@SerializedName("HIGHHOUR") val hIGHHOUR : String,
	@SerializedName("LOWHOUR") val lOWHOUR : String,
	@SerializedName("TOPTIERVOLUME24HOUR") val tOPTIERVOLUME24HOUR : String,
	@SerializedName("TOPTIERVOLUME24HOURTO") val tOPTIERVOLUME24HOURTO : String,
	@SerializedName("CHANGE24HOUR") val cHANGE24HOUR : String,
	@SerializedName("CHANGEPCT24HOUR") val cHANGEPCT24HOUR : String,
	@SerializedName("CHANGEDAY") val cHANGEDAY : String,
	@SerializedName("CHANGEPCTDAY") val cHANGEPCTDAY : String,
	@SerializedName("CHANGEHOUR") val cHANGEHOUR : String,
	@SerializedName("CHANGEPCTHOUR") val cHANGEPCTHOUR : String,
	@SerializedName("CONVERSIONTYPE") val cONVERSIONTYPE : String,
	@SerializedName("CONVERSIONSYMBOL") val cONVERSIONSYMBOL : String,
	@SerializedName("SUPPLY") val sUPPLY : String,
	@SerializedName("MKTCAP") val mKTCAP : String,
	@SerializedName("MKTCAPPENALTY") val mKTCAPPENALTY : String,
	@SerializedName("CIRCULATINGSUPPLY") val cIRCULATINGSUPPLY : String,
	@SerializedName("CIRCULATINGSUPPLYMKTCAP") val cIRCULATINGSUPPLYMKTCAP : String,
	@SerializedName("TOTALVOLUME24H") val tOTALVOLUME24H : String,
	@SerializedName("TOTALVOLUME24HTO") val tOTALVOLUME24HTO : String,
	@SerializedName("TOTALTOPTIERVOLUME24H") val tOTALTOPTIERVOLUME24H : String,
	@SerializedName("TOTALTOPTIERVOLUME24HTO") val tOTALTOPTIERVOLUME24HTO : String,
	@SerializedName("IMAGEURL") val iMAGEURL : String
)