package net.twinte.android.datastore.resetcookiesforsamesite

/**
 * ref: https://github.com/twin-te/twinte-android/issues/44
 * SameSite 属性が付与されていない twinte_session Cookie があるとき、
 * lTWINS からの時間割インポートに失敗するため、一度 WebView が利用できる Cookie を
 * すべて削除し、再度 Twin:te の認証を行わせるために利用される DataStore
 */
interface ResetCookiesForSameSiteDataStore {
    var shouldResetCookiesForSameSite: Boolean
}
