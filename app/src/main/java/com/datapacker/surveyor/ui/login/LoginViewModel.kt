package com.datapacker.surveyor.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.datapacker.surveyor.data.Repository
import com.datapacker.surveyor.model.LoginBody
import com.datapacker.surveyor.model.Surveyor
import com.datapacker.surveyor.model.Token

import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel( ) : ViewModel() {

  val parsedToken : MutableLiveData<Response<Token>> = MutableLiveData()
  val surveyor : MutableLiveData<Response<Surveyor>> = MutableLiveData()

  var repository = Repository()
  var TAG="LOGINVIEWMODEL"

   fun parseToken(loginBody: LoginBody){

       viewModelScope.launch {
           var token: Response<Token> = repository.parseToken(loginBody = loginBody)
           Log.e(TAG, "loginNow: "+token )
           parsedToken.postValue(token)

       }
   }


    fun loadSurveyor(){

        viewModelScope.launch {
            var surveyorr: Response<Surveyor> = repository.loadSurveyor()
            Log.e(TAG, "surveyour: "+surveyor )
            surveyor.postValue(surveyorr)

        }
    }
}

