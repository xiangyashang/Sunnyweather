package com.sunnyweather.android.logic

import android.util.Log
import androidx.lifecycle.liveData
import com.sunnyweather.android.logic.model.Place
import com.sunnyweather.android.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher
import java.lang.Exception

object Repository {

    fun searchPlaces(query: String) = liveData(Dispatchers.IO){
        Log.d("Replace",query.toString())
        val result = try {
            val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
            Log.d("Reeeplace",placeResponse.status.toString())
            if (placeResponse.status == "OK"){
                val place = placeResponse.places
                Result.success(place)
            } else {
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<List<Place>>(e)
        }

        Log.d("result",result.toString())
        emit(result)
    }

}