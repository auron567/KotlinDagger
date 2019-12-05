package com.example.kotlindagger.app

import kotlin.random.Random

fun randomInt(): Int {
    return Random.nextInt(until = 100)
}