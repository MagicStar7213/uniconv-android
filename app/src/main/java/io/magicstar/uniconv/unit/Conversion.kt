package io.magicstar.uniconv.unit

import kotlin.math.floor

fun conversion(value: Double, type1: Double, type2: Double): Number {
    val res = value * (type1 / type2)
    return if (floor(res) == res)
        res.toInt()
    else res
}

fun heatConvert(value: Double, type1: String, type2: String): Number {
    val res: Double = if (type1 == "ºC" && type2 == "ºK")
        value + 273.15
    else if (type1 == "ºK" && type2 == "ºC")
        value - 273.15
    else if (type1 == "ºC" && type2 == "ºF")
        (value * 9 / 5) + 32
    else if (type1 == "ºF" && type2 == "ºC")
        (value - 32) * 5 / 9
    else if (type1 == "ºF" && type2 == "ºK")
        (value - 32) * 5 / 9 + 273.15
    else if (type1 == "ºK" && type2 == "ºF")
        (value - 273.15) * 9 / 5 + 32
    else
        0.0
    return if (floor(res) == res)
        res.toInt()
    else res
}