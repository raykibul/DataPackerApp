package com.datapacker.surveyor.data.model

import android.graphics.drawable.Drawable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import java.io.Serializable

data class LoginBody(
    var email :String,
    var password : String
);



class Survey: Serializable{
    @PrimaryKey
    var id  :Int ? = null
    var name : String?=null
    var question_set: MutableList<Question>?=null

    override fun toString(): String {
         var gson = Gson()
         return gson.toJson(this, Survey::class.java).toString()

    }
}

class Question :Serializable{
    @PrimaryKey
    var id :Int ? = null
    var  question_text:String? = null
    var answer_type: String? = null
    var  serial: String? = null
    var image :String? = null
    var available_options : MutableList<Available_Options> ? = null
    var value: String? =null

}



class Available_Options :Serializable{
    @PrimaryKey(autoGenerate = true)
     var question_id: Int? = -1212
     var value: String? = null
     var post_question :Int? = -1
}


class Survey_Info : Serializable{
    @PrimaryKey(autoGenerate = true)
    var surveyor_id : Int?= null
    var surveyee_name: String? = null
    var surveyee_phone: String? = null
    var surveyee_address: String? = null
    var survey_id: Int? = null


}


class Survey_Answer : Serializable{

    @PrimaryKey(autoGenerate = true)
    var question_id : Int?= null
    var answer_value: String? = null
    var survey_info_id: Int? = null


}




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
    var type: HomeButtonType
)

enum class HomeButtonType{
    NEW_SURVEY,
    EDIT_SURVEY,
    UPLOAD_SUVEY

}