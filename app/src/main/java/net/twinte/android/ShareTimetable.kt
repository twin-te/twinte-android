package net.twinte.android

import android.content.Intent
import android.graphics.*
import android.webkit.WebView
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream

const val LOGO_SIZE = 200f
const val LOGO_MARGIN = 100f

object ShareTimetable {
    fun share(webView: WebView) = webView.context.run {
        val bmp = Bitmap.createBitmap(webView.width, webView.height, Bitmap.Config.ARGB_8888)

        Canvas(bmp).also { canvas ->
            webView.draw(canvas)
            val logo = BitmapFactory.decodeResource(resources, R.drawable.twinte)
            canvas.drawBitmap(
                logo,
                Rect(0, 0, logo.width, logo.height),
                RectF(
                    bmp.width - LOGO_SIZE - LOGO_MARGIN,
                    bmp.height - LOGO_SIZE - LOGO_MARGIN,
                    bmp.width - LOGO_MARGIN,
                    bmp.height - LOGO_MARGIN
                ),
                Paint()
            )
        }

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
            putExtra(Intent.EXTRA_TEXT, "今回の時間割\n#twinte")
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            type = "image/png"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        webView.context.startActivity(shareIntent)
    }
}
