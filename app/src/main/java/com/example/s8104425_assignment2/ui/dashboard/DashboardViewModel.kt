package com.example.s8104425_assignment2.ui.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.s8104425_assignment2.model.SportEntity
import com.example.s8104425_assignment2.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
//handle business logi for dashboard and separates success and error cases
class DashboardViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    //liveData to hold list of sports entities for UI
    val dashboardLiveData = MutableLiveData<List<SportEntity>>()

    //liveData to hold error messages for UI
    val errorLiveData = MutableLiveData<String>()

    //fetch dashboard from Api
    fun fetchDashboard(keypass: String) {
        viewModelScope.launch { //coroutine tied to ViewModel
            try {
                val response = repository.getDashboard(keypass) //api call
                if (response.isSuccessful) {
                    //post entities to LiveDATA. empty list if null
                    dashboardLiveData.postValue(response.body()?.entities ?: emptyList())
                } else {
                    //HTTP error
                    errorLiveData.postValue("Failed to fetch dashboard: ${response.code()}")
                }
            } catch (e: Exception) {
                //network or unexpected error
                errorLiveData.postValue("Error: ${e.message}")
            }
        }
    }
}