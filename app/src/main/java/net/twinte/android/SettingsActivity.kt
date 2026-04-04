package net.twinte.android

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import dagger.hilt.android.AndroidEntryPoint
import net.twinte.android.datastore.schedulenotification.ScheduleNotificationDataStore
import javax.inject.Inject

@AndroidEntryPoint
class SettingsActivity : AppCompatActivity() {
    @Inject
    lateinit var scheduleNotificationDataStore: ScheduleNotificationDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val scheduleOptionLabels =
            resources.getStringArray(R.array.schedule_key)
                .zip(resources.getStringArray(R.array.schedule_label))
        val defaultScheduleTiming = resources.getStringArray(R.array.default_schedule).toList()
        val versionName = packageManager.getPackageInfo(packageName, 0).versionName.orEmpty()

        setContent {
            SettingsScreen(
                title = "Androidアプリの設定",
                sharedPreferences = sharedPreferences,
                versionName = versionName,
                scheduleOptionLabels = scheduleOptionLabels,
                defaultScheduleTiming = defaultScheduleTiming,
                onBack = ::finish,
                onScheduleNotificationChanged = scheduleNotificationDataStore::schedule,
                onOpenLicense = {
                    if (BuildConfig.DEBUG) {
                        Toast.makeText(
                            this,
                            "debug ビルドではライセンス一覧が生成されないため、release ビルドで確認してください",
                            Toast.LENGTH_LONG,
                        ).show()
                    } else {
                        OssLicensesMenuActivity.setActivityTitle("オープンソースライセンス")
                        startActivity(Intent(this, OssLicensesMenuActivity::class.java))
                    }
                },
                onOpenTwitter = {
                    openUrl("https://twitter.com/te_twin")
                },
                onOpenMail = {
                    startActivity(
                        Intent(Intent.ACTION_SENDTO).apply {
                            data = Uri.parse("mailto:info@twinte.net")
                        },
                    )
                },
                onOpenWebsite = {
                    openUrl("https://www.twinte.net")
                },
                onOpenGithub = {
                    openUrl("https://github.com/twin-te")
                },
            )
        }
    }

    private fun openUrl(url: String) {
        startActivity(
            Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(url)
            },
        )
    }
}
