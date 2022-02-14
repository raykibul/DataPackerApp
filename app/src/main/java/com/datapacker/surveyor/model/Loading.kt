package com.datapacker.surveyor.model

import android.app.Dialog
import android.content.Context
import android.view.ViewGroup
import android.widget.TextView
import com.datapacker.surveyor.R


class Loading {
    companion object {

        var dialog:Dialog ? =null

        fun startLoading(context:Context, text :String){

            dialog= Dialog(context)
            dialog!!.setContentView(R.layout.loading_screen)
            var loadingText:TextView =  dialog!!.findViewById(R.id.text_loading)
            loadingText.setText(text)
            dialog!!.setCancelable(false)


            var window = dialog!!.window
            window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
            dialog!!.show()

        }

        fun stopLoading(){
            if (dialog!=null)
                dialog!!.dismiss()
        }
    }
}