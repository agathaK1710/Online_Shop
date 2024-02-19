package com.example.onlineshop.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.onlineshop.R
import com.example.onlineshop.databinding.FragmentNavigationBinding
import com.example.onlineshop.databinding.FragmentProductPageBinding
import com.example.onlineshop.ui.catalog.recyclerView.ProductCard

class ProductPageFragment : Fragment() {
    private var _binding: FragmentProductPageBinding? = null
    private val binding get() = _binding!!

    private lateinit var product: ProductCard
    private val navigationArgs : ProductPageFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        product = navigationArgs.productCard
        binding.textView.text = product.title
    }
}