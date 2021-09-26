package com.example.coinfolio.data.services

import com.example.coinfolio.Keys
import com.example.coinfolio.data.dao.TransactionDao
import com.example.coinfolio.data.dto.TransactionDTO
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.time.format.DateTimeFormatter


class WalletService {
    private val database = FirebaseDatabase.getInstance(Keys.FIREBASE_URL)

    fun uploadTransaction(transactionDTO: TransactionDTO) {
        val myRef = database.reference
        val date = DateTimeFormatter.ISO_OFFSET_DATE.format(transactionDTO.zonedDateTime)
        val transactionJson = Gson().toJson(transactionDTO)
        myRef.child("wallets").child(transactionDTO.walletId)
            .child("transactions").child(date).setValue(transactionJson)
    }

    // TODO: This could be improved as using the transactionDao in here is not good practice
    fun addValueEventListenerFirebase(walletId: String, transactionDao: TransactionDao) {
        val walletListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val transactionsHashMap =
                    dataSnapshot.child("wallets").child(walletId).child("transactions").value as HashMap<*, *>
                        ?: return
                val gson = Gson()
                val listValues = (transactionsHashMap as HashMap<*, *>).values

                val itemType = object : TypeToken<List<TransactionDTO>>() {}.type

                val transactions =
                    gson.fromJson<List<TransactionDTO>>(listValues.toString(), itemType)

                runBlocking {
                    withContext(Dispatchers.Default) {
                        for (transaction in transactions) {
                            transactionDao.createTransaction(transaction)
                        }
                    }
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                throw Exception("Cannot get wallet")
            }
        }
        database.reference.addValueEventListener(walletListener)
    }


}