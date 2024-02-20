package com.example.onlineshop.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.User
import com.example.domain.useCases.DeleteFavProductsUseCase
import com.example.domain.useCases.DeleteUsersUseCase
import com.example.domain.useCases.GetFavListUseCase
import com.example.domain.useCases.GetUsersUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val deleteUsersUseCase: DeleteUsersUseCase,
    private val getFavListUseCase: GetFavListUseCase,
    private val deleteFavProductsUseCase: DeleteFavProductsUseCase
) : ViewModel() {

    private val _currentUser = MutableLiveData<User?>()
    val currentUser: LiveData<User?> = _currentUser

    private val _favCount = MutableLiveData<Int>()
    val favCount: LiveData<Int> = _favCount

    init {
        viewModelScope.launch {
            val users = getUsersUseCase()
            if (users.isNotEmpty()) {
                _currentUser.value = getUsersUseCase()[0]
            } else {
                _currentUser.value = null
            }
            _favCount.value = getFavListUseCase().size
        }
    }

    fun logOut() = viewModelScope.launch {
        deleteUsersUseCase()
        deleteFavProductsUseCase()
    }

    fun refreshFavs() = viewModelScope.launch {
        _favCount.value = getFavListUseCase().size
    }
}