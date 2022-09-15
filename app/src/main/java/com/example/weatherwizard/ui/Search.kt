package com.example.weatherwizard.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.weatherwizard.R
import com.example.weatherwizard.databinding.SearchFragmentBinding
import com.example.weatherwizard.util.CityListener
import com.example.weatherwizard.util.RecycleViewAdapter

class Search : Fragment() {

    private lateinit var viewModel: SearchViewModel
    private lateinit var binding: SearchFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.search_fragment, container, false)

        binding.downButton.setOnClickListener {
            it.findNavController().navigate(SearchDirections.actionSearchToHome2(getSearchable()))
        }
        binding.lifecycleOwner = this
        val adapter = RecycleViewAdapter(CityListener { cityName ->
            viewModel.renewSearch(cityName)
        })

        binding.searchList.adapter = adapter

        return binding.root
    }


    /**
     *  returns a string containing searched keyword that update when user updates search box
     */
    private fun getSearchable(): String? {
        var text: String? = null
        binding.searchText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (binding.searchText.text?.isNotEmpty() == true) {
                    text = binding.searchText.text.toString()

                }
            }
        })
        return text
    }


}