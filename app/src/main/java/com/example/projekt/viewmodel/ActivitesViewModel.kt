package com.example.projekt.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.projekt.MyApplication
import com.example.projekt.model.User
import com.example.projekt.repository.TrackerRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class ActivitiesViewModelFactory(
    private val repository: TrackerRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ActivitiesViewModel(repository) as T
    }
}

class ActivitiesViewModel(val repository: TrackerRepository) : ViewModel() {
    var userList = MutableLiveData<List<User>>()

    fun readUsers() {
        viewModelScope.launch {
            try {
                val response = repository.getUsers(MyApplication.token)
                if(response.isSuccessful) {
                    userList.value = response.body()
                } else{
                    Log.i("xxx-uvm", response.message())
                }
            } catch (e: Exception) {
                Log.i("xxx", e.toString())
            }
        }
    }


}