package net.twinte.android.datastore.user

interface UserDataStore {
    suspend fun validateGoogleIdToken(idToken: String): Boolean
}
