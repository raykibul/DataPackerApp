package com.datapacker.surveyor.data.model

import android.content.Context
import android.content.SharedPreferences

class Save {


    companion object{
        var sharedprefName :String = "alkdjsfaklsjdluw93089ujdlskajkl"

        fun saveString(  name : String, value :String , context: Context){
            var sharedpref: SharedPreferences = context.getSharedPreferences(sharedprefName,Context.MODE_PRIVATE)
            var editor :SharedPreferences.Editor = sharedpref.edit()
            editor.putString(name,value)
            editor.apply()

        }
        fun getString(name :String, context: Context):String? {
            var sharedpref: SharedPreferences = context.getSharedPreferences(sharedprefName,Context.MODE_PRIVATE)
            return sharedpref.getString(name,null)
        }
    }
}