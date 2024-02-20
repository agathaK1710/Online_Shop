package com.example.onlineshop.ui.product

enum class Ending {
    FIRST_TYPE, SECOND_TYPE, THIRD_TYPE;

    companion object {
        fun getTypeByCount(count: Int): Ending? = Ending.entries.firstOrNull {
            return if (count % 10 == 0 || count % 10 == 5 ||
                count % 10 == 6 || count % 10 == 7 ||
                count % 10 == 8 || count % 10 == 9 ||
                count in (11..14)
            ) {
                FIRST_TYPE
            } else if (count % 10 == 1) {
                SECOND_TYPE
            } else {
               THIRD_TYPE
            }
        }
    }
}