package net.twinte.android.model

import com.google.gson.annotations.SerializedName

enum class Module(val m: String) {
    @SerializedName("SpringA")
    SpringA("春A"),

    @SerializedName("SpringB")
    SpringB("春B"),

    @SerializedName("SpringC")
    SpringC("春C"),

    @SerializedName("FallA")
    FallA("秋A"),

    @SerializedName("FallB")
    FallB("秋B"),

    @SerializedName("FallC")
    FallC("秋C"),

    @SerializedName("SummerVacation")
    SummerVacation("夏季休業中"),

    @SerializedName("SpringVacation")
    SpringVacation("春季休業中"),

    @SerializedName("Annual")
    Annual("通年"),

    @SerializedName("Unknown")
    Unknown("不明")
}
