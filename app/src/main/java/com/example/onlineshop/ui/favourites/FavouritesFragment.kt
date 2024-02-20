package com.example.onlineshop.ui.favourites

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.onlineshop.R
import com.example.onlineshop.databinding.FragmentFavouritesBinding
import com.example.onlineshop.ui.OnlineShopApp
import com.example.onlineshop.ui.ViewModelFactory
import com.example.onlineshop.ui.catalog.recyclerView.MainAdapter
import javax.inject.Inject

class FavouritesFragment : Fragment() {
    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainAdapter: MainAdapter

    private val component by lazy {
        (requireActivity().application as OnlineShopApp).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val favouriteViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[FavouritesViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        setupRvAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favouriteViewModel.refreshFavs()
        with(binding) {
            toogleBtn()
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun FragmentFavouritesBinding.toogleBtn() {
        whiteCardProducts.setOnClickListener {
            favouriteViewModel.refreshFavs()
            whiteCardProducts.setBackgroundResource(R.drawable.rounded_8dp_white)
            whiteCardProducts.setTextColor(Color.BLACK)
            whiteCardBrands.setBackgroundColor(Color.TRANSPARENT)
            whiteCardBrands.setTextColor(resources.getColor(R.color.grey, requireContext().theme))
        }
        whiteCardBrands.setOnClickListener {
            favouriteViewModel.setBrandsList()
            whiteCardBrands.setBackgroundResource(R.drawable.rounded_8dp_white)
            whiteCardBrands.setTextColor(Color.BLACK)
            whiteCardProducts.setBackgroundColor(Color.TRANSPARENT)
            whiteCardProducts.setTextColor(resources.getColor(R.color.grey, requireContext().theme))
        }
    }

    private fun setupRvAdapter() {
        favouriteViewModel.favList.observe(viewLifecycleOwner) { list ->
            mainAdapter = MainAdapter(
                onClickHeartListener = { id, toInsert ->
                    if (toInsert) {
                        favouriteViewModel.addToFavourites(id)
                    } else {
                        favouriteViewModel.deleteFavouriteProduct(id)
                    }
                },
                onItemClickListener = { id ->
                    val productCard = list.find { product ->
                        product.id == id
                    }
                    if (productCard != null) {
                        findNavController().navigate(
                            FavouritesFragmentDirections.actionFavouritesFragmentToProductPage(
                                productCard
                            )
                        )
                    }
                }
            )
            mainAdapter.items = list
            binding.favouritesRecyclerView.apply {
                adapter = mainAdapter
                layoutManager = GridLayoutManager(requireContext(), 2)
                itemAnimator = DefaultItemAnimator()
            }
        }
    }
}