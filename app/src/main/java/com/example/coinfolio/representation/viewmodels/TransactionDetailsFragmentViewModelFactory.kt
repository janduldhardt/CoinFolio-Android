package com.example.coinfolio.representation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TransactionDetailsFragmentViewModelFactory() :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        val viewModel = TransactionDetailsFragmentViewModel()
        return viewModel as T
    }
}