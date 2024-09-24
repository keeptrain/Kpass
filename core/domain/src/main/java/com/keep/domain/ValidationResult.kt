package com.keep.domain

import com.keep.common.ui.UiText

data class ValidationResult (
    val successful: Boolean,
    val errorMessage: UiText? = null
)