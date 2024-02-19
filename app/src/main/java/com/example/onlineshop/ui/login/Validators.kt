package com.example.onlineshop.ui.login

fun CharSequence.isValidCredentials(): Boolean = Regex("[А-яЁё]+").matches(this)

fun CharSequence.isValidPhone(): Boolean = this.length == 16