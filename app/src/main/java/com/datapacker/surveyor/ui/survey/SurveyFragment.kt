package com.datapacker.surveyor.ui.survey

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.get
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.SCROLL_STATE_DRAGGING
import com.datapacker.surveyor.R
import com.datapacker.surveyor.data.model.Loading
import com.datapacker.surveyor.data.model.MyAlert
import com.datapacker.surveyor.databinding.HomeFragmentBinding
import com.datapacker.surveyor.databinding.SuveyFragmentBinding
import com.datapacker.surveyor.ui.home.HomeViewModel

class SurveyFragment : Fragment() {
    private   val TAG = "SurveyFragment"
    companion object {
        fun newInstance() = SurveyFragment()
    }
    private var _binding: SuveyFragmentBinding? = null
    private lateinit var viewModel: SuveyViewModel
    private val bd get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SuveyFragmentBinding.inflate(layoutInflater)
        val view = bd.root
        viewModel= ViewModelProvider(requireActivity()).get( SuveyViewModel::class.java)

        viewModel.loadSurvey()

        viewModel.survey.observe(viewLifecycleOwner, Observer { response->


            if (response.isSuccessful){
                if (response.body()!=null){
                    var survey = response.body()!!.get(0)
                    MyAlert.success("সার্ভে ডাটা লোড করা হইছে। ","Total questions: "+survey.question_set!!.size+ " available"+survey.question_set!!.get(0).available_options!!.size,requireContext())
                    val adapter = ViewPagerAdapter(survey.question_set!!,requireActivity())
                    bd.viewpager.adapter= adapter
                }else
                    Log.e(TAG, "onCreateView: NULL SURVEY" )
            }else{
                Log.e(TAG, "onCreateView: NOT SUCEESSFULL "+response.code()+ response.message()+response.raw())
            }
        })

       /* bd.viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                  bd.viewpager.isUserInputEnabled = !(state == SCROLL_STATE_DRAGGING && bd.viewpager.currentItem == 0)
            }
        })*/



        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SuveyViewModel::class.java)

    }

}