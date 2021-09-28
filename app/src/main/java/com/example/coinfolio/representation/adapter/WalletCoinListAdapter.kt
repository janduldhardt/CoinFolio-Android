package com.example.coinfolio.representation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.coinfolio.R
import com.example.coinfolio.data.dto.CryptoCurrencyDTO

class WalletCoinListAdapter (
    private val coinList: List<CryptoCurrencyDTO>,
    val listener: (CryptoCurrencyDTO) -> Unit
    ) : RecyclerView.Adapter<WalletCoinListAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletCoinListAdapter.ViewHolder =
            ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_wallet, parent, false)
            )

        override fun onBindViewHolder(holder: WalletCoinListAdapter.ViewHolder, position: Int) {
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

                val circularProgressDrawable = CircularProgressDrawable(context)
                circularProgressDrawable.strokeWidth = 5f
                circularProgressDrawable.centerRadius = 30f
                circularProgressDrawable.start()

                Glide.with(itemView).load(coin.imageUri).centerCrop()
                    .placeholder(circularProgressDrawable).into(coinLogo)

                setOnClickListener { listener(coin) }
            }
        }
}