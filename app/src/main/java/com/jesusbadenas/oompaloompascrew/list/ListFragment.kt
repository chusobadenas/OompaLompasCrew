package com.jesusbadenas.oompaloompascrew.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jesusbadenas.oompaloompascrew.R
import com.jesusbadenas.oompaloompascrew.data.entities.OompaLoompa
import com.jesusbadenas.oompaloompascrew.databinding.ListFragmentBinding
import com.jesusbadenas.oompaloompascrew.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.list_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : Fragment() {

    private val olAdapter: OLAdapter by inject()
    private val viewModel: ListViewModel by viewModel()

    private lateinit var binding: ListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Data binding
        binding = DataBindingUtil.inflate(inflater, R.layout.list_fragment, container, false)
        binding.lifecycleOwner = this

        // View model
        binding.viewModel = viewModel

        subscribe()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRV()
    }

    private fun setupRV() {
        list_rv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = olAdapter
        }

        swipe_container.setColorSchemeResources(R.color.colorPrimary)
        swipe_container.setOnRefreshListener {
            viewModel.loadOlList()
        }
    }

    private fun subscribe() {
        viewModel.list.observe(viewLifecycleOwner) { list ->
            loadOlList(list)
        }
    }

    private fun loadOlList(list: List<OompaLoompa>) {
        if (swipe_container.isRefreshing) {
            swipe_container.isRefreshing = false
        }
        olAdapter.submitList(list)
    }
}
