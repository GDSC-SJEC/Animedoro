package com.example.animedoro.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Session(@StringRes val stringResourceId: Int,@StringRes val taskResourceId: Int, @DrawableRes val imageResourceId: Int)
