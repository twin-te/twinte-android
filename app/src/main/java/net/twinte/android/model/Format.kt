package net.twinte.android.model

import com.google.gson.annotations.SerializedName

enum class Format(f: String) {
    @SerializedName("Asynchronous")
    OnlineAsynchronous("Asynchronous"),
    @SerializedName("Synchronous")
    OnlineSynchronous("Synchronous"),
    @SerializedName("FaceToFace")
    FaceToFace("FaceToFace"),
    @SerializedName("Others")
    Others("Others")
}
