package com.example.kotlindagger.model

interface Storage {

    fun setString(key: String, value: String)

    fun getString(key: String): String

    fun removeString(key: String)
}