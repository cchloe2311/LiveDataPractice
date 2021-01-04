package com.example.livedatapractice.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.livedatapractice.R
import com.example.livedatapractice.data.model.DailyWeather
import kotlinx.android.synthetic.main.item_daily_weather_info.view.*

// 데이터 목록을 아이템 단위의 뷰로 구성
class DailyWeatherInfoItemAdapter: RecyclerView.Adapter<DailyWeatherInfoItemAdapter.DailyWeatherInfoItemViewHolder>() {
    private val dailyWeathers: ArrayList<DailyWeather> = arrayListOf()

    // 어댑터를 통해 만들어진 각 아이템 뷰는 "뷰홀더(ViewHolder)"객체에 저장되어 화면에 표시 (즉, 화면에 표시될 아이템 뷰를 저장하는 객체임)
    class DailyWeatherInfoItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvCurrentTemp: TextView = view.tv_currentTemp
        val tvCurrentState: TextView = view.tv_currentState
        val tvDailyTempInfo: TextView = view.tv_dayilTempInfo
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyWeatherInfoItemViewHolder = DailyWeatherInfoItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                    R.layout.item_daily_weather_info,
                    parent,
                    false
            )
    )

    override fun getItemCount(): Int = dailyWeathers.size

    override fun onBindViewHolder(holder: DailyWeatherInfoItemViewHolder, position: Int) {
        holder.tvCurrentTemp.text = dailyWeathers[position].dailyTemp.getDailyDayTempInfo()
        holder.tvCurrentState.text = dailyWeathers[position].dailyWeatherInfos[0].weatherDescription
        holder.tvDailyTempInfo.text = dailyWeathers[position].dailyTemp.getDailyMaxMinTempInfo()
    }

    fun resetDailyWeathers(dailyWeathers: ArrayList<DailyWeather>) {
        this.dailyWeathers.clear()
        this.dailyWeathers.addAll(dailyWeathers)
    }
}