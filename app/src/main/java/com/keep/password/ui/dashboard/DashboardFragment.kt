package com.keep.password.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.keep.password.R
import com.keep.password.MainActivity
import com.keep.password.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    private val binding get() = _binding!!

    private val dashboardViewModel : DashboardViewModel by viewModels()

    private val mainActivity by lazy {
        requireActivity() as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupGridCardView()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupActionBar() {
        val toolbar = binding.dashboardToolbar
        mainActivity.setSupportActionBar(toolbar)
    }

    private fun setupToolbar() {
        binding.dashboardToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_search -> {
                    Toast.makeText(requireContext(), "Search", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }

    private fun setupGridCardView() {
        binding.cardview1.setOnClickListener {
            findNavController().navigate(R.id.action_settings_to_manage_category)
        }
    }
}