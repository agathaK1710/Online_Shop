package com.example.onlineshop.ui.catalog

import android.content.Context
import com.example.onlineshop.R

enum class Tag(
    val tagNameRes: Int,
    val tagName: String
) {
    FACE(R.string.face, "face"), BODY(R.string.body, "body"),
    SUNTAN(R.string.tan, "suntan"), MASK(R.string.face_mask, "mask"),
    WATCH_ALL(R.string.watch_all, "");

    companion object {
        fun from(name: String, context: Context): Tag? = entries.firstOrNull {
            context.getString(it.tagNameRes) == name
        }
    }
}