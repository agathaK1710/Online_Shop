package com.example.onlineshop.ui.catalog

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.onlineshop.R
import com.example.onlineshop.databinding.FragmentCatalogBinding
import com.example.onlineshop.ui.OnlineShopApp
import com.example.onlineshop.ui.ViewModelFactory
import com.example.onlineshop.ui.catalog.recyclerView.MainAdapter
import javax.inject.Inject


class CatalogFragment : Fragment() {
    private var _binding: FragmentCatalogBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val catalogViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[CatalogViewModel::class.java]
    }
    private lateinit var mainAdapter: MainAdapter

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
        _binding = FragmentCatalogBinding.inflate(inflater, container, false)
        setupRvAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tags = arrayListOf<TagView>()
        with(binding) {
            tags.add(TagView(btnTagWatchAll, tagWatchAll, layoutTagWatchAll, BtnState.CLICKED))
            tags.add(TagView(btnTagBody, tagBody, layoutTagBody))
            tags.add(TagView(btnTagFace, tagFace, layoutTagFace))
            tags.add(TagView(btnTagMask, tagMask, layoutTagMask))
            tags.add(TagView(btnTagTan, tagTan, layoutTagTan))

            context?.let {
                ArrayAdapter.createFromResource(
                    it,
                    R.array.sort_array,
                    R.layout.spinner_item
                ).also { adapter ->
                    adapter.setDropDownViewResource(R.layout.spinner_dropdown)
                    spinner.adapter = adapter
                }
            }
            val criteriaArr = resources.getStringArray(R.array.sort_array)
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val criteria = SortCriteria.from(criteriaArr[position])
                    criteria?.let {
                        catalogViewModel.sortBy(it)
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

            }
        }
        tags.forEach { tagView ->
            refreshTag(tagView)
            tagView.button.setOnClickListener {
                tagView.state = BtnState.UNCLICKED
                refreshTag(tagView)
                catalogViewModel.resetTag()
            }
            tagView.layout.setOnClickListener {
                val clickedTag = tags.find {
                    it.state == BtnState.CLICKED
                }
                if (clickedTag != null && clickedTag != tagView) {
                    clickedTag.state = BtnState.UNCLICKED
                    tagView.state = BtnState.CLICKED
                    refreshTag(clickedTag)
                    refreshTag(tagView)
                } else {
                    tagView.state = BtnState.CLICKED
                    refreshTag(tagView)
                }

                val tag = Tag.from(tagView.text.text.toString(), requireContext())
                if (tag != null) {
                    catalogViewModel.filterList(tag)
                }

            }
        }
    }

    private fun refreshTag(tag: TagView) {
        context?.let { tag.setAppearance(it) }
    }

    private fun setupRvAdapter() {
        catalogViewModel.productsCardsList.observe(viewLifecycleOwner) { list ->
            mainAdapter = MainAdapter(
                onClickHeartListener = { id, toInsert ->
                    if (toInsert) {
                        catalogViewModel.addToFavourites(id)
                    } else {
                        catalogViewModel.deleteFavouriteProduct(id)
                    }
                },
                onItemClickListener = { id ->
                    val productCard = list.find { product ->
                        product.id == id
                    }
                    if (productCard != null) {
                        findNavController().navigate(
                            CatalogFragmentDirections.actionNavigationCatalogToProductPage(
                                productCard
                            )
                        )
                    }
                }
            )
            mainAdapter.items = list
            binding.recyclerView.apply {
                adapter = mainAdapter
                layoutManager = GridLayoutManager(requireContext(), 2)
                itemAnimator = DefaultItemAnimator()
            }
        }
    }
}