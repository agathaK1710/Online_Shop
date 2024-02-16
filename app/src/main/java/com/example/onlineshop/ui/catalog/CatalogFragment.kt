package com.example.onlineshop.ui.catalog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.onlineshop.databinding.FragmentCatalogBinding
import com.example.onlineshop.ui.OnlineShopApp
import com.example.onlineshop.ui.ViewModelFactory
import javax.inject.Inject

class CatalogFragment : Fragment() {
    private var _binding: FragmentCatalogBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val catalogViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[CatalogViewModel::class.java]
    }

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
       _binding =  FragmentCatalogBinding.inflate(inflater, container, false)
        return binding.root
    }
}