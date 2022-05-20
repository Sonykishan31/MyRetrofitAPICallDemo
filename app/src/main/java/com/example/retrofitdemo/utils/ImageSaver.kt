package com.vendor.sterlingvendorapp.utils

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import com.example.retrofitdemo.R

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.util.*


class ImageSaver(private val context: Context) {

    private var directoryName = context.getString(R.string.app_name)
    private var fileName = "image.png"
    private var external: Boolean = false

    fun setFileName(fileName: String): ImageSaver {
        this.fileName = fileName
        return this
    }

    fun setExternal(external: Boolean): ImageSaver {
        this.external = external
        return this
    }

    fun setDirectoryName(directoryName: String): ImageSaver {
        this.directoryName = directoryName
        return this
    }

    fun check_file_exists(fileName: String): Boolean {
        var exits = false

        val pictureFile = File(getAlbumStorageDir(directoryName), fileName)

        if (pictureFile.exists()) {
            exits = true
        }
        return exits


    }

    fun save(bitmapImage: Bitmap): File {

        val file = createFile()
        var fileOutputStream: FileOutputStream? = null


        try {
            fileOutputStream = FileOutputStream(file)
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
            fileOutputStream.flush()
            fileOutputStream.close()
            MediaScannerConnection.scanFile(
                context, arrayOf(file.absolutePath),
                arrayOf("images/*")
            ) { path, uri -> Log.i("ScanCompleted", "Scanned $path:") }

        } catch (e: Exception) {
            e.printStackTrace()


        } finally {
            try {
                fileOutputStream?.close()

            } catch (e: IOException) {
                e.printStackTrace()

            }

        }

        return file
    }

    fun createFile(): File {
        val directory: File
        if (external) {
            directory = getAlbumStorageDir(directoryName)
        } else {
            directory = context.getDir(directoryName, Context.MODE_PRIVATE)
        }
        Log.e("ImageSaver", "filename:$fileName")

        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            return directory
        }
        return File(directory, (0..100).shuffled().last().toString()+""+fileName)
    }

    fun getAlbumStorageDir(albumName: String): File {

        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            val resolver = context.contentResolver
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                //   put(MediaStore.MediaColumns.MIME_TYPE, "image/png")
                put(
                    MediaStore.MediaColumns.RELATIVE_PATH,
                    Environment.DIRECTORY_PICTURES + "/" + albumName
                )
            }

            val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

            return File(getPathFromURI(context, uri!!))
        } else {
            val file = File(
                Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES
                ), albumName
            )
            if (!file.mkdirs()) {
                Log.e("ImageSaver", "Directory not created")

                file.mkdirs()
            }
            return file
        }
    }

    fun load(): Bitmap? {
        var inputStream: FileInputStream? = null
        try {
            inputStream = FileInputStream(createFile())
            return BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) {
            e.printStackTrace()

        } finally {
            try {
                inputStream?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
        return null
    }

    fun deleteFolder() {
        var pictureFile: File? = null


        pictureFile = getAlbumStorageDir(directoryName)


        if (pictureFile.isDirectory && pictureFile != null) {
            val children = pictureFile.list()
            if (children != null) {

                for (i in children.indices) {
                    File(pictureFile, children[i]).delete()
                }
            }
        }
    }

    companion object {
        var audioFileName = "recording.mp4"
        var tempaudioFileName = "temprecording"

        val isExternalStorageWritable: Boolean
            get() {
                val state = Environment.getExternalStorageState()
                return Environment.MEDIA_MOUNTED == state
            }

        val isExternalStorageReadable: Boolean
            get() {
                val state = Environment.getExternalStorageState()
                return Environment.MEDIA_MOUNTED == state || Environment.MEDIA_MOUNTED_READ_ONLY == state
            }

        fun drawableToBitmap(drawable: Drawable): Bitmap {

            if (drawable is BitmapDrawable) {
                return drawable.bitmap
            }

            val bitmap = Bitmap.createBitmap(
                drawable.intrinsicWidth,
                drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)

            return bitmap
        }
    }

    fun getPathFromURI(context: Context, contentUri: Uri): String {
        var mediaCursor: Cursor? = null
        try {
            val dataPath = arrayOf(MediaStore.Images.Media.DATA)
            mediaCursor = context.contentResolver.query(contentUri, dataPath, null, null, null)
            val column_index = mediaCursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            mediaCursor.moveToFirst()
            return mediaCursor.getString(column_index)
        } finally {
            if (mediaCursor != null) {
                mediaCursor.close()
            }
        }
    }

}
