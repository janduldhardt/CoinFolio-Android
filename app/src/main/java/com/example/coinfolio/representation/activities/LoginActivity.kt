package com.example.coinfolio.representation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coinfolio.R
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.example.coinfolio.CoinFolioApp
import com.example.coinfolio.Constants
import com.example.coinfolio.representation.viewmodels.LoginViewModel
import com.example.coinfolio.representation.viewmodels.LoginViewModelFactory
import com.example.coinfolio.representation.viewmodels.TopCryptoCurrenciesViewModel
import com.example.coinfolio.representation.viewmodels.TopCryptoCurrenciesViewModelProviderFactory
import com.example.coinfolio.utils.SharedPreferencesUtil
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*


class LoginActivity : AppCompatActivity() {
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
        setContentView(R.layout.activity_login)

        if(hasWalletId()){
            openMainActivity()
        }

        btn_generate_wallet_seed.setOnClickListener {
            edit_wallet_login.text.clear()
            val seed = UUID.randomUUID().toString().replace(Regex("-"),"")
            edit_wallet_login.setText(seed)
        }

        btn_login.setOnClickListener {
            if (!seedIsValid()) {
                return@setOnClickListener
            }

            SharedPreferencesUtil.writeToSharedPreferences(
                this,
                Constants.WALLET_ID,
                edit_wallet_login.text.toString()
            )
            openMainActivity()
        }
    }

    private fun hasWalletId(): Boolean {
        val walletId = SharedPreferencesUtil.readFromSharedPreferences(this,Constants.WALLET_ID)
        return !walletId.isNullOrBlank()
    }

    private fun seedIsValid(): Boolean {
        val seed = edit_wallet_login.text.toString()
        val onlyDigitAndLettersRegex = "[a-zA-Z0-9]*".toRegex()
        var valid = seed.matches(onlyDigitAndLettersRegex)
        valid = valid && seed.length > 15 && seed.length < 255
        if (!valid) {
            edit_wallet_login.error = R.string.LoignWalletSeedInputError.toString()
            return false
        }
        return true
    }

    private fun openMainActivity(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}