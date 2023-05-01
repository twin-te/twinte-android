package net.twinte.android.model

import com.google.gson.annotations.SerializedName

enum class Format(val f: String) {
    @SerializedName("Asynchronous")
    OnlineAsynchronous("オンデマンド"),

    @SerializedName("Synchronous")
    OnlineSynchronous("同時双方向"),

    @SerializedName("FaceToFace")
    FaceToFace("対面"),

    @SerializedName("Others")
    Others("その他")
}
