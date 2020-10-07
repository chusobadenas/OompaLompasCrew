package com.jesusbadenas.oompaloompascrew.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.jesusbadenas.oompaloompascrew.R
import com.jesusbadenas.oompaloompascrew.databinding.DetailFragmentBinding

class DetailFragment : Fragment() {

    private lateinit var binding: DetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Data binding
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_fragment, container, false)
        binding.lifecycleOwner = this

        return binding.root
    }
}
