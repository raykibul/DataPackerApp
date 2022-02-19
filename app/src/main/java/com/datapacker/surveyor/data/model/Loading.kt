package com.datapacker.surveyor.data.model

import android.app.Dialog
import android.content.Context
import android.view.ViewGroup
import android.widget.TextView
import com.datapacker.surveyor.R


class Loading {

   companion object {

       fun getLoadingDialog(context: Context, text: String): Dialog {
           var dialog = Dialog(context)
           dialog!!.setContentView(R.layout.loading_screen)
           var loadingText: TextView = dialog!!.findViewById(R.id.text_loading)
           loadingText.setText(text)
           dialog!!.setCancelable(false)
           var window = dialog!!.window
           window?.setLayout(
               ViewGroup.LayoutParams.MATCH_PARENT,
               ViewGroup.LayoutParams.WRAP_CONTENT
           )
           return dialog

       }
   }
}