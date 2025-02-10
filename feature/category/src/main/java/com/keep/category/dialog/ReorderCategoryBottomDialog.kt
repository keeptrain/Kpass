package com.keep.category.dialog

import android.app.Dialog
import android.graphics.Outline
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import androidx.annotation.Px
import androidx.core.view.updateLayoutParams
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.withStarted
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.keep.category.CategoryViewModel
import com.keep.category.adapter.CategoryAdapter
import com.keep.category.adapter.CategoryAdapterEvent
import com.keep.category.adapter.CustomItemTouchHelperCallback
import com.keep.password.feature.category.R
import com.keep.password.feature.category.databinding.FragmentReorderBottomSheetDialogBinding
import kotlinx.coroutines.launch


class ReorderCategoryBottomDialog(
    val viewModel: CategoryViewModel,
    val categoryAdapter: CategoryAdapter,
    val listener: CategoryAdapterEvent
) : BottomSheetDialogFragment() {

    private var _binding: FragmentReorderBottomSheetDialogBinding? = null
    private val binding get() = _binding!!

    private val customItemTouchHelperCallback by lazy {
        CustomItemTouchHelperCallback(viewModel)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            setFullScreen()
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

        //val callback = CustomItemTouchHelperCallback(viewModel)
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

        setupToolbar(customItemTouchHelperCallback,recyclerView)

    }

    fun Dialog.setFullScreen(
        @Px cornerRadius: Int = 15,
        skipCollapsed: Boolean = true,
    ) {
        check(this is BottomSheetDialog) {
            ReorderCategoryBottomDialog(viewModel,categoryAdapter,listener)
        }

        lifecycleScope.launch {
            withStarted {
                val bottomSheetLayout = findViewById<ViewGroup>(com.google.android.material.R.id.design_bottom_sheet) ?: return@withStarted
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

                val bottomSheetCallback = object : BottomSheetBehavior.BottomSheetCallback() {

                    override fun onStateChanged(bottomSheet: View, newState: Int) {

                    }

                    override fun onSlide(bottomSheet: View, slideOffset: Float) {
                        // Do something for slide offset.
                    }
                }

                // To add the callback:
                behavior.addBottomSheetCallback(bottomSheetCallback)
            }
        }
    }

    private fun setupToolbar(callback : CustomItemTouchHelperCallback,recyclerView: RecyclerView) {
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