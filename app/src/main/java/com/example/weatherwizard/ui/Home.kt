package com.example.weatherwizard.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.weatherwizard.R
import com.example.weatherwizard.databinding.HomeFragmentBinding
import com.example.weatherwizard.databinding.WeatherCardBinding

class Home : Fragment() {
    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)

        binding.searchIcon.setOnClickListener {
            it.findNavController().navigate(HomeDirections.actionHome2ToSearch())
        }
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.reloadButton.setOnClickListener {
            viewModel.renewWeatherData(viewModel.searchableText)
        }

        var args = arguments?.get("searchable")

        if (args != null && args is String)
            viewModel.renewWeatherData(args)
        else if (args == null && args is String)
            viewModel.renewWeatherData(viewModel.searchableText)


        return binding.root
    }

}