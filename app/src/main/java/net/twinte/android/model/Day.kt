package net.twinte.android.model

import com.google.gson.annotations.SerializedName

enum class Day(val d: String) {
    @SerializedName("Sun")
    Sun("日"),

    @SerializedName("Mon")
    Mon("月"),

    @SerializedName("Tue")
    Tue("火"),

    @SerializedName("Wed")
    Wed("水"),

    @SerializedName("Thu")
    Thu("木"),

    @SerializedName("Fri")
    Fri("金"),

    @SerializedName("Sat")
    Sat("土"),
}
