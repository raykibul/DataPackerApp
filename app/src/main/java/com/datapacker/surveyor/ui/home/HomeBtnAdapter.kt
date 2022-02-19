package com.datapacker.surveyor.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.datapacker.surveyor.R
import com.datapacker.surveyor.data.model.HomeButton
import com.datapacker.surveyor.data.model.HomeButtonInterface

class HomeBtnAdapter(var buttonList:List<HomeButton>, val context : Context, var homeInterFace: HomeButtonInterface) : RecyclerView.Adapter<HomeBtnAdapter.viewHolder>() {

    class viewHolder(val itemView: View, var homeInterFace: HomeButtonInterface, val buttonList: List<HomeButton>) : RecyclerView.ViewHolder(itemView),View.OnClickListener {

        var image: ImageView = itemView.findViewById(R.id.imageView)
        var text: TextView = itemView.findViewById(R.id.button_text)
        var card: CardView = itemView.findViewById(R.id.item_card)


        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

            homeInterFace.onHomeButtonsclicked(buttonList.get(adapterPosition))

        }


    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.home_button_layout,parent,false)
        return viewHolder(view,homeInterFace,buttonList)
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