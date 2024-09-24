package com.keep.domain.ui.category

import com.keep.domain.ValidationResult

interface CategoryValidationUseCase {
    fun validateTitle(title: String): ValidationResult
}