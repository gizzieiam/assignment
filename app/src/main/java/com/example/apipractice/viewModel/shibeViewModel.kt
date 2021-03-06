package com.example.apipractice.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apipractice.model.ShibaRepo
import com.example.apipractice.util.ViewState
import kotlinx.coroutines.launch

class shibeViewModel: ViewModel() {


    val _viewState = MutableLiveData<ViewState>(ViewState.Loading)
    private val _count = MutableLiveData(0)
    private var count = 0
    val viewState : LiveData<ViewState> get() = _viewState

    fun getCount(): LiveData<Int>{ return _count }

    fun updateCount(increment: Boolean) {
        if (increment){
            if (count +1 <= 100) {
                count += 1
            }
        }else{
            if (count -1 >= 0) {
                count -= 1
            }
        }
        _count.value = count
    }
    fun passArgs(count: Int){
        println(count)
        viewModelScope.launch{
            val state = try {
                val urls = ShibaRepo.getShibes(count)
                ViewState.Success(urls)
            }catch(ex: Exception) {
                ViewState.Error(ex.message ?: "Something went wrong")
            }
            _viewState.value = state
        }
    }
}

