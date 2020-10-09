package com.jesusbadenas.oompaloompascrew.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.jesusbadenas.oompaloompascrew.R
import com.jesusbadenas.oompaloompascrew.data.entities.OompaLoompa
import com.jesusbadenas.oompaloompascrew.databinding.ListFragmentBinding
import com.jesusbadenas.oompaloompascrew.navigation.Navigator
import com.jesusbadenas.oompaloompascrew.util.clearAndAdd
import com.jesusbadenas.oompaloompascrew.util.hideKeyboard
import com.jesusbadenas.oompaloompascrew.util.showError
import com.jesusbadenas.oompaloompascrew.viewmodel.ListViewModel
import com.jesusbadenas.oompaloompascrew.viewmodel.ListViewModel.Companion.MAX_SIZE
import com.jesusbadenas.oompaloompascrew.viewmodel.ListViewModel.Companion.PAGE_SIZE
import kotlinx.android.synthetic.main.list_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : Fragment(), OLAdapter.OnItemClickListener {

    private val navigator: Navigator by inject()
    private val olAdapter: OLAdapter by inject()
    private val viewModel: ListViewModel by viewModel()

    private lateinit var binding: ListFragmentBinding
    private lateinit var layoutManager: LinearLayoutManager

    private var currentList = mutableListOf<OompaLoompa>()

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

        setupViews(binding.root)
        subscribe()

        return binding.root
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

    private fun setupViews(view: View) {
        // Recycler view
        layoutManager = LinearLayoutManager(context)
        olAdapter.onItemClickListener = this
        view.findViewById<RecyclerView>(R.id.list_rv).apply {
            layoutManager = this@ListFragment.layoutManager
            adapter = olAdapter
            addOnScrollListener(scrollListener)
        }

        // Swipe refresh
        view.findViewById<SwipeRefreshLayout>(R.id.swipe_container).apply {
            setColorSchemeResources(R.color.colorPrimary)
            setOnRefreshListener {
                viewModel.loadFirst()
            }
        }

        // Filter
        view.findViewById<EditText>(R.id.search_et).setOnEditorActionListener { tView, action, _ ->
            if (action == EditorInfo.IME_ACTION_DONE) {
                filter(tView.text)
                hideKeyboard(tView)
                true
            } else {
                false
            }
        }
    }

    private fun filter(text: CharSequence) {
        olAdapter.filter.listToFilter.clearAndAdd(currentList)
        olAdapter.filter.filter(text)
    }

    private fun stopRefreshing() {
        if (swipe_container.isRefreshing) {
            swipe_container.isRefreshing = false
        }
    }

    private fun subscribe() {
        viewModel.list.observe(viewLifecycleOwner) { list ->
            loadOLList(list)
        }
        viewModel.uiError.observe(viewLifecycleOwner) { uiError ->
            stopRefreshing()
            showError(uiError)
        }
    }

    private fun loadOLList(list: List<OompaLoompa>) {
        stopRefreshing()
        currentList = mutableListOf<OompaLoompa>().apply { addAll(list) }
        olAdapter.submitList(currentList)
    }

    override fun onItemClicked(id: Int) {
        navigator.navigateToDetail(this, id)
    }
}
