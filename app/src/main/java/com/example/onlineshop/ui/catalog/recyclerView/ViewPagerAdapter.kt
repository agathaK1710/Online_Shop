package com.example.onlineshop.ui.catalog.recyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.example.onlineshop.databinding.ProductImageBinding
import java.util.Objects


class ViewPagerAdapter (
    private val images: List<Int>,
    private val context: Context
) : PagerAdapter() {
    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = ProductImageBinding.inflate(LayoutInflater.from(context), container, false)
        binding.productImage.setImageResource(images[position])
        Objects.requireNonNull(container).addView(binding.root)
        return binding.root
    }
}