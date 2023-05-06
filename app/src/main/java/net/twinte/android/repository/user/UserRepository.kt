package net.twinte.android.repository.user

interface UserRepository {
    suspend fun validateGoogleIdToken(idToken: String): Boolean
}
