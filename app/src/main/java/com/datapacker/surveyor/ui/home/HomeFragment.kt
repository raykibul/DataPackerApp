package com.datapacker.surveyor.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.datapacker.surveyor.R
import com.datapacker.surveyor.databinding.HomeFragmentBinding


import com.datapacker.surveyor.model.HomeButton
import com.datapacker.surveyor.model.HomeButtonType

class HomeFragment : Fragment() {
    private var _binding: HomeFragmentBinding? = null

    private val bd get() = _binding!!
    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(layoutInflater)
        val view = bd.root

        bd.homeButtonRecycler.setHasFixedSize(true)
        bd.homeButtonRecycler.layoutManager = GridLayoutManager(requireActivity(),2)
        var listofButtons: MutableList<HomeButton> = ArrayList()

        listofButtons.add(HomeButton("নতুন ফর্ম",resources.getDrawable(R.drawable.logo_newsurvey),HomeButtonType.NEW_SURVEY))
        listofButtons.add(HomeButton("ফর্ম পাঠান",resources.getDrawable(R.drawable.logo_uploadsurvey),HomeButtonType.NEW_SURVEY))
        listofButtons.add(HomeButton("এডিট ফর্ম",resources.getDrawable(R.drawable.logo_editsurvey),HomeButtonType.NEW_SURVEY))
        listofButtons.add(HomeButton("পাঠানো ফর্ম",resources.getDrawable(R.drawable.logo_sentform),HomeButtonType.NEW_SURVEY))
        listofButtons.add(HomeButton("ইন্সট্রাকশন",resources.getDrawable(R.drawable.logo_instruction),HomeButtonType.NEW_SURVEY))
        listofButtons.add(HomeButton("লগ আউট",resources.getDrawable(R.drawable.logo_logout),HomeButtonType.NEW_SURVEY))
        var adapter = HomeBtnAdapter(listofButtons,requireActivity())
        bd.homeButtonRecycler.adapter= adapter



        return view
    }


}