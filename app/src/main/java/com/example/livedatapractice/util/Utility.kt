package com.example.livedatapractice.util

import kotlin.math.roundToInt

const val EXCLUDE_PART = "current,minutely";
const val UNITS = "metric";

fun convertFahrenheitToCelsius(fahrenheit: Float): Float = ((5F/9F * (fahrenheit - 32F)) * 10).roundToInt() / 10F