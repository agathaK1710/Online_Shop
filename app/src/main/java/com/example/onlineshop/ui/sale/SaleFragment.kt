package com.example.onlineshop.ui.sale

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.onlineshop.databinding.FragmentSaleBinding
import com.example.onlineshop.ui.OnlineShopApp
import com.example.onlineshop.ui.ViewModelFactory
import javax.inject.Inject

class SaleFragment : Fragment() {
    private var _binding: FragmentSaleBinding? = null
    private val binding get() = _binding!!

    private val component by lazy {
        (requireActivity().application as OnlineShopApp).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val saleViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[SaleViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSaleBinding.inflate(inflater, container, false)
        return binding.root
    }
}