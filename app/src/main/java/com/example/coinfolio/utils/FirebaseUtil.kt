package com.example.coinfolio.utils

import com.google.firebase.database.FirebaseDatabase


class FirebaseUtil {
    companion object {
        private val database = FirebaseDatabase.getInstance()

        fun writeToFirebaseDataStorage(key: String, value: String) {
            val myRef = database.getReference(key)
            myRef.setValue(value)
        }
    }
}