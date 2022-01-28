package com.example.apipractice.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apipractice.model.ShibaRepo
import com.example.apipractice.util.ViewState
import kotlinx.coroutines.launch

class shibeViewModel: ViewModel() {

    private val _viewState = MutableLiveData<ViewState>(ViewState.Loading)
    private val _count = MutableLiveData(0)
    private var count = 0
    fun getCount(): LiveData<Int>{
        return _count
    }


    val viewState : LiveData<ViewState> get() = _viewState
    fun updateCount() {
        count += 1
        _count.value = count
        println(count)
    }

    init{
        viewModelScope.launch{
            println()
            val state = try {
                val urls = ShibaRepo.getShibes(_count.value ?: 1)
                ViewState.Success(urls)
            }catch(ex: Exception) {
                ViewState.Error(ex.message ?: "Something went wrong")
            }
            _viewState.value = state
        }
    }
}

