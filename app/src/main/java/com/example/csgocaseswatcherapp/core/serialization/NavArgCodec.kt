package com.example.csgocaseswatcherapp.core.serialization

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

object NavArgCodec {

    private val json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
        prettyPrint = false
    }

    fun <T> encode(value: T, serializer: KSerializer<T>): String {
        val raw = json.encodeToString(serializer, value)
        return Uri.encode(raw)
    }

    fun <T> decode(encoded: String, serializer: KSerializer<T>): T {
        val raw = Uri.decode(encoded)
        return json.decodeFromString(serializer, raw)
    }

    fun <T> listSerializer(item: KSerializer<T>): KSerializer<List<T>> =
        ListSerializer(item)
}

fun <T> navTypeOf(serializer: KSerializer<T>): NavType<T> =
    object : NavType<T>(isNullableAllowed = false) {

        override fun get(bundle: Bundle, key: String): T? {
            val str = bundle.getString(key) ?: return null
            return NavArgCodec.decode(str, serializer)
        }

        override fun parseValue(value: String): T {
            return NavArgCodec.decode(value, serializer)
        }

        override fun serializeAsValue(value: T): String {
            return NavArgCodec.encode(value, serializer)
        }

        override fun put(bundle: Bundle, key: String, value: T) {
            bundle.putString(key, serializeAsValue(value))
        }
    }