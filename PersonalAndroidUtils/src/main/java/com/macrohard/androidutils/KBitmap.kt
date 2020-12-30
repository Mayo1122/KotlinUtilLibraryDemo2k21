package com.macrohard.androidutils

import android.graphics.*
import android.util.Base64
import android.view.View
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


/**
 * Get the view as bitmap
 * @param bitmapConfig An optional parameter specifying bitmap config value
 * @return Bitmap representation of the view
 */
fun View.getBitmap(bitmapConfig: Bitmap.Config = Bitmap.Config.ARGB_8888): Bitmap {
    val bmp = Bitmap.createBitmap(width, height, bitmapConfig)
    val canvas = Canvas(bmp)
    draw(canvas)
    canvas.save()
    return bmp
}

/**
 * Converts bitmap to bas64 string
 * @return base64 representation of the bitmap
 */
fun Bitmap.toBase64(): String {
    var result = ""
    val baos = ByteArrayOutputStream()
    try {
        compress(Bitmap.CompressFormat.JPEG, 100, baos)
        baos.flush()
        baos.close()
        val bitmapBytes = baos.toByteArray()
        result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT)
    } catch (e: IOException) {
        e.printStackTrace()
    } finally {
        try {
            baos.flush()
            baos.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    return result
}

/**
 * Resize the bitmap to specified height and width.
 */
fun Bitmap.resize(newWidth: Number, newHeight: Number): Bitmap {
    val width = width
    val height = height
    val scaleWidth = newWidth.toFloat() / width
    val scaleHeight = newHeight.toFloat() / height
    val matrix = Matrix()
    matrix.postScale(scaleWidth, scaleHeight)
    if (width > 0 && height > 0) {
        return Bitmap.createBitmap(this, 0, 0, width, height, matrix, true)
    }
    return this
}

/**
 * Saves the bitmap to the given file
 * @param file The file instance where the object needs to be saved
 * @param compressFormat Defaults to PNG value
 * @param quality Default 100 % of the original quality
 */
fun Bitmap.saveFile(
        file: File,
        compressFormat: Bitmap.CompressFormat = Bitmap.CompressFormat.PNG,
        quality: Int = 100) {
    if (!file.exists()) {
        file.createNewFile()
    }
    val stream = FileOutputStream(file)
    compress(compressFormat, quality, stream)
    stream.flush()
    stream.close()
}

/**
 * Make bitmap corner
 * @return Bitmap object
 */
fun Bitmap.toRoundCorner(radius: Float): Bitmap? {
    val width = this.width
    val height = this.height
    val bitmap = Bitmap.createBitmap(width, height, this.config)
    val paint = Paint()
    val canvas = Canvas(bitmap)
    val rect = Rect(0, 0, width, height)

    paint.isAntiAlias = true
    canvas.drawRoundRect(RectF(rect), radius, radius, paint)
    paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    canvas.drawBitmap(this, rect, rect, paint)

    this.recycle()
    return bitmap
}