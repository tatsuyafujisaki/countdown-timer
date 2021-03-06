package com.example.androiddevchallenge

fun seconds(totalSeconds: Int): Int = totalSeconds % 60
fun minutes(totalSeconds: Int): Int = (totalSeconds / 60) % 60
fun hours(totalSeconds: Int): Int = totalSeconds / 3600

fun incrementTotalSecondsByHour(totalSeconds: Int) =
    totalSeconds + if (hours(totalSeconds) == 99) -99 * 3600 else 3600

fun decrementTotalSecondsByHour(totalSeconds: Int) =
    totalSeconds + if (hours(totalSeconds) == 0) 99 * 3600 else -3600

fun incrementTotalSecondsByMinute(totalSeconds: Int) =
    totalSeconds + if (minutes(totalSeconds) == 59) -59 * 60 else 60

fun decrementTotalSecondsByMinute(totalSeconds: Int) =
    totalSeconds + if (minutes(totalSeconds) == 0) 59 * 60 else -60

fun incrementTotalSeconds(totalSeconds: Int) =
    totalSeconds + if (seconds(totalSeconds) == 59) -59 else 1

fun decrementTotalSeconds(totalSeconds: Int) =
    totalSeconds + if (seconds(totalSeconds) == 0) 59 else -1
