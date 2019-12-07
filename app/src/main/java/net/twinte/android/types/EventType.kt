package net.twinte.android.types

import com.google.gson.annotations.SerializedName

enum class EventType(val e: String) {
    @SerializedName("休日")
    Holiday("休日"),
    @SerializedName("祝日")
    PublicHoliday("祝日"),
    @SerializedName("試験")
    Exam("試験"),
    @SerializedName("振替授業日")
    SubstituteDay("振替授業日")
}
