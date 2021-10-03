package com.example.coinfolio.representation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinfolio.data.dto.WalletDTO
import com.example.coinfolio.data.repository.WalletRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(
    val walletRepository: WalletRepository
) : ViewModel() {

    fun login(walletId : String) {
        val newWallet = WalletDTO(walletId)
        viewModelScope.launch{
            walletRepository.createWallet(newWallet)
        }

    }

}