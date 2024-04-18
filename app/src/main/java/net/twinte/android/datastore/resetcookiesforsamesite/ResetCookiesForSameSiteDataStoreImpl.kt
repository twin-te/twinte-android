package net.twinte.android.datastore.resetcookiesforsamesite

import android.content.Context
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ResetCookiesForSameSiteDataStoreImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : ResetCookiesForSameSiteDataStore {
    private val preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)

    override var shouldResetCookiesForSameSite: Boolean
        get() = preferences.getBoolean(KEY_RESET_COOKIES_FOR_SAMESITE, true)
        set(value) {
            preferences.edit {
                putBoolean(KEY_RESET_COOKIES_FOR_SAMESITE, value)
            }
        }

    companion object {
        const val FILE_NAME = "reset_cookies_For_samesite"
        private const val KEY_RESET_COOKIES_FOR_SAMESITE = "resetCookiesForSameSite"
    }
}
