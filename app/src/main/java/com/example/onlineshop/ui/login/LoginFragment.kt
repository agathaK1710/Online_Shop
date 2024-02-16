package com.example.onlineshop.ui.login

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
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
    private val validName = MutableLiveData(false)
    private val validSurname = MutableLiveData(false)
    private val validPhone = MutableLiveData(false)

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
        configValidation(binding.editTextName, binding.nameButton, validName)
        configValidation(binding.editTextSurname, binding.surnameButton, validSurname)
        configPhoneEditText()

        validName.observe(viewLifecycleOwner) { validName ->
            validSurname.observe(viewLifecycleOwner) { validSurname ->
                validPhone.observe(viewLifecycleOwner) { validPhone ->
                    if (validName && validPhone && validSurname) {
                        binding.buttonLogin.isEnabled = true
                        binding.buttonLogin.setBackgroundResource(R.drawable.rounded_8dp_pink)
                        binding.buttonLogin.setOnClickListener {
                            loginViewModel.login()
                            activity?.supportFragmentManager?.beginTransaction()
                                ?.replace(R.id.fragment_container_main, NavigationFragment())
                                ?.commit()

                        }
                    } else {
                        binding.buttonLogin.isEnabled = false
                        binding.buttonLogin.setBackgroundResource(R.drawable.rounded_8dp_light_pink)
                    }
                }
            }
        }
    }

    private fun configPhoneEditText() {
        with(binding.editTextPhone) {
            onFocusChangeListener =
                View.OnFocusChangeListener { _, hasFocus ->
                    if (hasFocus) {
                        if(text?.toString().equals(resources.getString(R.string.phone_number))) {
                            hint = resources.getString(R.string.mask_hint)
                            mask = getString(R.string.mask)
                        }
                    } else {
                        if(text.toString() == getString(R.string.code_and_mask)) {
                            hint = resources.getString(R.string.phone_number)
                            mask = resources.getString(R.string.simple_mask)
                            binding.numButton.visibility = View.GONE
                        }
                    }
                }

            doOnTextChanged { text, _, _, _ ->
                if (text != null) {
                    if (text.toString() != getString(R.string.code_and_mask)) {
                        binding.numButton.visibility = View.VISIBLE
                        binding.numButton.setOnClickListener {
                            binding.editTextPhone.text?.clear()
                        }
                        if (text.isValidPhone()) validPhone.value = true
                    } else {
                        binding.numButton.visibility = View.GONE
                        validPhone.value = false
                    }
                }
            }
        }
    }

    private fun configValidation(
        editText: EditText,
        imageButton: ImageButton,
        validData: MutableLiveData<Boolean>
    ) {
        with(editText) {
            doOnTextChanged { text, _, _, _ ->
                if (text != null) {
                    if (text.isNotEmpty()) {
                        imageButton.visibility = View.VISIBLE
                        imageButton.setOnClickListener {
                            editText.text.clear()
                        }
                        if (!text.isValidCredentials()) {
                            changeEditTextBackground(R.drawable.rounded_8dp_err)
                        } else {
                            changeEditTextBackground(R.drawable.rounded_8dp)
                            validData.value = true
                        }
                    } else {
                        imageButton.visibility = View.GONE
                        changeEditTextBackground(R.drawable.rounded_8dp)
                        validData.value = false
                    }
                }
            }
        }
    }

    private fun EditText.changeEditTextBackground(drawableRes: Int) =
        this.setBackgroundResource(
            drawableRes
        )

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}