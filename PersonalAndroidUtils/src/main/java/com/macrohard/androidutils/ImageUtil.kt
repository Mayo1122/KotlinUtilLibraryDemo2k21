package com.macrohard.common_android.helper

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.widget.AppCompatImageView
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL


/*
 * Common Utils of Images
 * */
object ImageUtil {

    /**
     * This function can load image on ImageView
     * @param imageView on which image will be show
     * @param link link of image getting from server
     */
    fun loadImgURL(imageView: AppCompatImageView, link: String) {
       /* Picasso.get().load(link)
                .fit().centerInside()
                .into(imageView)*/
    }

//    fun startCameraByIntent(activity: Activity) {
//        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        activity.startActivityForResult(intent, Constants.IntentRequestCode.REQUEST_CAMERA)
//    }
//
//    fun startPermissionIntent(activity: Activity) {
//        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
//        intent.setData(Uri.parse("package:" + activity.getPackageName()))
//        activity.startActivityForResult(intent, Constants.IntentRequestCode.REQUEST_SETTINGS)
//    }
//
//
//    fun startGalleryByIntent(fragment: Fragment) {
//        val intent1 = Intent()
//        intent1.action = Intent.ACTION_GET_CONTENT
//        intent1.addCategory(Intent.CATEGORY_OPENABLE)
//        intent1.putExtra(MediaStore.EXTRA_SCREEN_ORIENTATION, ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
//        intent1.type = "image/*"
//        fragment.startActivityForResult(intent1, Constants.IntentRequestCode.REQUEST_GALLERY)
//    }

    fun getFacebookProfilePicture(userID: String): Bitmap? {
        var imageURL: URL? = null
        try {
            imageURL = URL("https://graph.facebook.com/$userID/picture?type=large")
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }

        var bitmap: Bitmap? = null
        try {
            bitmap = BitmapFactory.decodeStream(imageURL!!.openConnection().getInputStream())
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return bitmap
    }

    /*fun changeImageColor(mCurrentActivity: Context?, imageId: Int, color: Int): Drawable? {

        val mDrawable = mCurrentActivity?.resources?.getDrawable(imageId)
        if (mDrawable != null) {
            mDrawable.colorFilter = PorterDuffColorFilter(mCurrentActivity.resources.getColor(color),
                PorterDuff.Mode.SRC_IN)
        }
        return mDrawable
    }

    fun changeSrcImageColor(mCurrentActivity: Context, imageId: Int): Drawable {
        val mDrawable = mCurrentActivity.resources.getDrawable(imageId)
        mDrawable.colorFilter = PorterDuffColorFilter(mCurrentActivity.resources.getColor(R.color.transparent),
            PorterDuff.Mode.SRC_IN)
        return mDrawable
    }*/
}
