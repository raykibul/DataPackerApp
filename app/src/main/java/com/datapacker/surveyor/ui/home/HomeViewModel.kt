package com.datapacker.surveyor.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.datapacker.surveyor.data.Repository

import com.datapacker.surveyor.data.model.Survey
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel : ViewModel() {
    var repository = Repository()
    var survey:MutableLiveData<Response<List<Survey>>> = MutableLiveData()

    fun loadSurvey(){
        viewModelScope.launch {
            var surveyResponse = repository.LoadSurvey()
            survey.postValue(surveyResponse)
        }

    }



}