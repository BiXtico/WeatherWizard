package com.example.weatherwizard.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.weatherwizard.R
import com.example.weatherwizard.databinding.SearchFragmentBinding
import com.example.weatherwizard.util.CityListener
import com.example.weatherwizard.util.RecycleViewAdapter


class Search : Fragment() {

    private lateinit var viewModel: SearchViewModel
    private lateinit var binding: SearchFragmentBinding

    @SuppressLint("ClickableViewAccessibility")
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

        binding.root.setOnTouchListener { _, ev ->
            var lastY = 0f
            var nowY = 0f
            when (ev.action) {
                MotionEvent.ACTION_DOWN -> lastY = ev.rawY
                MotionEvent.ACTION_MOVE -> nowY = ev.rawY
                MotionEvent.ACTION_UP -> if (nowY - lastY > 0) {
                    Toast.makeText(context, "swipe rejester", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }

        return binding.root
    }

    fun onTouch(v: View, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {}
            MotionEvent.ACTION_UP -> v.performClick()
        }
        return true
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
    private fun getSearchable() {
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
                    viewModel.renewSearch(binding.searchText.text.toString())
                }
            }
        })
    }


}