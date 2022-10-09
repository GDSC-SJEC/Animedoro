package com.example.animedoro.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Session(@StringRes val stringResourceId: Int, @DrawableRes val imageResourceId: Int)
