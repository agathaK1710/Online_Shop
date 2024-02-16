package com.example.onlineshop.ui.login

fun CharSequence.isValid(): Boolean = Regex("[А-яЁё]+").matches(this)