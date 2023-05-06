package net.twinte.android.repository

interface UserRepository {
    suspend fun validateGoogleIdToken(idToken: String): Boolean
}
