package com.example.onlineshop.ui.product

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.onlineshop.R
import com.example.onlineshop.databinding.FragmentProductPageBinding
import com.example.onlineshop.ui.OnlineShopApp
import com.example.onlineshop.ui.ViewModelFactory
import com.example.onlineshop.ui.catalog.CatalogViewModel
import com.example.onlineshop.ui.catalog.recyclerView.ProductCard
import com.example.onlineshop.ui.catalog.recyclerView.ViewPagerAdapter
import com.example.onlineshop.ui.utils.Ending
import javax.inject.Inject

class ProductPageFragment : Fragment() {
    private var _binding: FragmentProductPageBinding? = null
    private val binding get() = _binding!!

    private lateinit var product: ProductCard
    private val navigationArgs: ProductPageFragmentArgs by navArgs()

    private val component by lazy {
        (requireActivity().application as OnlineShopApp).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val catalogViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[CatalogViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        product = navigationArgs.productCard
        with(binding) {
            productDetailViewpager.adapter = ViewPagerAdapter(product.images, requireContext())
            dotsProductDetail.attachTo(productDetailViewpager)
            setHeartButton()
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
            title.text = product.title
            subtitle.text = product.subtitle
            setAvailable()
            setFeedback()
            newPrice.text = "${product.newPrice} ${product.unit}"
            oldPrice.apply {
                paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                text = "${product.oldPrice} ${product.unit}"
            }
            discount.text = "-${product.discount}%"
            button.text = product.title
            description.text = product.description
            setHideBtn()
            setMoreDescriptionBtn()
            setCharacteristics()
            composition.text = product.ingredients
            setMoreCompositionBtn()
            setHideCompositionBtn()
            btnNewPrice.text = "${product.newPrice} ${product.unit}"
            btnOldPrice.apply {
                paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                text = "${product.oldPrice} ${product.unit}"
            }
        }

    }

    private fun FragmentProductPageBinding.setHideCompositionBtn() {
        hideComposition.setOnClickListener {
            hideComposition.visibility = View.GONE
            composition.maxLines = 2
            moreComposition.visibility = View.VISIBLE
        }
    }

    private fun FragmentProductPageBinding.setMoreCompositionBtn() {
        moreComposition.setOnClickListener {
            hideComposition.visibility = View.VISIBLE
            composition.maxLines = Integer.MAX_VALUE
            moreComposition.visibility = View.GONE
        }
    }

    //todo custom view?
    private fun FragmentProductPageBinding.setCharacteristics() {
        when (product.info.size) {
            1 -> {
                characteristics1.visibility = View.VISIBLE
                characteristicsValue1.visibility = View.VISIBLE
                separator1.visibility = View.VISIBLE
                characteristics1.text = product.info[0].title
                characteristicsValue1.text = product.info[0].value
            }

            2 -> {
                characteristics1.visibility = View.VISIBLE
                characteristicsValue1.visibility = View.VISIBLE
                separator1.visibility = View.VISIBLE
                characteristics2.visibility = View.VISIBLE
                characteristicsValue2.visibility = View.VISIBLE
                separator2.visibility = View.VISIBLE
                characteristics1.text = product.info[0].title
                characteristicsValue1.text = product.info[0].value
                characteristics2.text = product.info[1].title
                characteristicsValue2.text = product.info[1].value
            }

            3 -> {
                characteristics1.visibility = View.VISIBLE
                characteristicsValue1.visibility = View.VISIBLE
                separator1.visibility = View.VISIBLE
                characteristics2.visibility = View.VISIBLE
                characteristicsValue2.visibility = View.VISIBLE
                separator2.visibility = View.VISIBLE
                characteristics3.visibility = View.VISIBLE
                characteristicsValue3.visibility = View.VISIBLE
                separator3.visibility = View.VISIBLE
                characteristics1.text = product.info[0].title
                characteristicsValue1.text = product.info[0].value
                characteristics2.text = product.info[1].title
                characteristicsValue2.text = product.info[1].value
                characteristics3.text = product.info[2].title
                characteristicsValue3.text = product.info[2].value
            }
        }
    }

    private fun FragmentProductPageBinding.setMoreDescriptionBtn() {
        moreDescription.setOnClickListener {
            moreDescription.visibility = View.GONE
            button.visibility = View.VISIBLE
            description.visibility = View.VISIBLE
            hide.visibility = View.VISIBLE
        }
    }

    private fun FragmentProductPageBinding.setHideBtn() {
        hide.setOnClickListener {
            button.visibility = View.GONE
            description.visibility = View.GONE
            moreDescription.visibility = View.VISIBLE
            hide.visibility = View.GONE
        }
    }

    private fun FragmentProductPageBinding.setFeedback() {
        if (product.feedbackCount != null && product.rating != null) {
            rating.text = product.rating.toString()
            val endingMap = HashMap<Ending, String>()
            endingMap[Ending.FIRST_TYPE] = "отзывов"
            endingMap[Ending.SECOND_TYPE] = "отзыв"
            endingMap[Ending.THIRD_TYPE] = "отзыва"
            val type = Ending.getTypeByCount(product.feedbackCount!!)
            numOfFeedbacs.text = "${product.feedbackCount.toString()} ${endingMap[type]}"

            when (product.rating!!.toInt()) {
                1 -> {
                    star1.setBackgroundResource(R.drawable.fill_star)
                    if (product.rating!! > 1.0) {
                        star2.setBackgroundResource(R.drawable.half_star)
                    }
                }

                2 -> {
                    star1.setBackgroundResource(R.drawable.fill_star)
                    star2.setBackgroundResource(R.drawable.fill_star)
                    if (product.rating!! > 2.0) {
                        star3.setBackgroundResource(R.drawable.half_star)
                    }
                }

                3 -> {
                    star1.setBackgroundResource(R.drawable.fill_star)
                    star2.setBackgroundResource(R.drawable.fill_star)
                    star3.setBackgroundResource(R.drawable.fill_star)
                    if (product.rating!! > 3.0) {
                        star4.setBackgroundResource(R.drawable.half_star)
                    }
                }

                4 -> {
                    star1.setBackgroundResource(R.drawable.fill_star)
                    star2.setBackgroundResource(R.drawable.fill_star)
                    star3.setBackgroundResource(R.drawable.fill_star)
                    star4.setBackgroundResource(R.drawable.fill_star)
                    if (product.rating!! > 4.0) {
                        star5.setBackgroundResource(R.drawable.half_star)
                    }
                }

                5 -> {
                    star1.setBackgroundResource(R.drawable.fill_star)
                    star2.setBackgroundResource(R.drawable.fill_star)
                    star3.setBackgroundResource(R.drawable.fill_star)
                    star4.setBackgroundResource(R.drawable.fill_star)
                    star5.setBackgroundResource(R.drawable.fill_star)
                }
            }

        } else {
            star1.visibility = View.GONE
            star2.visibility = View.GONE
            star3.visibility = View.GONE
            star4.visibility = View.GONE
            star5.visibility = View.GONE
            rating.visibility = View.GONE
            dot.visibility = View.GONE
            numOfFeedbacs.visibility = View.GONE
        }
    }

    private fun FragmentProductPageBinding.setAvailable() {
        val endingMap = HashMap<Ending, String>()
        endingMap[Ending.FIRST_TYPE] = "штук"
        endingMap[Ending.SECOND_TYPE] = "штука"
        endingMap[Ending.THIRD_TYPE] = "штуки"
        val type = Ending.getTypeByCount(product.available)
        availableProduct.text =
            "${getString(R.string.available)} ${product.available} ${endingMap[type]}"
    }

    private fun FragmentProductPageBinding.setHeartButton() {
        if (product.isFavourite) {
            buttonHeart.setBackgroundResource(R.drawable.heart_filled)
        } else {
            buttonHeart.setBackgroundResource(R.drawable.heart_transparent)
        }
        buttonHeart.setOnClickListener {
            if (product.isFavourite) {
                catalogViewModel.deleteFavouriteProduct(product.id)
                product.isFavourite = false
                buttonHeart.setBackgroundResource(R.drawable.heart_transparent)
            } else {
                catalogViewModel.addToFavourites(product.id)
                product.isFavourite = true
                buttonHeart.setBackgroundResource(R.drawable.heart_filled)
            }
        }
    }
}