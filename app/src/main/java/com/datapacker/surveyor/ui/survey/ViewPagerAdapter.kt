package com.datapacker.surveyor.ui.survey

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.datapacker.surveyor.data.model.Available_Options
import com.datapacker.surveyor.data.model.Question
import com.datapacker.surveyor.databinding.ViewpagerlayoutBinding

class ViewPagerAdapter(val questionList: List<Question>,val context:Context) : RecyclerView.Adapter<ViewPagerAdapter.viewpagerHolder>() {



    inner class viewpagerHolder( val viewpagerlayoutBinding:  ViewpagerlayoutBinding) : RecyclerView.ViewHolder(viewpagerlayoutBinding.root) {
        fun bind(question: Question) {
            viewpagerlayoutBinding.questionText.text= question.question_text

            if(question.available_options==null||question.available_options?.size==0||question.answer_type=="boolean"||question.answer_type=="direct_answer"){

                var availop = Available_Options()
                availop.value= null
                var listofAvail : MutableList<Available_Options> = ArrayList()
                listofAvail.add(availop)
                question.available_options = ArrayList(listofAvail)
            }

            var availableOptionAdapter = AvailableOptionAdapter(question.available_options!!,question.answer_type!!)
            viewpagerlayoutBinding.availableOptionRecycler.setHasFixedSize(true)
            viewpagerlayoutBinding.availableOptionRecycler.layoutManager=LinearLayoutManager(context)
            viewpagerlayoutBinding.availableOptionRecycler.adapter=availableOptionAdapter

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewpagerHolder {
        var view = ViewpagerlayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  viewpagerHolder(view)

    }

    override fun onBindViewHolder(holder: viewpagerHolder, position: Int) {
        var question = questionList.get(position)
        holder.bind(question)
    }

    override fun getItemCount(): Int {
        return questionList.size
    }
}