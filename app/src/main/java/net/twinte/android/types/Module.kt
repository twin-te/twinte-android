package net.twinte.android.types

import com.google.gson.annotations.SerializedName

enum class Module(val m: String) {
    @SerializedName("春A")
    SpringA("春A"),
    @SerializedName("春B")
    SpringB("春B"),
    @SerializedName("春C")
    SpringC("春C"),
    @SerializedName("秋A")
    FallA("秋A"),
    @SerializedName("秋B")
    FallB("秋B"),
    @SerializedName("秋C")
    FallC("秋C"),
    @SerializedName("夏季休業中")
    SummerVacation("夏季休業中"),
    @SerializedName("春季休業中")
    SpringVacation("春季休業中"),
    @SerializedName("通年")
    Annual("通年"),
    @SerializedName("不明")
    Unknown("不明")
}
