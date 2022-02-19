package com.datapacker.surveyor.data.model

import android.content.Context
import com.datapacker.surveyor.R

class Constant {

    companion object{
        const val  TOKENSAVE="TOKEN_SAVED"
        const val BASE_URL = "https://datapacker.raykibul.com/"
        const val SURVEYOR = "SURVEYOUR_PROFILE_DATA"


        fun getHomeButtonList(context: Context):MutableList<HomeButton> {
            var listofButtons: MutableList<HomeButton> = ArrayList()

            listofButtons.add(
                HomeButton("নতুন ফর্ম",context.resources.getDrawable(R.drawable.logo_newsurvey),
                    HomeButtonType.NEW_SURVEY)
            )
            listofButtons.add(
                HomeButton("ফর্ম পাঠান",context.resources.getDrawable(R.drawable.logo_uploadsurvey),
                    HomeButtonType.NEW_SURVEY)
            )
            listofButtons.add(
                HomeButton("এডিট ফর্ম",context.resources.getDrawable(R.drawable.logo_editsurvey),
                    HomeButtonType.NEW_SURVEY)
            )
            listofButtons.add(
                HomeButton("পাঠানো ফর্ম",context.resources.getDrawable(R.drawable.logo_sentform),
                    HomeButtonType.NEW_SURVEY)
            )
            listofButtons.add(
                HomeButton("ইন্সট্রাকশন",context.resources.getDrawable(R.drawable.logo_instruction),
                    HomeButtonType.NEW_SURVEY)
            )
            listofButtons.add(
                HomeButton("লগ আউট",context.resources.getDrawable(R.drawable.logo_logout),
                    HomeButtonType.NEW_SURVEY)
            )
            return listofButtons
        }


    }
}