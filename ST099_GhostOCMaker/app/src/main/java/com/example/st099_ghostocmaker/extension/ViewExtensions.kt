package com.chibimaker.create.avatar.cutechibi.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.view.WindowCompat
import com.chibimaker.create.avatar.cutechibi.utils.SystemUtils.INTENT_KEY
import com.chibimaker.create.avatar.cutechibi.utils.SystemUtils.isFromOtherScreen
import com.chibimaker.create.avatar.cutechibi.utils.SystemUtils.mediaPlayer
import com.chibimaker.create.avatar.cutechibi.utils.SystemUtils.statusSound
import com.example.st099_ghostocmaker.R
import java.io.File
import java.io.FileOutputStream
import java.text.DecimalFormat
import java.util.Locale

internal fun View.show() {
    visibility = View.VISIBLE
}

internal fun View.hide() {
    visibility = View.INVISIBLE
}

internal fun View.gone() {
    visibility = View.GONE
}

internal fun Activity.showToast(message: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, ContextCompat.getString(this, message), duration).show()
}

internal fun Activity.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}


internal fun Activity.startIntent(targetActivity: Class<*>) {
    val intent = Intent(this, targetActivity)
    startActivity(intent)
}

internal fun Activity.startIntent(targetActivity: Class<*>, value: String) {
    val intent = Intent(this, targetActivity)
    intent.putExtra(INTENT_KEY, value)
    startActivity(intent)
}

internal fun Activity.startIntent(targetActivity: Class<*>, key: String, value: String) {
    val intent = Intent(this, targetActivity)
    intent.putExtra(key, value)
    startActivity(intent)
}

internal fun Activity.startIntentAnim(targetActivity: Class<*>) {
    val intent = Intent(this, targetActivity)
    startActivity(intent)
    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
}

internal fun Activity.startIntentAnim(targetActivity: Class<*>, value: String) {
    val intent = Intent(this, targetActivity)
    intent.putExtra(INTENT_KEY, value)
    startActivity(intent)
    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
}

internal fun Activity.startIntentAnim(targetActivity: Class<*>, key: String, value: String) {
    val intent = Intent(this, targetActivity)
    intent.putExtra(key, value)
    startActivity(intent)
    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
}



internal fun View.select() {
    isSelected = true
}

internal fun upperFirstCharacter(str: String): String {
    return str.capitalize(Locale.ROOT)
}

internal fun convertToLowerCase(input: String): String {
    return input.lowercase()
}

internal fun formatDecimal(number: Double, decimalPlaces: Int): String {
    val pattern = "#." + "0".repeat(decimalPlaces)
    val decimalFormat = DecimalFormat(pattern)
    return decimalFormat.format(number)
}

internal fun dp2px(dpVal: Float): Int {
    val r = Resources.getSystem()
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, r.displayMetrics).toInt()
}

internal fun sp2px(spVal: Float): Int {
    val r = Resources.getSystem()
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal, r.displayMetrics).toInt()
}

//internal fun dpToPx(context: Context, dp: Float): Int {
//    val density = context.resources.displayMetrics.density
//    return (dp * density).toInt()
//}


fun pxToDp(context: Context, px: Int): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        px.toFloat(),
        context.resources.displayMetrics
    ).toInt()
}

fun Activity.showSystemUI(white: Boolean) {
    if (white) {
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    } else {
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        WindowCompat.setDecorFitsSystemWindows(window, false);
    } else {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
    }
}


fun Activity.hideSoftKeyboard() {
    currentFocus?.let {
        val inputMethodManager =
            ContextCompat.getSystemService(this, InputMethodManager::class.java)!!
        inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
    }
}

internal fun Activity.hideWindow() {
    window.setFlags(
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
    )
    window.decorView.systemUiVisibility =
        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
}

internal fun Activity.mLog(content: String) {
    Log.d("nbhieu", content)
}


internal fun Activity.handleShare(bit: Bitmap) {
    val imageFile = saveBitmapToCache(bit)
    val imageUri = FileProvider.getUriForFile(
        this,
        "${this.packageName}.fileprovider",
        imageFile
    )
    shareImage(this, imageUri)
}

internal fun Activity.saveBitmapToCache(bitmap: Bitmap): File {
    val cachePath = File(cacheDir, "images")
    cachePath.mkdirs()
    val file = File(cachePath, "shared_image.png")
    FileOutputStream(file).use { out ->
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
    }
    return file
}

internal fun shareImage(context: Context, imageUri: Uri) {
    val arr = ArrayList<Uri>()
    arr.add(imageUri)

    val intent = Intent(Intent.ACTION_SEND_MULTIPLE).apply {
        type = "*/*"
        putParcelableArrayListExtra(Intent.EXTRA_STREAM, arr)
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }
    context.startActivity(Intent.createChooser(intent, "Share Images"))
}

// ST077
internal fun Activity.handleBack() {
    isFromOtherScreen = true
    finish()
    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
}


internal fun Activity.stopActivity() {
    if (!isFromOtherScreen) {
        mediaPlayer?.pause()
    } else {
        isFromOtherScreen = false
    }
}

internal fun Activity.restartActivity() {
    if (statusSound) {
        mediaPlayer?.start()
    }
}


internal fun Activity.hideNavigation() {
    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
}
