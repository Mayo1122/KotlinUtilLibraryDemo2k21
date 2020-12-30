package com.macrohard.common_android.helper

import java.io.File
import java.io.FileOutputStream
import java.net.URL


/*
 * Common Utils of Files
 * */
object FilesUtil {

    /**
     * This function will download file received from backend
     * @param link is file url link
     * @param path where file will saved
     */
    fun downloadFile(link: String, path: String) {
        URL(link).openStream().use { input ->
            FileOutputStream(File(path)).use { output ->
                input.copyTo(output)
            }
        }
    }
}
