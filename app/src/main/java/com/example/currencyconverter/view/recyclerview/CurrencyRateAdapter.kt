package com.example.currencyconverter.view.recyclerview

import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.R
import com.example.currencyconverter.common.inflate
import com.example.currencyconverter.model.CurrencyRateModel
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.currency_rate_item.view.*

class CurrencyRateAdapter(
    private val currencyRate: CurrencyRateModel,
    private val rateUi: RateUI
) :
    RecyclerView.Adapter<CurrencyRateAdapter.CurrencyViewHolder>() {

    private fun currencyList():List<Double>{
        val currencyPosition =ArrayList<Double>()
        if (currencyPosition.isEmpty()){
            currencyPosition.add(0, currencyRate.rates.aUD)
            currencyPosition.add(1, currencyRate.rates.bGN)
            currencyPosition.add(2, currencyRate.rates.bRL)
            currencyPosition.add(3, currencyRate.rates.cAD)
            currencyPosition.add(4, currencyRate.rates.cHF)
            currencyPosition.add(5, currencyRate.rates.cNY)
            currencyPosition.add(6, currencyRate.rates.cZK)
            currencyPosition.add(7, currencyRate.rates.dKK)
            currencyPosition.add(8, currencyRate.rates.gBP)
            currencyPosition.add(9, currencyRate.rates.hKD)
            currencyPosition.add(10, currencyRate.rates.hRK)
            currencyPosition.add(11, currencyRate.rates.hUF)
            currencyPosition.add(12, currencyRate.rates.iDR)
            currencyPosition.add(13, currencyRate.rates.iLS)
            currencyPosition.add(14, currencyRate.rates.iNR)
            currencyPosition.add(15, currencyRate.rates.iSK)
            currencyPosition.add(16, currencyRate.rates.jPY)
            currencyPosition.add(17, currencyRate.rates.kRW)
            currencyPosition.add(18, currencyRate.rates.mXN)
            currencyPosition.add(19, currencyRate.rates.mYR)
            currencyPosition.add(20, currencyRate.rates.nOK)
            currencyPosition.add(21, currencyRate.rates.nZD)
            currencyPosition.add(22, currencyRate.rates.pHP)
            currencyPosition.add(23, currencyRate.rates.pLN)
            currencyPosition.add(24, currencyRate.rates.rON)
            currencyPosition.add(25, currencyRate.rates.rUB)
            currencyPosition.add(26, currencyRate.rates.sEK)
            currencyPosition.add(27, currencyRate.rates.sGD)
            currencyPosition.add(28, currencyRate.rates.tHB)
            currencyPosition.add(29, currencyRate.rates.uSD)
            currencyPosition.add(30, currencyRate.rates.zAR)
        }
        return currencyPosition
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        return CurrencyViewHolder(
            parent.inflate(R.layout.currency_rate_item)
        )
    }

    override fun getItemCount(): Int {
        return rateUi.shortNameList.size
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.currencyRate.setText(currencyList()[position].toString())
        holder.currencyShort.text = rateUi.shortNameList[position]
        holder.currencyFull.text = rateUi.fullNameList[position]
    }

    class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val currencyShort:TextView = itemView.tv_currency_short
        val currencyFull:TextView = itemView.tv_currency_full
        val currencyRate:EditText = itemView.et_currency_rate
        //val currencyFlag:CircleImageView = itemView.cimg_currency_flag
    }
}