package com.datapacker.surveyor.ui.survey

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.datapacker.surveyor.data.model.Available_Options
import com.datapacker.surveyor.data.model.Question
import com.datapacker.surveyor.databinding.AvailableQuestionBinding
import com.datapacker.surveyor.databinding.QuestionLaytoutBinding

class AvailableOptionAdapter (val options:MutableList<Available_Options>,var questionType:String ): RecyclerView.Adapter<AvailableOptionAdapter.viewHolder>() {

    inner class  viewHolder(private val bd: AvailableQuestionBinding) : RecyclerView.ViewHolder(bd.root) {
        fun bind(option: Available_Options){
           if (questionType=="direct_answer"){
               bd.directAnswerEditText.visibility=View.VISIBLE
               bd.checkBox.visibility=View.GONE
               bd.imageCheckboxGroup.visibility=View.GONE
               bd.radioGroup.visibility= View.GONE


           }else if (questionType=="mcq_text"){
               bd.checkBox.visibility=View.VISIBLE
               bd.checkBox.text= option.value
               bd.imageCheckboxGroup.visibility=View.GONE
               bd.directAnswerEditText.visibility=View.GONE
               bd.radioGroup.visibility=View.GONE
           }else if (questionType=="boolean"){
               bd.checkBox.visibility=View.GONE
               bd.imageCheckboxGroup.visibility=View.GONE
               bd.radioGroup.visibility= View.VISIBLE
               bd.directAnswerEditText.visibility=View.GONE
           }else if (questionType=="mcq_image"){
               bd.checkBox.visibility=View.GONE
               bd.imageCheckboxGroup.visibility=View.VISIBLE
               bd.radioGroup.visibility= View.GONE
               bd.directAnswerEditText.visibility=View.GONE
               Glide.with(bd.root).load(option.value).into(bd.imageView3)
           }else if (questionType=="mcq_others"){
               bd.checkBox.visibility=View.VISIBLE
               bd.checkBox.text= option.value
               bd.imageCheckboxGroup.visibility=View.GONE
               bd.directAnswerEditText.visibility=View.VISIBLE
           }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var availableQuestionBinding = AvailableQuestionBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return viewHolder(availableQuestionBinding)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
       holder.bind(option = options!!.get(position))
    }

    override fun getItemCount(): Int {

            return  options.size
    }

}