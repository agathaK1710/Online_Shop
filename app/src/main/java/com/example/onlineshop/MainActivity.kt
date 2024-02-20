package com.example.onlineshop

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import com.example.onlineshop.databinding.ActivityMainBinding
import com.example.onlineshop.ui.OnlineShopApp
import com.example.onlineshop.ui.ViewModelFactory
import com.example.onlineshop.ui.login.LoginFragment
import com.example.onlineshop.ui.nav.NavigationFragment
import com.example.onlineshop.ui.profile.ProfileViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val profileViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[ProfileViewModel::class.java]
    }

    private val component by lazy {
        (application as OnlineShopApp).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = true
        }
        profileViewModel.currentUser.observe(this) { user ->
            Log.d("CHANGEUSER", "$user")
            if (user == null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_main, LoginFragment())
                    .commit()
            } else {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_main, NavigationFragment())
                    .commit()
            }
        }
    }
}