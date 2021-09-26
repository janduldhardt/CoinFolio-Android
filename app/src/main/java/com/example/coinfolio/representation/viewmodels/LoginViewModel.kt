package com.example.coinfolio.representation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.coinfolio.data.repository.WalletRepository

class LoginViewModel(
    val walletRepository: WalletRepository
) : ViewModel() {

    fun login() {

    }

}