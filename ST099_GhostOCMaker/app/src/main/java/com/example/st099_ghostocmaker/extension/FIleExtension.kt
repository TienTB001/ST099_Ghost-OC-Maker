package com.chibimaker.create.avatar.cutechibi.extension

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import com.chibimaker.create.avatar.cutechibi.utils.SystemUtils.BACKGROUND_IMAGE
import com.chibimaker.create.avatar.cutechibi.utils.SystemUtils.DOWNLOAD_ALBUM
import com.chibimaker.create.avatar.cutechibi.utils.SystemUtils.PICK_IMAGE_REQUEST_CODE
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

internal fun Activity.openImagePicker() {
    val intent = Intent(Intent.ACTION_PICK)
    intent.type = "image/*"
    startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE)
}

internal fun generateRandomFileName(): String {
    val randomNumber = (100000000000..999999999999).random()
    return "IMG_$randomNumber.png"
}

internal fun Activity.saveBitmapToExternalStorage(bitmap: Bitmap) {
    val resolver = contentResolver
    val imageCollection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
    } else {
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI
    }

    val contentValues = ContentValues().apply {
        put(MediaStore.Images.Media.DISPLAY_NAME, "image_${System.currentTimeMillis()}.png")
        put(MediaStore.Images.Media.MIME_TYPE, "image/png")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/$DOWNLOAD_ALBUM")
        } else {
            val directory = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                DOWNLOAD_ALBUM
            )
            if (!directory.exists()) {
                directory.mkdirs()
            }
            val filePath = File(directory, "image_${System.currentTimeMillis()}.png").absolutePath
            put(MediaStore.Images.Media.DATA, filePath)
        }
    }

    val imageUri = resolver.insert(imageCollection, contentValues)
    if (imageUri != null) {
        try {
            val outputStream: OutputStream? = resolver.openOutputStream(imageUri)
            if (outputStream != null) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                outputStream.close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

internal fun Activity.saveBitmapToInternalStorage(
    bitmap: Bitmap
): String? {
    val name = generateRandomFileName()

    return try {
        val directory = File(filesDir, DOWNLOAD_ALBUM)
        if (!directory.exists()) {
            directory.mkdir() // Tạo thư mục nếu nó chưa tồn tại
        }

        // Tạo file mới trong thư mục "BabyPhoto"
        val file = File(directory, name)

        // Mở FileOutputStream để ghi dữ liệu vào file
        val fileOutputStream = FileOutputStream(file)

        // Nén bitmap dưới dạng PNG và ghi vào file
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)

        // Đóng luồng
        fileOutputStream.flush()
        fileOutputStream.close()

        // Trả về đường dẫn đầy đủ của file đã lưu
        file.absolutePath

    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

internal fun Activity.saveBitmapToInternalStorageBackground(
    bitmap: Bitmap, name: String
): String? {

    return try {
        val directory = File(filesDir, BACKGROUND_IMAGE)
        if (!directory.exists()) {
            directory.mkdir() // Tạo thư mục nếu nó chưa tồn tại
        }

        // Tạo file mới trong thư mục "BabyPhoto"
        val file = File(directory, name)

        // Mở FileOutputStream để ghi dữ liệu vào file
        val fileOutputStream = FileOutputStream(file)

        // Nén bitmap dưới dạng PNG và ghi vào file
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)

        // Đóng luồng
        fileOutputStream.flush()
        fileOutputStream.close()

        // Trả về đường dẫn đầy đủ của file đã lưu
        file.absolutePath

    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

internal fun Activity.saveBitmapToInternalStorageZip(bitmap: Bitmap): String? {
    val name = generateRandomFileName()
    // Giảm kích thước ảnh xuống 512x512 px
    val resizedBitmap = Bitmap.createScaledBitmap(bitmap, 512, 512, true)

    return try {
        val directory = File(filesDir, DOWNLOAD_ALBUM)

        if (!directory.exists()) {
            directory.mkdir() // Tạo thư mục nếu nó chưa tồn tại
        }

        // Tạo file mới trong thư mục
        val file = File(directory, "$name.png")

        // Mở FileOutputStream để ghi dữ liệu vào file
        val fileOutputStream = FileOutputStream(file)

        // Nén bitmap với chất lượng giảm dần
        var quality = 100
        do {
            fileOutputStream.flush()
            resizedBitmap.compress(Bitmap.CompressFormat.PNG, quality, fileOutputStream)
            quality -= 5 // Giảm chất lượng sau mỗi lần nén
        } while (file.length() > 512 * 1024 && quality > 5) // 512 KB và chất lượng không dưới 5%

        // Đóng luồng
        fileOutputStream.flush()
        fileOutputStream.close()

        // Giải phóng bộ nhớ
        resizedBitmap.recycle() // Giải phóng bitmap sau khi sử dụng

        // Trả về đường dẫn đầy đủ của file đã lưu
        file.absolutePath
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

@Throws(OutOfMemoryError::class)
internal fun createBimapFromView(view: View): Bitmap {
    try {
        val output = Bitmap.createBitmap(
            view.width,
            view.height,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(output)
        view.draw(canvas)
        return output
    } catch (error: OutOfMemoryError) {
        throw error
    }
}