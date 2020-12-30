package com.macrohard.androidutils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Hides the soft keyboard
 * @receiver Activity
 * @return a boolean value if the action was performed or not
 */
fun Activity.hideKeyboard(): Boolean {
    val view = currentFocus
    view?.let {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return inputMethodManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
    return false
}

/**
 * Opens up the keyboard by focusing on the view
 * @receiver View
 */
fun View.showKeyboard() {
    val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    this.requestFocus()
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

/**
 * Opens up the keyboard forcefully
 * @receiver Context
 */
fun Context.showKeyboard() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)
}

/**
 * hide keyboard
 */
fun Dialog.hideKeyboard() {
    this.currentFocus?.let {
        this.context.inputMethodManager?.hideSoftInputFromWindow(it.windowToken, 0)
    }
}

/**
 * toggle keyboard open / close
 */
fun Context.toggleKeyboard() {
    inputMethodManager?.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS)
}