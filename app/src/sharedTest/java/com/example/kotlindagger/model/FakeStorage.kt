package com.example.kotlindagger.model

import javax.inject.Inject

class FakeStorage @Inject constructor() : Storage {
    private val map = mutableMapOf<String, String>()

    override fun setString(key: String, value: String) {
        map[key] = value
    }

    override fun getString(key: String): String {
        return map[key].orEmpty()
    }

    override fun removeString(key: String) {
        map.remove(key)
    }
}