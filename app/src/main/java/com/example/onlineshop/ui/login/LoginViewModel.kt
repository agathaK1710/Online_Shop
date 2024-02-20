package com.example.onlineshop.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.User
import com.example.domain.useCases.GetUserCountUseCase
import com.example.domain.useCases.InsertUserUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val insertUserUseCase: InsertUserUseCase,
    private val getUserCountUseCase: GetUserCountUseCase
) : ViewModel() {

    private val _userInDb = MutableLiveData<Boolean>()
    val userInDb: LiveData<Boolean> = _userInDb

    init {
        viewModelScope.launch {
            _userInDb.value = getUserCountUseCase.invoke() != 0
        }
    }

    fun login(user: User) = viewModelScope.launch {
        insertUserUseCase(user)
    }
}