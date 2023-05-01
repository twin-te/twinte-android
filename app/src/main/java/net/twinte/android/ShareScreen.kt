package net.twinte.android

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.webkit.WebView
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream

fun WebView.shareScreen(body: String) = context.run {
    val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    draw(Canvas(bmp))

    File(cacheDir, "images").mkdir()
    val uri = File(File(cacheDir, "images"), "share.png").let { shareFile ->
        FileOutputStream(shareFile).also { fos ->
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fos)
        }.close()
        FileProvider.getUriForFile(this, "net.twinte.android.fileprovider", shareFile)
    }

    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_STREAM, uri)
        putExtra(Intent.EXTRA_TEXT, body)
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        type = "image/png"
    }

    val shareIntent = Intent.createChooser(sendIntent, null)
    startActivity(shareIntent)
}
