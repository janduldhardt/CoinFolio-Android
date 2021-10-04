package com.example.coinfolio.representation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coinfolio.R
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.example.coinfolio.CoinFolioApp
import com.example.coinfolio.Constants
import com.example.coinfolio.databinding.ActivityLoginBinding
import com.example.coinfolio.representation.viewmodels.LoginViewModel
import com.example.coinfolio.representation.viewmodels.LoginViewModelFactory
import com.example.coinfolio.utils.SharedPreferencesUtil
import java.util.*


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val viewModel: LoginViewModel by lazy {
        val app = application as CoinFolioApp
        val viewModelProviderFactory =
            LoginViewModelFactory(
                app,
                intent
            )
        ViewModelProvider(
            this,
            viewModelProviderFactory
        )[LoginViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_CoinFolio)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if(hasWalletId()){
            CoinFolioApp.walletId = SharedPreferencesUtil.readFromSharedPreferences(this, Constants.WALLET_ID)!!
            openMainActivity()
        }

        binding.btnGenerateWalletSeed.setOnClickListener {
            binding.editWalletLogin.text.clear()
            val seed = UUID.randomUUID().toString().replace(Regex("-"),"")
            binding.editWalletLogin.setText(seed)
        }

        binding.btnLogin.setOnClickListener {
            val walletSeed = binding.editWalletLogin.text.toString()

            if (!seedIsValid()) {
                return@setOnClickListener
            }

            SharedPreferencesUtil.writeToSharedPreferences(
                this,
                Constants.WALLET_ID,
                binding.editWalletLogin.text.toString()
            )

            viewModel.login(walletSeed)
            CoinFolioApp.walletId = walletSeed
            openMainActivity()
        }
    }

    private fun hasWalletId(): Boolean {
        val walletId = SharedPreferencesUtil.readFromSharedPreferences(this,Constants.WALLET_ID)
        return !walletId.isNullOrBlank()
    }

    private fun seedIsValid(): Boolean {
        val seed = binding.editWalletLogin.text.toString()
        val onlyDigitAndLettersRegex = "[a-zA-Z0-9]*".toRegex()
        var valid = seed.matches(onlyDigitAndLettersRegex)
        valid = valid && seed.length > 15 && seed.length < 255
        if (!valid) {
            binding.editWalletLogin.error = R.string.LoignWalletSeedInputError.toString()
            return false
        }
        return true
    }

    private fun openMainActivity(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}