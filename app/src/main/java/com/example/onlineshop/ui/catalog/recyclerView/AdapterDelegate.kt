package com.example.onlineshop.ui.catalog.recyclerView

import android.annotation.SuppressLint
import android.app.Application
import android.graphics.Paint
import android.view.View
import com.example.onlineshop.R
import com.example.onlineshop.databinding.ProductCardBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import javax.inject.Inject

class AdapterDelegate @Inject constructor(
    val context: Application
) {
    companion object {
        @SuppressLint("SetTextI18n")
        fun productAdapterDelegate(
            onClickHeartListener: ((String, Boolean) -> Unit)? = null,
            onItemClickListener: ((String) -> Unit)? = null
        ) =
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
                        discount.text = "-${item.discount}%"
                        title.text = item.title
                        subtitle.text = item.subtitle
                        rating.text = item.rating.toString()
                        if (item.feedback != null) {
                            numOfFeedbacs.text = "(${item.feedback?.count.toString()})"
                        } else {
                            numOfFeedbacs.visibility = View.GONE
                            star.visibility = View.GONE
                        }
                        if (item.isFavourite) {
                            buttonHeart.setBackgroundResource(R.drawable.heart_filled)
                        } else {
                            buttonHeart.setBackgroundResource(R.drawable.heart_transparent)
                        }
                        buttonHeart.setOnClickListener {
                            if (item.isFavourite) {
                                onClickHeartListener?.invoke(item.id, false)
                            } else {
                                onClickHeartListener?.invoke(item.id, true)
                            }
                        }
                        itemCard.setOnClickListener {
                            onItemClickListener?.invoke(item.id)
                        }
                    }
                }
            }
    }
}