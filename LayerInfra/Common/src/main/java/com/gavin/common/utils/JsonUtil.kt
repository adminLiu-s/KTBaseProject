package com.gavin.common.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.EntryPoint
import java.lang.Exception
import java.lang.reflect.Type
import javax.inject.Inject

fun Any?.toJson() = JsonUtil.toJson(this)

inline fun <reified T> String?.toAny() = JsonUtil.toAny<T>(this)
fun <T> String?.toAny(clazz: Class<T>) = JsonUtil.toAny(this, clazz)

fun <T> String?.toList(clazz: Class<Array<T>>?) = JsonUtil.toList(this, clazz)
fun <T> String?.toListType(type: Type) = JsonUtil.toListType<T>(this, type)

inline fun <reified T> String?.toMap() = JsonUtil.toMap<T>(this)
fun <T> String?.toMap(clazz: Class<T>?) = JsonUtil.toMap(this, clazz)

object JsonUtil {

    private val gson by lazy { Gson() }

    fun toJson(any: Any?): String? {
        return toJson(gson, any)
    }

    private fun toJson(gson: Gson, any: Any?): String? {
        return kotlin.runCatching {
            gson.toJson(any)
        }.onFailure {
            it.printStackTrace()
            null
        }.getOrDefault(null)
    }

    inline fun <reified T> toAny(json: String?): T? {
        return toAny(json, T::class.java)
    }

    fun <T> toAny(json: String?, clazz: Class<T>): T? {
        return toAny(gson, json, clazz)
    }

    private fun <T> toAny(gson: Gson, json: String?, clazz: Class<T>): T? {
        return kotlin.runCatching {
            gson.fromJson(json, clazz)
        }.onFailure {
            it.printStackTrace()
            null
        }.getOrDefault(null)
    }

    fun <T> toList(json: String?, clazz: Class<Array<T>>?): List<T>? {
        return toList(gson, json, clazz)
    }

    private fun <T> toList(gson: Gson, json: String?, clazz: Class<Array<T>>?): List<T>? {
        return kotlin.runCatching {
            val array: Array<T> = gson.fromJson(json, clazz)
            mutableListOf(*array)
        }.onFailure {
            it.printStackTrace()
            null
        }.getOrDefault(null)
    }

    fun <T> toListType(json: String?, type: Type): List<T>? {
        return toListType(gson, json, type)
    }

    private fun <T> toListType(gson: Gson, json: String?, type: Type): List<T>? {
        return try {
            gson.fromJson(json, type)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    inline fun <reified T> toMap(json: String?): Map<String?, T>? {
        return toMap(json, T::class.java)
    }

    fun <T> toMap(json: String?, clazz: Class<T>?): Map<String?, T>? {
        return toMap(gson, json, clazz)
    }

    private fun <T> toMap(gson: Gson, json: String?, clazz: Class<T>?): Map<String?, T>? {
        return kotlin.runCatching {
            val type = object : TypeToken<Map<String?, T>?>() {}.type
            return gson.fromJson(json, type)
        }.onFailure {
            it.printStackTrace()
            null
        }.getOrDefault(null)
    }
}