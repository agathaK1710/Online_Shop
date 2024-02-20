package com.example.onlineshop.ui.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.onlineshop.R
import com.example.onlineshop.databinding.FragmentProfileBinding
import com.example.onlineshop.ui.OnlineShopApp
import com.example.onlineshop.ui.ViewModelFactory
import com.example.onlineshop.ui.login.LoginFragment
import com.example.onlineshop.ui.utils.Ending
import javax.inject.Inject

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val profileViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[ProfileViewModel::class.java]
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
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            profileViewModel.refreshFavs()
            profileViewModel.currentUser.observe(viewLifecycleOwner){user ->
                if (user != null) {
                    credentials.text = "${user.name} ${user.surname}"
                    phone.text = user.phoneNumber
                }
            }
            profileViewModel.favCount.observe(viewLifecycleOwner){
                val endingMap = HashMap<Ending, String>()
                endingMap[Ending.FIRST_TYPE] = "товаров"
                endingMap[Ending.SECOND_TYPE] = "товар"
                endingMap[Ending.THIRD_TYPE] = "товара"
                val type = Ending.getTypeByCount(it)
                favourites.text = "$it ${endingMap[type]}"
            }
            logoutButton.setOnClickListener {
                profileViewModel.logOut()
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.fragment_container_main, LoginFragment())
                    ?.commit()
            }
            cardFavourites.setOnClickListener {
                findNavController().navigate(R.id.action_navigation_profile_to_favouritesFragment)
            }
        }
    }
}