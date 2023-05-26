package com.example.newsapp.presentation.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

// Binding adapter to show image using Glide
@BindingAdapter("app:imageURL")
fun setImageFromURL(imageView: ImageView, url: String?) {
    url?.let {
        Glide.with(imageView.context)
            .load(it)
            .into(imageView)
    }
}