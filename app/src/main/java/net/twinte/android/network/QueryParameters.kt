package net.twinte.android.network

/**
 * リクエスト送信時に指定するクエリパラメータを保持するためのデータ構造
 */
typealias QueryParameters = MutableMap<String, String>

/**
 * 空のクエリパラメータ
 */
fun QueryParameters(): QueryParameters = mutableMapOf()

/**
 * クエリパラメータを指定するための shorthand 用関数
 */
fun params(block: QueryParameters.() -> Unit): QueryParameters =
    QueryParameters().apply(block)
