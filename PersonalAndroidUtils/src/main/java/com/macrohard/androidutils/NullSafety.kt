package com.macrohard.androidutils


/**
 * Returns false if it is null
 */
fun Boolean?.orFalse(): Boolean = this ?: false

/**
 * Returns true if it is null
 * Warning: Please use this with caution. Boolean's default value is false
 */
fun Boolean?.orTrue(): Boolean = this ?: true