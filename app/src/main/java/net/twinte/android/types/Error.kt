package net.twinte.android.types

data class Error(val message: String, val errors: Array<Map<String, String>>)
