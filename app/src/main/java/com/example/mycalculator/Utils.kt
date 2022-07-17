package com.example.mycalculator

fun String.replaceLast(v: String): String {
    return this.dropLast(1).plus(v)
}