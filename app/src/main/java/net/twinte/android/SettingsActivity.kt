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
import androidx.core.net.toUri

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
                title = getString(R.string.settings_title),
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
                            getString(R.string.settings_license_debug_message),
                            Toast.LENGTH_LONG,
                        ).show()
                    } else {
                        OssLicensesMenuActivity.setActivityTitle(getString(R.string.settings_license_title))
                        startActivity(Intent(this, OssLicensesMenuActivity::class.java))
                    }
                },
                onOpenTwitter = {
                    openUrl(getString(R.string.settings_twitter_url))
                },
                onOpenMail = {
                    startActivity(
                        Intent(Intent.ACTION_SENDTO).apply {
                            data = getString(R.string.settings_mailto).toUri()
                        },
                    )
                },
                onOpenWebsite = {
                    openUrl(getString(R.string.settings_site_url))
                },
                onOpenGithub = {
                    openUrl(getString(R.string.settings_github_profile_url))
                },
            )
        }
    }

    private fun openUrl(url: String) {
        startActivity(
            Intent(Intent.ACTION_VIEW).apply {
                data = url.toUri()
            },
        )
    }
}
