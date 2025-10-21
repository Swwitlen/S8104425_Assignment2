package com.example.s8104425_assignment2.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.s8104425_assignment2.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    //liveData holding the login result:either a keypass or error
    val loginResult = MutableLiveData<Result<String>>() // Holds keypass or error

    //called when user presses login button
    fun login(location: String, username: String, password: String) {
        viewModelScope.launch { //coroutine scope tied to ViewModel
            try {
                val response = repository.login(location, username, password)
                if (response.isSuccessful) {
                    val keypass = response.body()?.keypass ?: "" //get keypass from response
                    loginResult.postValue(Result.success(keypass)) //success
                } else {
                    //non-200 response
                    loginResult.postValue(Result.failure(Exception("Login failed: ${response.code()}")))
                }
            } catch (e: Exception) {
                //network or unexpected exception
                loginResult.postValue(Result.failure(e))
            }
        }
    }
}