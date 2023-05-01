package net.twinte.android

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import java.util.Timer
import kotlin.concurrent.schedule

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        title = "Androidアプリの設定"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.preference_wrapper, SettingsFragment())
            .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return false
    }

    class SettingsFragment : PreferenceFragmentCompat() {

        private var versionTapTime = 0L
        private var versionTapCount = 0
        override fun onPreferenceTreeClick(preference: Preference): Boolean {
            when (preference.key) {
                "license" -> startActivity(Intent(this.context, OssLicensesMenuActivity::class.java))
                "version" -> {
                    if (System.currentTimeMillis() - versionTapTime < 500) {
                        versionTapCount++
                        if (versionTapCount >= 7) {
                            Toast.makeText(
                                this.context,
                                "開発者の方々はGithubでお待ちしています！",
                                Toast.LENGTH_LONG
                            ).show()
                            Timer().schedule(1000) {
                                findPreference<Preference>("github")?.isVisible = true
                            }
                        } else if(versionTapCount >= 4) {
                            Toast.makeText(
                                this.context,
                                "あと${ 7 - versionTapCount}ステップで開発者になります",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    versionTapTime = System.currentTimeMillis()
                }
            }
            return super.onPreferenceTreeClick(preference)
        }

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.preferences, rootKey)
            findPreference<Preference>("github")?.isVisible = false
            findPreference<Preference>("version")?.setSummaryProvider {
                this.context?.run {
                    "version: ${packageManager.getPackageInfo(packageName, 0).versionName}"
                }
            }
        }
    }
}
