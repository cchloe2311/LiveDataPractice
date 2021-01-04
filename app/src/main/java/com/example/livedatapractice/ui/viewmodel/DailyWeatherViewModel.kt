package com.example.livedatapractice.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.livedatapractice.R
import com.example.livedatapractice.data.api.WeatherRemoteDataSource
import com.example.livedatapractice.data.model.DailyTemperature
import com.example.livedatapractice.data.model.DailyWeather
import com.example.livedatapractice.data.model.DailyWeatherInfo
import com.example.livedatapractice.data.model.WeatherForecastInfo
import com.example.livedatapractice.data.repository.WeatherRepository
import com.example.livedatapractice.util.EXCLUDE_PART
import com.example.livedatapractice.util.Resource
import com.example.livedatapractice.util.ResourceProvider
import com.example.livedatapractice.util.UNITS
import kotlinx.coroutines.*

class DailyWeatherViewModel(
        application: Application
) : AndroidViewModel(application) { // ViewModel은 액티비티 수명주기 외부에 존재하기 때문에 UI 컨텍스트를 ViewModel에 저장하면 메모리 릭을 발생시키는 원인이 될 수 있음. 때문에, VM 사용 시 VM에 뷰에 대한 컨텍스트를 저장하면 안됨
                                    // 다만 Application 컨텍스트는 전체 앱의 수명주기를 의미하기 때문에 메모리 릭에 영향을 주지 않으므로 필요 시 AndroidViewModel 클래스(AndroidViewModel has application context)를 사용!
    // We all know having static context instance is evil as it can cause memory leaks!! However, having static Application instance is not as bad as you might think because there is only one Application instance in the running application.

    private val repository: WeatherRepository = WeatherRepository(WeatherRemoteDataSource())
    private val resourceProvider: ResourceProvider = ResourceProvider(getApplication())

    lateinit var weatherForecastInfo: Resource<WeatherForecastInfo>

    // [LiveData] #1. @ViewModel - LiveData 초기화
    val dailyWeathers = MutableLiveData<ArrayList<DailyWeather>>().apply {
        value = arrayListOf()
    }

    private val viewModelJob = SupervisorJob()
    private val jobScope = CoroutineScope(Dispatchers.Main + viewModelJob) // 왜 메인에서 할까? 다른 스레드에서 안하고??

    init {
        getDailyWeathers()
    }

    private fun getDailyWeathers() {
        // [LiveData] #3. @ViewModel - api를 호출해 데이터를 받고 dailyWeather setValue 호출 부분 작성
        jobScope.launch {
            weatherForecastInfo = repository.getWeatherForecastInfo(
                EXCLUDE_PART,
                UNITS,
                "37.566536",
                "126.977966",
                resourceProvider.getStringResource(R.string.OPEN_WEATHER_KEY)
            )

            weatherForecastInfo.data?.let {
                dailyWeathers.value?.clear()
                dailyWeathers.value?.addAll(it.getDailyWeathersForMain())
                dailyWeathers.value = dailyWeathers.value // setValue called
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}