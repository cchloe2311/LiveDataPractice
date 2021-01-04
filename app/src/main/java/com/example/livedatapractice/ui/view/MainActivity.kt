package com.example.livedatapractice.ui.view

import android.gesture.GestureOverlayView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.livedatapractice.R
import com.example.livedatapractice.data.model.DailyWeather
import com.example.livedatapractice.ui.adapter.DailyWeatherInfoItemAdapter
import com.example.livedatapractice.ui.viewmodel.DailyWeatherViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() { // CoroutineScope
    private lateinit var dailyWeatherViewModel: DailyWeatherViewModel
    private val dailyWeatherInfoItemAdapter = DailyWeatherInfoItemAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dailyWeatherViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(DailyWeatherViewModel::class.java)

        viewPager_dailyWeatherInfo.apply {
            adapter = dailyWeatherInfoItemAdapter

            offscreenPageLimit = 3
            val pageMargin = resources.getDimensionPixelOffset(R.dimen.pageMargin)
            val offsetBetweenPages = resources.getDimensionPixelOffset(R.dimen.offsetBetweenPages)
            setPageTransformer { page, position ->
                val viewPager = page.parent.parent as ViewPager2
                val offset = position * -(2 * offsetBetweenPages + pageMargin)
                if (viewPager.orientation == GestureOverlayView.ORIENTATION_HORIZONTAL) {
                    if (ViewCompat.getLayoutDirection(viewPager) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                        page.translationX = -offset
                    } else {
                        page.translationX = offset
                    }
                } else {
                    page.translationY = offset
                }
            }
        }

        // [LiveData] #2. @View - ViewModel의 LiveData 구독 (observe 메소드 사용)
        dailyWeatherViewModel.dailyWeathers.observe(this, Observer<ArrayList<DailyWeather>> {
            // [LiveData] #4. @View - observe 시 UI update 코드 작성
            dailyWeatherInfoItemAdapter.apply {
                resetDailyWeathers(it)
                notifyDataSetChanged()
            }
        })
    }
}
