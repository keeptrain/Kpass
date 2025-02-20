package com.kpass.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.keep.common.util.Event
import com.keep.domain.ValidationResult
import com.keep.domain.ui.category.CategoryValidationUseCase
import com.keep.domain.usecase.category.GetCategoryUseCase
import com.keep.model.Category
import com.kpass.category.adapter.CategoryListAdapterItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val useCase: GetCategoryUseCase,
    private val validationUseCase: CategoryValidationUseCase,
): ViewModel() {

    private val _state : MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val state : StateFlow<UiState> = _state.asStateFlow()

    private val _validationResult = MutableLiveData<Event<ValidationResult>>()
    val validationResult : LiveData<Event<ValidationResult>> = _validationResult

    private val _insertResult = MutableLiveData<Event<Boolean>>()
    val insertResult : LiveData<Event<Boolean>> = _insertResult

    private val _resetErrorEvent = MutableLiveData<Event<Unit>>()
    val resetErrorEvent: LiveData<Event<Unit>> = _resetErrorEvent

    val categories: LiveData<List<Category>> = useCase.getCategory().asLiveData()

    suspend fun getLastPosition(): Int {
        return useCase.getLastPosition()
    }

    fun updateCategoryPosition(category: List<Category>) {
        useCase.updateCategoryPosition(category)
    }

    fun deleteCategory(category: Category) {
        useCase.deleteCategory(category)
    }

    fun changeUiStateToDetail() {
        _state.update {
            it.copy(
                currentState = STATE.DETAIL
            )
        }
    }

    fun changeUiStateToReorder() {
        _state.update {
            it.copy(
                currentState = STATE.REORDER
            )
        }
    }

    fun insertCategoryWithFieldsValidation(category: Category) {
        val result = validationUseCase.validateTitle(category.name)
        if (result.successful) {
            viewModelScope.launch {
                useCase.insertCategory(category)
                _insertResult.value = Event(true)
            }
        } else {
            _validationResult.value = Event(result)
        }
    }

    fun isCategoryNameExists(categoryName : String, callback: (Boolean) -> Unit){
        viewModelScope.launch {
            val exists = useCase.getCategory().firstOrNull()?.any {
                it.name.equals(categoryName, ignoreCase = true)
            } == true
            callback(exists)
        }
    }

    fun generateCategoryAdapterList(list: List<Category>): List<CategoryListAdapterItem> {
        val array: MutableList<CategoryListAdapterItem> = mutableListOf()
        if (list.isEmpty()) {
            listOf(CategoryListAdapterItem.EmptyItem())
        } else {
            list.forEach {
                array.add(CategoryListAdapterItem.CategoryItem(it))
            }
        }
        return array
    }

    enum class STATE {
        INITIAL,
        CREATE,
        DETAIL,
        REORDER,
    }

    data class UiState(
        val currentState : STATE = STATE.INITIAL,
    )

}