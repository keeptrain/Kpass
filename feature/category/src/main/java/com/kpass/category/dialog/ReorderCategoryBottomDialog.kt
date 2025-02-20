package com.kpass.category.dialog

import android.app.Dialog
import android.graphics.Outline
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import androidx.annotation.Px
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.withStarted
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager

import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kpass.category.CategoryViewModel
import com.kpass.category.adapter.CategoryAdapter
import com.kpass.category.adapter.CustomItemTouchHelperCallback
import com.kpass.category.adapter.NoCategoryAdapterEvent
import com.kpass.feature.category.R
import com.kpass.feature.category.databinding.FragmentReorderBottomSheetDialogBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ReorderCategoryBottomDialog :
    BottomSheetDialogFragment() {

    private var _binding: FragmentReorderBottomSheetDialogBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CategoryViewModel by viewModels()

    private val categoryAdapter: CategoryAdapter = CategoryAdapter(
        NoCategoryAdapterEvent,
    )

    private val customItemTouchHelperCallback by lazy {
        CustomItemTouchHelperCallback(viewModel)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            setFullScreen()
            viewModel.changeUiStateToReorder()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReorderBottomSheetDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.rvReorder
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = categoryAdapter

        val itemTouchHelper = ItemTouchHelper(customItemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.categories.observe(this@ReorderCategoryBottomDialog) { listCategory ->
                    categoryAdapter.submitList(
                        viewModel.generateCategoryAdapterList(listCategory)
                    )
                }
            }
        }

        setupToolbar(customItemTouchHelperCallback)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun Dialog.setFullScreen(
        @Px cornerRadius: Int = 15,
        skipCollapsed: Boolean = true,
    ) {
        check(this is BottomSheetDialog) {
            ReorderCategoryBottomDialog()
        }

        lifecycleScope.launch {
            withStarted {
                val bottomSheetLayout = findViewById<ViewGroup>(com.google.android.material.R.id.design_bottom_sheet)
                    ?: return@withStarted

                with(bottomSheetLayout) {
                    updateLayoutParams {
                        height = ViewGroup.LayoutParams.MATCH_PARENT
                    }
                    clipToOutline = true
                    outlineProvider = object : ViewOutlineProvider() {
                        override fun getOutline(view: View, outline: Outline) {
                            outline.setRoundRect(
                                0,
                                0,
                                view.width,
                                view.height + cornerRadius,
                                cornerRadius.toFloat()
                            )
                        }
                    }
                }

                behavior.state = BottomSheetBehavior.STATE_EXPANDED
                behavior.skipCollapsed = skipCollapsed

            }
        }
    }

    private fun setupToolbar(callback : CustomItemTouchHelperCallback) {
        binding.appbar.apply {
            setNavigationOnClickListener {
                onDestroy()
                dismiss()
            }

            setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.action_save -> {
                        // Handle save
                        callback.onSaveClicked()
                        dismiss()
                        true
                    }
                    else -> false
                }
            }
        }
    }
}