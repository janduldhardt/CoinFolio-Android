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
import com.example.coinfolio.representation.viewmodels.UserCryptoCurrencyViewModel
import com.example.coinfolio.utils.toCurrencyString
import com.example.coinfolio.utils.toPercentString
import com.example.coinfolio.utils.toString8Decimals
import java.math.BigDecimal

class WalletCoinListAdapter(
    private val coinList: List<UserCryptoCurrencyViewModel>,
    val listener: (UserCryptoCurrencyViewModel) -> Unit
) : RecyclerView.Adapter<WalletCoinListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WalletCoinListAdapter.ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_wallet, parent, false)
        )

    override fun onBindViewHolder(holder: WalletCoinListAdapter.ViewHolder, position: Int) {
        holder.bindItems(coinList[position])
    }

    override fun getItemCount(): Int = coinList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val coinName: TextView = itemView.findViewById(R.id.textView_currency_name)
        private val coinAmount: TextView = itemView.findViewById(R.id.textView_currency_amount)
        private val coinPrice: TextView = itemView.findViewById(R.id.textView_currency_price)
        private val coinLogo: ImageView = itemView.findViewById(R.id.imageView_currency_logo)
        private val coinProfit: TextView =
            itemView.findViewById(R.id.textView_currency_price_profit)
        private val trendImageView: ImageView =
            itemView.findViewById(R.id.imageView_wallet_item_profit_sign)

        fun bindItems(coin: UserCryptoCurrencyViewModel) = with(itemView) {
            coinName.text = "${coin.cryptoCurrencyName}"
            coinPrice.text = "${coin.usdAmount.toCurrencyString()}"
            coinAmount.text = "${coin.coinSymbol} ${coin.amount.toString8Decimals()}"
            coinProfit.text = "${coin.getProfitInPercent().toPercentString()}"
            val profit = coin.getProfitInPercent()
            val isPos = profit >= BigDecimal(0)

            val textColor =
                if (isPos) context.getColor((R.color.textColorGreen)) else context.getColor((R.color.textColorRed))
            coinProfit.setTextColor(textColor)

            (if (isPos) R.drawable.ic_baseline_trending_up_24 else R.drawable.ic_baseline_trending_down_24).also {
                trendImageView.setImageResource(
                    it
                )
            }
            trendImageView.setColorFilter(textColor)

            val circularProgressDrawable = CircularProgressDrawable(context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()

            Glide.with(itemView).load(coin.logoUrl).fitCenter()
                .placeholder(circularProgressDrawable).into(coinLogo)

            setOnClickListener { listener(coin) }
        }
    }
}