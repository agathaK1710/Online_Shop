package com.example.onlineshop.ui.catalog.recyclerView

import android.annotation.SuppressLint
import android.app.Application
import android.graphics.Paint
import android.view.View
import com.example.onlineshop.databinding.ProductCardBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import javax.inject.Inject

class AdapterDelegate @Inject constructor(
    val context: Application
){
    companion object {
        @SuppressLint("SetTextI18n")
        fun productAdapterDelegate() =
            adapterDelegateViewBinding<ProductCard, DisplayableItem, ProductCardBinding>(
                { layoutInflater, root -> ProductCardBinding.inflate(layoutInflater, root, false) }
            ) {
                bind {
                    with(binding) {
                        photosViewpager.adapter = ViewPagerAdapter(item.images, context)
                        dots.attachTo(photosViewpager)
                        oldPrice.apply {
                            paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                            text = "${item.oldPrice} ${item.unit}"
                        }
                        newPrice.text = "${item.newPrice} ${item.unit}"
                        discount.text = "-${item.discount.toString()}%"
                        title.text = item.title
                        subtitle.text = item.subtitle
                        rating.text = item.rating.toString()
                        if (item.feedback != null) {
                            numOfFeedbacs.text = "(${item.feedback?.count.toString()})"
                        } else {
                            numOfFeedbacs.visibility = View.GONE
                            star.visibility = View.GONE
                        }
                    }
                }
            }
    }
}