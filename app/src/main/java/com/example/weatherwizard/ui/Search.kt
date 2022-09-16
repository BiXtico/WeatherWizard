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
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
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

        binding.lifecycleOwner = this

        val adapter = RecycleViewAdapter(CityListener { cityName ->
            findNavController().navigate(SearchDirections.actionSearchToHome2(cityName))
        })
        binding.searchList.adapter = adapter

        viewModel.nextDaysData.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        setClickListeners()
        getSearchable()

        return binding.root
    }

    /**
     * setting the Fragment Click Listeners
     */
    private fun setClickListeners() {
        binding.downButton.setOnClickListener {
            it.findNavController().navigate(SearchDirections.actionSearchToHome2(null))
        }
    }

    /**
     *  calls the viewModel to renew search data with the new keyword that updates when the user updates search box
     */
    private fun getSearchable(){
        binding.searchText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {}

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (binding.searchText.text?.isNotEmpty() == true) {
                    viewModel.renewSearch(binding.searchText.text.toString())
                }
            }
        })
    }


}