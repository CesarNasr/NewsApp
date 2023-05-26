package com.example.newsapp.presentation.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.data.model.response.Article
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.presentation.adapters.NewsAdapter
import com.example.newsapp.presentation.utils.UiState
import com.example.newsapp.presentation.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding
    private val viewModel: NewsViewModel by viewModels()

    @Inject
    lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        collectUiStates()

        binding.swipeLayout.setOnRefreshListener {
            viewModel.fetchNews(true)
        }

        val handler = Handler(Looper.getMainLooper())

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            /*     override fun onQueryTextSubmit(query: String): Boolean {

                     if (query.length >= 3) {
                         searchLayout.visibility = View.VISIBLE
                         searchView?.clearFocus()
                         page = 0
                         isLastPage = false
                         presenter.getVoiceRoomCelebritySearchItems(query.trim().toString(), sectionId, page, PAGE_ITEM_SIZE)
                         return false
                     }

                     return true
                 }
     */
            override fun onQueryTextChange(newText: String): Boolean {
                handler.postDelayed({
                    if (newText.length > 2) {
                        viewModel.searchNews(newText)
                    }
                }, 300)

                /*  else if(newText.isEmpty()) {

                   }*/
                return false
            }
        })
    }


    private fun initRecyclerView() {
        binding.newsRecyclerview.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        newsAdapter.setOnItemClickListener {
            //do something

            navigateToNewsDetailsFragment(it)
        }
    }

    private fun navigateToNewsDetailsFragment(article: Article) {
        val bundle = Bundle().apply {
            putSerializable(PARAM_ARTICLE, article)
        }
        findNavController().navigate(
            R.id.action_newsFragment_to_newsDetailsFragment,
            bundle
        )
    }

    private fun collectUiStates() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {
                    viewModel.newsUiState.collect { uiState ->
                        when (uiState) {
                            is UiState.Loaded -> {
                                binding.swipeLayout.isRefreshing = false
                                uiState.data?.let { newsList ->
                                    newsAdapter.setList(newsList.toMutableList())
                                }
                            }

                            is UiState.Error -> {
                                binding.swipeLayout.isRefreshing = false

                                Toast.makeText(requireContext(), uiState.message, Toast.LENGTH_LONG)
                                    .show()
                                // show error dialog if needed using error message : {uiState.message}
                            }

                            UiState.Empty -> {
                            }

                            UiState.Loading -> {
                            }
                        }
                    }
                }

            }
        }
    }

    companion object {
        const val PARAM_ARTICLE = "article"

        @JvmStatic
        fun newInstance() = NewsFragment()
    }
}