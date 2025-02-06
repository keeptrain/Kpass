package com.keep.domain.ui.category

import com.keep.common.ui.UiText
import com.keep.domain.ValidationResult
import javax.inject.Inject
import com.keep.password.core.common.R

class CategoryValidationUseCaseImpl @Inject constructor() : CategoryValidationUseCase {
    override fun validateTitle(title: String): ValidationResult {
        if (title.isEmpty()) {
            return ValidationResult(false,UiText.StringResource(R.string.cannot_empty))
        }
        return ValidationResult(
            successful = true
        )
    }
}