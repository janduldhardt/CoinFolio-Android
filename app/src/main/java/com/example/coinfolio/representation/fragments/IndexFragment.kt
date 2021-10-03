package com.example.coinfolio.representation.fragments

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
import com.example.coinfolio.databinding.FragmentIndexBinding
import com.example.coinfolio.representation.activities.MainActivity
import com.example.coinfolio.representation.adapter.TrackCoinListAdapter
import com.example.coinfolio.representation.viewmodels.MainViewModel

class IndexFragment : Fragment() {

    private lateinit var mCoinListAdapter: TrackCoinListAdapter

    private lateinit var parentViewModel: MainViewModel

    private var _binding: FragmentIndexBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_index, container, false).apply {
        _binding = FragmentIndexBinding.inflate(inflater, container, false)
        parentViewModel = (activity as MainActivity).viewModel

        // Set RecyclerView
        val rv = findViewById<RecyclerView>(R.id.RecyclerView_activity_track)
        rv.layoutManager = LinearLayoutManager(activity)

        val coinListObserver = Observer<List<CryptoCurrencyDTO>> {
            val sortedList = it.sortedBy { it.marketCap }.reversed()
            mCoinListAdapter = TrackCoinListAdapter(sortedList) {}
            rv.adapter = mCoinListAdapter
        }

        parentViewModel.mAllCryptoCurrenciesDTO.observe(viewLifecycleOwner, coinListObserver)

//        walletButton.setOnClickListener {
//            navigateToWallet()
//        }

//        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun navigateToWallet() {
//        val intent = Intent(this, WalletActivity::class.java)
//        startActivity(intent)
    }

}