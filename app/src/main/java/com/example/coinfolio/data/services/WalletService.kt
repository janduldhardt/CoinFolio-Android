package com.example.coinfolio.data.services

import com.example.coinfolio.Keys
import com.example.coinfolio.data.dto.TransactionDTO
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson
import java.time.format.DateTimeFormatter

class WalletService {
    private val database = FirebaseDatabase.getInstance(Keys.FIREBASE_URL)

    fun uploadTransaction(transactionDTO: TransactionDTO){
        val myRef = database.getReference(transactionDTO.walletId)
        val date = DateTimeFormatter.ISO_OFFSET_DATE.format(transactionDTO.zonedDateTime)
        val transactionJson = Gson().toJson(transactionDTO)
        myRef.child("transactions").child(date).setValue(transactionJson)
    }

    fun downloadWallet(walletId : String){
//        val myRef = database.getReference(walletId)
//        val transactionJson = Gson().toJson(transactionDTO)
//        myRef.child()
//        myRef.child("transactions").child(date).setValue(transactionJson)
    }

}