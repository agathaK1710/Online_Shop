package com.example.onlineshop.ui.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.onlineshop.R
import com.example.onlineshop.databinding.FragmentLoginBinding
import com.example.onlineshop.ui.nav.NavigationFragment


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val loginViewModel by lazy {
        ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeActionBarVisibility()
        binding.Buttonlogin.setOnClickListener {
            loginViewModel.login()
            changeActionBarVisibility(View.VISIBLE)
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container_main, NavigationFragment())?.commit()

        }
        configValidation(binding.editTextName, binding.nameButton)
        configValidation(binding.editTextSurname, binding.surnameButton)
    }

    private fun configValidation(editText: EditText, imageButton: ImageButton) {
        with(editText) {
            doOnTextChanged { text, _, _, _ ->
                if (text != null) {
                    if (text.isNotEmpty()) {
                        imageButton.visibility = View.VISIBLE
                        imageButton.setOnClickListener {
                            editText.text.clear()
                        }
                        if (!text.isValid()) {
                            changeEditTextBackground(R.drawable.rounded_8dp_err)
                        } else {
                            changeEditTextBackground(R.drawable.rounded_8dp)
                        }
                    } else {
                        imageButton.visibility = View.GONE
                        changeEditTextBackground(R.drawable.rounded_8dp)
                    }
                }
            }
        }
    }

    private fun EditText.changeEditTextBackground(drawableRes: Int) =
        this.setBackgroundResource(
            drawableRes
        )

    private fun changeActionBarVisibility(visibility: Int = View.GONE) {
        with((requireActivity() as AppCompatActivity).supportActionBar) {
            if (visibility == View.VISIBLE) this?.show() else this?.hide()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}