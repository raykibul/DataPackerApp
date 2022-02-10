package com.datapacker.surveyor.model

class Token {
    var access: String?=null
    var refresh: String?=null
    var detail: String?=null

    companion object {
        var instance: Token? = null

        @JvmName("getInstance1")
        fun getInstance(): Token? {
            if (instance == null) {
                instance = Token()
            }
            return instance
        }
    }
}