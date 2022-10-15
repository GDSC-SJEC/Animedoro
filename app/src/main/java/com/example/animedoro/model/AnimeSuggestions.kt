package com.example.animedoro.model

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

@SuppressLint("SupportAnnotationUsage")
data class AnimeSuggestions(@StringRes val animeName: String, @StringRes val animeLink: String, @DrawableRes val animeImage: Int)