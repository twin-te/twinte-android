package net.twinte.android.types

import com.google.gson.annotations.SerializedName

enum class EventType(val e: String) {
    @SerializedName("Holiday")
    Holiday("休日"),
    @SerializedName("PublicHoliday")
    PublicHoliday("祝日"),
    @SerializedName("Exam")
    Exam("試験"),
    @SerializedName("SubstituteDay")
    SubstituteDay("振替授業日")
}
