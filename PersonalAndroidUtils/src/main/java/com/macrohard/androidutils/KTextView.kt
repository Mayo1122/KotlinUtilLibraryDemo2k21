
package com.macrohard.androidutils

import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView

val TextView.isEmpty
    get() = text.isEmpty()

val TextView.isNotEmpty
    get() = text.isNotEmpty()

val TextView.isBlank
    get() = text.isBlank()

val TextView.isNotBlank
    get() = text.isNotBlank()