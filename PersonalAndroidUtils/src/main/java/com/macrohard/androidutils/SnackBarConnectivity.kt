package com.macrohard.common_android.helper

import android.view.View
import com.google.android.material.snackbar.Snackbar


public object SnackBarConnectivity {

    private var snackbar: Snackbar? = null

     public fun showSnackBar(view: View, msg: String) {
         if (view != null) {
             snackbar?.dismiss()
             snackbar =  Snackbar.make(view, msg, Snackbar.LENGTH_INDEFINITE)
             snackbar?.setAction("Hide") {
                 snackbar?.dismiss()
             }
             snackbar?.show()
         }
     }

     public fun dismissSnackBar(){
         snackbar?.setText("Connected")
         snackbar?.setDuration(Snackbar.LENGTH_LONG)
         snackbar?.show()
     }

}
