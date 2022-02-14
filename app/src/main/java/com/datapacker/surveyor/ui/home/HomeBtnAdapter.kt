package com.datapacker.surveyor.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.datapacker.surveyor.R
import com.datapacker.surveyor.model.HomeButton

class HomeBtnAdapter(var buttonList:List<HomeButton>,val context : Context) : RecyclerView.Adapter<HomeBtnAdapter.viewHolder>() {

    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.imageView)
        var text: TextView = itemView.findViewById(R.id.button_text)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.home_button_layout,parent,false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        var currentButton = buttonList.get(position)
        holder.image.setImageDrawable(currentButton.buttonImage)
        holder.text.setText(currentButton.buttonText)

    }

    override fun getItemCount(): Int {
         return buttonList.size
    }
}