package com.jesusbadenas.oompaloompascrew.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jesusbadenas.oompaloompascrew.R
import com.jesusbadenas.oompaloompascrew.data.entities.OompaLoompa
import com.jesusbadenas.oompaloompascrew.databinding.ListFragmentBinding
import com.jesusbadenas.oompaloompascrew.navigation.Navigator
import com.jesusbadenas.oompaloompascrew.viewmodel.ListViewModel
import com.jesusbadenas.oompaloompascrew.viewmodel.ListViewModel.Companion.MAX_SIZE
import com.jesusbadenas.oompaloompascrew.viewmodel.ListViewModel.Companion.PAGE_SIZE
import kotlinx.android.synthetic.main.list_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class ListFragment : Fragment(), OLAdapter.OnItemClickListener {

    private val layoutManager: LinearLayoutManager by inject()
    private val navigator: Navigator by inject()
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
        setupViews()
    }

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val firstVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()
            val totalItemCount: Int = layoutManager.itemCount
            val visibleItemCount: Int = layoutManager.childCount

            if (!viewModel.isLoading
                && visibleItemCount + firstVisibleItemPosition >= totalItemCount
                && firstVisibleItemPosition >= 0
                && totalItemCount >= PAGE_SIZE
                && totalItemCount < MAX_SIZE
            ) {
                loadMoreItems()
            }
        }
    }

    private fun loadMoreItems() {
        viewModel.loadMoreItems()
    }

    private fun setupViews() {
        // Recycler view
        olAdapter.onItemClickListener = this
        list_rv.apply {
            layoutManager = this@ListFragment.layoutManager
            adapter = olAdapter
            addOnScrollListener(scrollListener)
        }

        // Swipe refresh
        swipe_container.setColorSchemeResources(R.color.colorPrimary)
        swipe_container.setOnRefreshListener {
            viewModel.loadFirst()
        }
    }

    private fun subscribe() {
        viewModel.list.observe(viewLifecycleOwner) { list ->
            loadOLList(list)
        }
    }

    private fun loadOLList(list: List<OompaLoompa>) {
        if (swipe_container.isRefreshing) {
            swipe_container.isRefreshing = false
        }
        val newList = mutableListOf<OompaLoompa>().apply { addAll(list) }
        olAdapter.submitList(newList)
    }

    override fun onItemClicked(id: Int) {
        navigator.navigateToDetail(this, id)
    }
}
