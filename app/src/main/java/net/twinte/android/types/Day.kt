package net.twinte.android.types

import com.google.gson.annotations.SerializedName

enum class Day(val d: String) {
    @SerializedName("日")
    Sun("日"),
    @SerializedName("月")
    Mon("月"),
    @SerializedName("火")
    Tue("火"),
    @SerializedName("水")
    Wed("水"),
    @SerializedName("木")
    Thu("木"),
    @SerializedName("金")
    Fri("金"),
    @SerializedName("土")
    Sat("土")
}
