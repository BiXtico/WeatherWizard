package com.example.weatherwizard.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.weatherwizard.R
import com.example.weatherwizard.databinding.HomeFragmentBinding

class Home : Fragment() {
    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater,R.layout.home_fragment,container,false)

        binding.searchIcon.setOnClickListener{
            it.findNavController().navigate(HomeDirections.actionHome2ToSearch())
        }

        var args = arguments?.let { HomeArgs.fromBundle(it) }

        //use the argument in the view model to get result from API



        return binding.root
    }

}