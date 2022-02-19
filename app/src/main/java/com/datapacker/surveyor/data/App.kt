package com.datapacker.surveyor.data

import android.app.Application

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class App : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
   /* val database by lazy { AutoRechargeDatabase.getDatabase(this,applicationScope) }*/
/*   val myrepository by lazy { Repository(database.operatorDao(),database.sendflexiDao(),database.processDao(),database.operatorSMSDao()) }*/

    override fun onCreate() {
        super.onCreate()

    }

}