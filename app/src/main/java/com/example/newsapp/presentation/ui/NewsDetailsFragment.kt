package com.example.newsapp.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.newsapp.data.model.response.Article
import com.example.newsapp.databinding.FragmentNewsDetailsBinding
import com.example.newsapp.presentation.viewmodel.NewsDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailsFragment : Fragment() {

    private lateinit var article: Article
    private lateinit var binding: FragmentNewsDetailsBinding
    private val viewModel: NewsDetailsViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args: NewsDetailsFragmentArgs by navArgs()
        article = args.article
        viewModel.setArticleData(article)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNewsDetailsBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        article.urlToImage?.let {
            Glide.with(requireContext()).load(it).into(binding.imageView)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = NewsDetailsFragment()
    }
}