package com.chibimaker.create.avatar.cutechibi.utils

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.view.View
import androidx.core.app.ShareCompat
import com.chibimaker.create.avatar.cutechibi.extension.animateScaleEffect
import com.chibimaker.create.avatar.cutechibi.extension.animateScaleOutEffect
import com.facebook.shimmer.Shimmer
import java.util.Locale

object SystemUtils {
    const val BASE_URL = "https://lvtglobal.tech"
    private var myLocale: Locale? = null
    private const val FIRST_LANG = "first_lang"
    private const val FIRST_LANG_KEY = "first_access_lang"

    private const val FIRST_PERMISSION = "first_permission"
    private const val FIRST_PERMISSION_KEY = "first_access_permission"

    private const val RATE = "rate"
    private const val RATE_KEY = "rate_5_star"

    private const val COUNT_BACK = "count_back"
    private const val COUNT_BACK_KEY = "back_count"

    const val STORAGE_PERMISSION_CODE = 999
    const val NOTIFICATION_PERMISSION_CODE = 997
    val PICK_IMAGE_REQUEST_CODE = 103
    val storagePermission = arrayOf( Manifest.permission.WRITE_EXTERNAL_STORAGE)
//        when {
//        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> arrayOf(
//            Manifest.permission.READ_MEDIA_IMAGES
//        )
//
//        else -> arrayOf(
//            Manifest.permission.WRITE_EXTERNAL_STORAGE
////            ,
////            Manifest.permission.READ_EXTERNAL_STORAGE
//        )
//    }

    val notificationPermission =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arrayOf(Manifest.permission.POST_NOTIFICATIONS)
        } else {
            emptyArray()
        }


    const val INTENT_KEY = "intent_key"
    const val INTENT_KEY_LANG = "intent_lang"
    var FIRST_ACCESS = false

    fun setLocale(context: Context) {
        val language = getPreLanguage(context)
        if (language.isEmpty()) {
            val config = Configuration()
            val locale = Locale.getDefault()
            Locale.setDefault(locale)
            config.setLocale(locale)
            context.resources.updateConfiguration(config, context.resources.displayMetrics)
        } else {
            changeLang(language, context)
        }
    }


    fun changeLang(lang: String, context: Context) {
        if (lang.equals("", ignoreCase = true)) return
        myLocale = Locale(lang)
        saveLocale(context, lang)
        Locale.setDefault(myLocale!!)
        val config = Configuration()
        config.setLocale(myLocale)
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }

    fun saveLocale(context: Context, lang: String) {
        setPreLanguage(context, lang)
    }

    fun setPreLanguage(context: Context, language: String) {
        if (language.isNotEmpty()) {
            val preferences: SharedPreferences =
                context.getSharedPreferences("data", Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.putString("KEY_LANGUAGE", language)
            editor.apply()
        }
    }

    fun getPreLanguage(context: Context): String {
        val preferences: SharedPreferences =
            context.getSharedPreferences("data", Context.MODE_PRIVATE)
        return preferences.getString("KEY_LANGUAGE", "") ?: ""
    }

    fun isFirstLang(context: Context): Boolean {
        val preferences: SharedPreferences =
            context.getSharedPreferences(FIRST_LANG, Context.MODE_PRIVATE)
        return preferences.getBoolean(FIRST_LANG_KEY, true)
    }

    fun setFirstLang(context: Context, isFirstAccess: Boolean) {
        val preferences: SharedPreferences =
            context.getSharedPreferences(FIRST_LANG, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putBoolean(FIRST_LANG_KEY, isFirstAccess)
        editor.apply()
    }

    var lastClickTime = 0L
    fun View.onSingleClickOrigin(action: (View) -> Unit) {
        this.setOnClickListener {
            if (System.currentTimeMillis() - lastClickTime >= 500) {
                action(it)
                lastClickTime = System.currentTimeMillis()
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    fun View.onSingleClick(action: (View) -> Unit) {
        this.setOnClickListener {
            if (System.currentTimeMillis() - lastClickTime >= 500) {
                animateScaleEffect()
                lastClickTime = System.currentTimeMillis()
                action(it)
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    fun View.onSingleClickOut(time:Int = 500, action: (View) -> Unit) {
        this.setOnClickListener {
            if (System.currentTimeMillis() - lastClickTime >= time) {
                animateScaleOutEffect()
                lastClickTime = System.currentTimeMillis()
                action(it)
            }
        }
    }


    @SuppressLint("ClickableViewAccessibility")
    fun View.viewClick(action: (View) -> Unit) {
        this.setOnClickListener {
            animateScaleEffect()
            action(it)
        }
    }

    fun isFirstPermission(context: Context): Boolean {
        val preferences: SharedPreferences =
            context.getSharedPreferences(FIRST_PERMISSION, Context.MODE_PRIVATE)
        return preferences.getBoolean(FIRST_PERMISSION_KEY, true)
    }

    fun setFirstPermission(context: Context, isFirstAccess: Boolean) {
        val preferences: SharedPreferences =
            context.getSharedPreferences(FIRST_PERMISSION, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putBoolean(FIRST_PERMISSION_KEY, isFirstAccess)
        editor.apply()
    }

    fun isRate(context: Context): Boolean {
        val preferences: SharedPreferences =
            context.getSharedPreferences(RATE, Context.MODE_PRIVATE)
        return preferences.getBoolean(RATE_KEY, false)
    }

    fun setRate(context: Context, isFirstAccess: Boolean) {
        val preferences: SharedPreferences =
            context.getSharedPreferences(RATE, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putBoolean(RATE_KEY, isFirstAccess)
        editor.apply()
    }

    fun setCountBack(context: Context, countBack: Int) {
        val preferences: SharedPreferences =
            context.getSharedPreferences(COUNT_BACK, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putInt(COUNT_BACK_KEY, countBack)
        editor.apply()
    }

    fun isCountBack(context: Context): Int {
        val preferences: SharedPreferences =
            context.getSharedPreferences(COUNT_BACK, Context.MODE_PRIVATE)
        return preferences.getInt(COUNT_BACK_KEY, 0)
    }

    fun Activity.shareApp() {
        ShareCompat.IntentBuilder.from(this)
            .setType("text/plain")
            .setChooserTitle("Chooser title")
            .setText("http://play.google.com/store/apps/details?id=" + (this).packageName)
            .startChooser()
    }

    fun Activity.policy() {
        val url =
            "https://sites.google.com/andesgroup.app/policy-chibi-maker"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }

    /*fun reviewApp(context: Activity, isBackPress: Boolean) {
        val manager = ReviewManagerFactory.create(context)
        val request = manager.requestReviewFlow();
        request.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val reviewInfo = task.result
                Log.e("ReviewInfo", "" + reviewInfo.toString())
                val flow = (context as Activity?)?.let { manager.launchReviewFlow(it, reviewInfo) }
                flow?.addOnCompleteListener { task2: Task<Void> ->
                    if (isBackPress) {
                        exitProcess(0)
                    }
                }
            } else {
                if (isBackPress) {
                    exitProcess(0)
                }
            }
        }
    }*/

    const val IS_STORAGE = "IS_STORAGE"
    const val STORAGE_KEY = "STORAGE_KEY"
    fun isStoragePermission(context: Context): Int {
        val preferences: SharedPreferences =
            context.getSharedPreferences(IS_STORAGE, Context.MODE_PRIVATE)
        return preferences.getInt(STORAGE_KEY, 0)
    }

    fun setStoragePermission(context: Context, count: Int) {
        val preferences: SharedPreferences =
            context.getSharedPreferences(IS_STORAGE, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putInt(STORAGE_KEY, count)
        editor.apply()
    }

    const val IS_NOTIFICATION = "IS_NOTIFICATION"
    const val NOTIFICATION_KEY = "NOTIFICATION_KEY"
    fun isNotificationPermission(context: Context): Int {
        val preferences: SharedPreferences =
            context.getSharedPreferences(IS_NOTIFICATION, Context.MODE_PRIVATE)
        return preferences.getInt(NOTIFICATION_KEY, 0)
    }

    fun setNotificationPermission(context: Context, count: Int) {
        val preferences: SharedPreferences =
            context.getSharedPreferences(IS_NOTIFICATION, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putInt(NOTIFICATION_KEY, count)
        editor.apply()
    }

    val shimmer = Shimmer.AlphaHighlightBuilder()
        .setDuration(1800)
        .setBaseAlpha(0.7f)
        .setHighlightAlpha(0.6f)
        .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
        .setAutoStart(true)
        .build()

   /* val shimmerDrawable = ShimmerDrawable().apply {
        setShimmer(shimmer)
    }*/

    var mediaPlayer: MediaPlayer? = null
    var isFromOtherScreen: Boolean = false
    var statusSound: Boolean = true
    val VALUE_COME_BACK_HOME = "VALUE_COME_BACK_HOME"
    val DOWNLOAD_ALBUM = "GhostMaker"
    val BACKGROUND_IMAGE = "Background Image"
//    var listSaveChibi: ArrayList<DataModel> = arrayListOf()
    val KEY_CHIBI = "key_chibi"
    val FROM_CREATE = "from_create"
    val FROM_CHOOSE = "from_choose"
/*    var editChibi: DataModel? = null
    var dataChooseCategory: CategoryModel? = null*/
    val FROM_SUC = "from_suc"
    val EDIT_CHIBI = "EDIT_CHIBI"
    val FROM_CATEGORY = "from_category"
}