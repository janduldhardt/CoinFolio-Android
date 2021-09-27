package com.example.coinfolio.representation.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coinfolio.*
import com.example.coinfolio.data.dto.CryptoCurrencyDTO
import com.example.coinfolio.representation.adapter.TrackCoinListAdapter
import com.example.coinfolio.representation.viewmodels.MainViewModel

class TopCryptoCurrenciesFragment : Fragment() {
//    private val viewModel: TopCryptoCurrenciesViewModel by lazy {
//        val viewModelProviderFactory =
//            TopCryptoCurrenciesViewModelProviderFactory(
//                requireContext()
//            )
//        ViewModelProvider(
//            this,
//            viewModelProviderFactory
//        )[TopCryptoCurrenciesViewModel::class.java]
//    }

    private lateinit var mCoinListAdapter: TrackCoinListAdapter

    private lateinit var parentViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_track, container, false).apply {

        parentViewModel = (activity as MainActivity).viewModel

        // Set RecyclerView
        val rv = findViewById<RecyclerView>(R.id.RecyclerView_activity_track)
        rv.layoutManager = LinearLayoutManager(activity)

        val coinListObserver = Observer<List<CryptoCurrencyDTO>> {
            mCoinListAdapter = TrackCoinListAdapter(it) {}
            rv.adapter = mCoinListAdapter
        }

        parentViewModel.mAllCryptoCurrenciesDTO.observe(viewLifecycleOwner, coinListObserver)

//        walletButton.setOnClickListener {
//            navigateToWallet()
//        }

//        return super.onCreateView(inflater, container, savedInstanceState)
    }


    private fun navigateToWallet() {
//        val intent = Intent(this, WalletActivity::class.java)
//        startActivity(intent)
    }

}