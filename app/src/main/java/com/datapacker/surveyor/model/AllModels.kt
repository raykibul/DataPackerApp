package com.datapacker.surveyor.model

import java.io.Serializable

data class LoginBody(
    var email :String,
    var password : String
);

data class Surveyor (

    var email :String?,
    var user_name : String?,
    var first_name : String?,
    var start_date : String?,
    var zilla : String?,
    var upazilla : String?,
    var phoneNumber : String?,
    var name : String?,

);

