package com.example.coinfolio.representation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coinfolio.R
import com.example.coinfolio.data.dto.CryptoCurrencyDTO
import com.squareup.picasso.Picasso

class TrackCoinListAdapter (
    private val coinList: List<CryptoCurrencyDTO>,
    val listener: (CryptoCurrencyDTO) -> Unit
    ) : RecyclerView.Adapter<TrackCoinListAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackCoinListAdapter.ViewHolder =
            ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.track_coin_list_item, parent, false)
            )

        override fun onBindViewHolder(holder: TrackCoinListAdapter.ViewHolder, position: Int) {
            holder.bindItems(coinList[position])
        }

        override fun getItemCount(): Int = coinList.size

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val coinName: TextView = itemView.findViewById(R.id.textView_currency_name)
            private val coinPrice: TextView = itemView.findViewById(R.id.textView_currency_price)
            private val coinLogo: ImageView = itemView.findViewById(R.id.imageView_currency_logo)

            fun bindItems(coin: CryptoCurrencyDTO) = with(itemView) {
                coinName.text = "${coin.name} (${coin.abbreviation})"
                coinPrice.text = "\$${coin.price}"
                Picasso.get().load(coin.imageUri).into(coinLogo);

                setOnClickListener { listener(coin) }
            }
        }
}