package com.example.onlineshop.ui.catalog

enum class SortCriteria(val criteriaName: String) {
    RATING("По популярности"),
    PRICE_DESC("По уменьшению цены"),
    PRICE_ASC("По возрастанию цены");

    companion object {
        infix fun from(name: String): SortCriteria? =  entries.firstOrNull {
            it.criteriaName == name
        }
    }
}