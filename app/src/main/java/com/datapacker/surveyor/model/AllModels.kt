package com.datapacker.surveyor.model

import android.graphics.drawable.Drawable
import java.io.Serializable

data class LoginBody(
    var email :String,
    var password : String
);




class Surveyor: Serializable {

    var email: String?=null
    var user_name: String?=null
    var first_name: String?=null
    var start_date: String?=null
    var zilla: String?=null
    var upazilla: String?=null
    var phoneNumber: String?=null
    var name: String?=null

    fun Surveyor(){

    }

}

data class HomeButton(
    var buttonText:String,
    var buttonImage:Drawable,
    var type:HomeButtonType
)

enum class HomeButtonType{
    NEW_SURVEY,
    EDIT_SURVEY,
    UPLOAD_SUVEY

}