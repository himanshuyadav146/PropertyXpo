package com.example.propertyxpo.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.propertyxpo.common.models.Event
import com.example.propertyxpo.common.models.UiState
import com.example.propertyxpo.common.utils.SingleLiveEvent

abstract class BaseViewModel : ViewModel(){
    val singleEventLiveData = SingleLiveEvent<Event>()
    protected val mFullPageUiState = MutableLiveData<UiState>()
    val fullPageUiState : LiveData<UiState>
        get() = mFullPageUiState
}