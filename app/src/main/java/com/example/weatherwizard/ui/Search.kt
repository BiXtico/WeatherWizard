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
import com.example.weatherwizard.databinding.SearchFragmentBinding

class Search : Fragment() {

    private lateinit var viewModel: SearchViewModel
    private lateinit var binding: SearchFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.search_fragment,container,false)

        binding.downButton.setOnClickListener{
            it.findNavController().navigate(R.id.action_search_to_home2)
        }

        return binding.root
    }


}