package com.datapacker.surveyor.model

import android.app.Dialog
import android.content.Context
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.contentValuesOf
import com.airbnb.lottie.LottieAnimationView
import com.datapacker.surveyor.R


class MyAlert {


    companion object{

        fun createAlert(title: String,msg:String,context: Context,type:AlertType):Dialog{

            var alert : Dialog = Dialog(context)
            alert.setContentView(R.layout.custom_alert)
            var tv_msg :TextView = alert.findViewById(R.id.textView8)
            var tv_title :TextView = alert.findViewById(R.id.textView7)
            var animation :LottieAnimationView = alert.findViewById(R.id.animation_view)
            var button : Button = alert.findViewById(R.id.button)
            alert.setCancelable(false)

            tv_msg.setText(msg)
            tv_title.setText(title)
            if (type == AlertType.SUCCESS){
                tv_title.setTextColor(context.resources.getColor(R.color.success))
                animation.setAnimation(R.raw.alert_success)
                tv_msg.setTextColor(context.resources.getColor(R.color.purple_700))
            }else if (type==AlertType.ERROR){
                tv_title.setTextColor(context.resources.getColor(R.color.design_default_color_error))
                tv_msg.setTextColor(context.resources.getColor(R.color.purple_700))
                animation.setAnimation(R.raw.cancell_alert)
            }else if (type==AlertType.FAILED){
                tv_title.setTextColor(context.resources.getColor(R.color.failed))
                tv_msg.setTextColor(context.resources.getColor(R.color.purple_700))
                animation.setAnimation(R.raw.cancelled_man)
            }
            button.setOnClickListener {
                alert.dismiss()
            }
            val window: Window? = alert.getWindow()
            window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            return alert

        }


        fun error( title:String,msg:String, context:Context){
           var  dialog : Dialog = createAlert(title,msg,context,AlertType.ERROR)
            dialog.show()
        }
        fun success( title:String, msg:String,  context:Context){
            var  dialog : Dialog = createAlert(title,msg,context,AlertType.SUCCESS)
            dialog.show()
        }
        fun failed( title:String, msg:String,  context:Context){
            var  dialog : Dialog = createAlert(title,msg,context,AlertType.FAILED)
            dialog.show()
        }
    }
}

enum class AlertType{
    ERROR,SUCCESS,FAILED
}