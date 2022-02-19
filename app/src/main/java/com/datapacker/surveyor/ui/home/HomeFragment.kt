package com.datapacker.surveyor.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.datapacker.surveyor.R
import com.datapacker.surveyor.data.model.*
import com.datapacker.surveyor.databinding.HomeFragmentBinding


import com.datapacker.surveyor.ui.survey.SurveyFragment

class HomeFragment : Fragment(), HomeButtonInterface {
    private var _binding: HomeFragmentBinding? = null
    private   val TAG = "HomeFragment"
    private val bd get() = _binding!!
    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(layoutInflater)
        val view = bd.root

        viewModel= ViewModelProvider(requireActivity()).get( HomeViewModel::class.java)


        bd.homeButtonRecycler.setHasFixedSize(true)
        bd.homeButtonRecycler.layoutManager = GridLayoutManager(requireActivity(),2)
        var listofButtons: MutableList<HomeButton> = ArrayList(Constant.getHomeButtonList(requireActivity()))
        var adapter = HomeBtnAdapter(listofButtons,requireActivity(),this)
        bd.homeButtonRecycler.adapter= adapter

        return view


    }

    override fun onHomeButtonsclicked(homeButton: HomeButton) {
         if (homeButton.type== HomeButtonType.NEW_SURVEY){
             requireActivity().supportFragmentManager.beginTransaction()
                 .replace(R.id.container, SurveyFragment.newInstance())
                 .commitNow()
         }else if (homeButton.type== HomeButtonType.EDIT_SURVEY){

         }
    }


}