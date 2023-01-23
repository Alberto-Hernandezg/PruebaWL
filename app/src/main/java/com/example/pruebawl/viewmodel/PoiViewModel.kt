package com.example.pruebawl.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.pruebawl.model.Poi
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

class PoiViewModel(private val application: Application) : ViewModel() {

    private var _poiList = MutableLiveData<List<Poi>>()
    var poiList: LiveData<List<Poi>> = _poiList

    init {
        loadData()
    }

    private fun loadData() {
        val assetManager = application.assets
        val inputStream = assetManager.open("pois.json")
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        val jsonObject = JSONObject(jsonString)
        val jsonArray = jsonObject.getJSONArray("list")
        val itemsType = object : TypeToken<List<Poi>>() {}.type
        _poiList.value = Gson().fromJson(jsonArray.toString(), itemsType)
    }
}


