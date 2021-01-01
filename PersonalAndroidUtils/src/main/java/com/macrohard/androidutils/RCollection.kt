package com.macrohard.androidutils


fun <T> ArrayList<T>.lastIndex():Int {
    return this.size-1
}

fun <T> ArrayList<T>.firstIndex():Int {
    return 0
}

fun <T> ArrayList<T>.middleIndex():Int {
    return this.size/2
}