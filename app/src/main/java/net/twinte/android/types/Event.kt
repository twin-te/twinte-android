package net.twinte.android.types

data class Event (
    val description: String,
    val event_type: EventType,
    val metadata: Map<String,String>,
    val date: String
)
