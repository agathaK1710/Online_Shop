package com.example.onlineshop.ui.catalog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.onlineshop.R
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
        _binding = FragmentCatalogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tags = arrayListOf<Tag>()
        with(binding) {
            tags.add(Tag(btnTagWatchAll, tagWatchAll, layoutTagWatchAll, BtnState.CLICKED))
            tags.add(Tag(btnTagBody, tagBody, layoutTagBody))
            tags.add(Tag(btnTagFace, tagFace, layoutTagFace))
            tags.add(Tag(btnTagMask, tagMask, layoutTagMask))
            tags.add(Tag(btnTagTan, tagTan, layoutTagTan))

            context?.let {
                ArrayAdapter.createFromResource(
                    it,
                    R.array.sort_array,
                    R.layout.spinner_item
                ).also { adapter ->
                    adapter.setDropDownViewResource(R.layout.spinner_item)
                    spinner.adapter = adapter
                }
            }
        }
        tags.forEach { tag ->
            refreshTag(tag)
            tag.button.setOnClickListener {
                tag.state = BtnState.UNCLICKED
                refreshTag(tag)
            }
            tag.layout.setOnClickListener {
                val clickedTag = tags.find {
                    it.state == BtnState.CLICKED
                }
                if (clickedTag != null && clickedTag != tag) {
                    clickedTag.state = BtnState.UNCLICKED
                    tag.state = BtnState.CLICKED
                    refreshTag(clickedTag)
                    refreshTag(tag)
                } else {
                    tag.state = BtnState.CLICKED
                    refreshTag(tag)
                }
            }
        }
    }

    private fun refreshTag(tag: Tag) {
        context?.let { tag.setAppearance(it) }
    }
}