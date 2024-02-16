package com.example.onlineshop.ui.nav

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.onlineshop.R
import com.example.onlineshop.databinding.FragmentNavigationBinding
import com.example.onlineshop.ui.OnlineShopApp

class NavigationFragment : Fragment() {
    private var _binding: FragmentNavigationBinding? = null
    private val binding get() = _binding!!

    private val component by lazy {
        (requireActivity().application as OnlineShopApp).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNavigationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navHostFragment = childFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.navView.setupWithNavController(navController)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}