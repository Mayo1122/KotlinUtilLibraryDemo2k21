
@file:Suppress("unused")
package com.macrohard.androidutils

import android.os.Build
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlin.DeprecationLevel.ERROR

var View.backgroundDrawable: Drawable?
    inline get() = background
    set(value) = setBackgroundDrawable(value)

var View.leftPadding: Int
    inline get() = paddingLeft
    set(value) = setPadding(value, paddingTop, paddingRight, paddingBottom)

var View.topPadding: Int
    inline get() = paddingTop
    set(value) = setPadding(paddingLeft, value, paddingRight, paddingBottom)

var View.rightPadding: Int
    inline get() = paddingRight
    set(value) = setPadding(paddingLeft, paddingTop, value, paddingBottom)

var View.bottomPadding: Int
    inline get() = paddingBottom
    set(value) = setPadding(paddingLeft, paddingTop, paddingRight, value)


inline var TextView.isSelectable: Boolean
    get() = isTextSelectable
    set(value) = setTextIsSelectable(value)

var ImageView.image: Drawable?
    inline get() = drawable
    inline set(value) = setImageDrawable(value)
