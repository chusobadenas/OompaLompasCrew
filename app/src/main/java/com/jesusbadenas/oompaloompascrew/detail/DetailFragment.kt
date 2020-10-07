package com.jesusbadenas.oompaloompascrew.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.jesusbadenas.oompaloompascrew.R
import com.jesusbadenas.oompaloompascrew.databinding.DetailFragmentBinding
import com.jesusbadenas.oompaloompascrew.viewmodel.DetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailFragment : Fragment() {

    private val navArgs: DetailFragmentArgs by navArgs()

    private val viewModel: DetailViewModel by viewModel {
        parametersOf(navArgs.id)
    }

    private lateinit var binding: DetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Data binding
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_fragment, container, false)
        binding.lifecycleOwner = this

        // View model
        binding.viewModel = viewModel

        return binding.root
    }
}
