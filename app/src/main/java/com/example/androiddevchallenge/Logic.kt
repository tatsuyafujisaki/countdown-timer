/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

fun seconds(totalSeconds: Int): Int = totalSeconds % 60
fun minutes(totalSeconds: Int): Int = (totalSeconds / 60) % 60
fun hours(totalSeconds: Int): Int = totalSeconds / 3600

fun hhmmss(totalSeconds: Int) =
    "%02d:%02d:%02d".format(hours(totalSeconds), minutes(totalSeconds), seconds(totalSeconds))

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
