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

import android.os.CountDownTimer
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.PauseCircle
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

var countDownTimer: CountDownTimer? = null

@Composable
fun CountDownTimerScreen(modifier: Modifier = Modifier) {
    var totalSeconds by rememberSaveable { mutableStateOf(0) }
    var remainingTotalSeconds by rememberSaveable { mutableStateOf(0) }
    var timerState by rememberSaveable { mutableStateOf(TimerState.Stopped) }

    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("HH")
                IconButton(
                    enabled = timerState == TimerState.Stopped,
                    onClick = {
                        totalSeconds = incrementTotalSecondsByHour(totalSeconds)
                    },
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropUp,
                        contentDescription = null,
                        modifier = Modifier.size(48.dp)
                    )
                }
                Text(
                    text = "%02d".format(hours(totalSeconds)),
                    modifier = modifier.padding(bottom = 8.dp),
                    style = MaterialTheme.typography.h5
                )
                IconButton(
                    enabled = timerState == TimerState.Stopped,
                    onClick = {
                        totalSeconds = decrementTotalSecondsByHour(totalSeconds)
                    },
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = null,
                        modifier = Modifier.size(48.dp)
                    )
                }
            }
            Column(
                modifier = modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("MM")
                IconButton(
                    enabled = timerState == TimerState.Stopped,
                    onClick = {
                        totalSeconds = incrementTotalSecondsByMinute(totalSeconds)
                    },
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropUp,
                        contentDescription = null,
                        modifier = Modifier.size(48.dp)
                    )
                }
                Text(
                    text = "%02d".format(minutes(totalSeconds)),
                    modifier = modifier.padding(bottom = 8.dp),
                    style = MaterialTheme.typography.h5
                )
                IconButton(
                    enabled = timerState == TimerState.Stopped,
                    onClick = {
                        totalSeconds = decrementTotalSecondsByMinute(totalSeconds)
                    },
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = null,
                        modifier = Modifier.size(48.dp)
                    )
                }
            }
            Column(
                modifier = modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("SS")
                IconButton(
                    enabled = timerState == TimerState.Stopped,
                    onClick = {
                        totalSeconds = incrementTotalSeconds(totalSeconds)
                    },
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropUp,
                        contentDescription = null,
                        modifier = Modifier.size(48.dp)
                    )
                }
                Text(
                    text = "%02d".format(seconds(totalSeconds)),
                    modifier = modifier.padding(bottom = 8.dp),
                    style = MaterialTheme.typography.h5
                )
                IconButton(
                    enabled = timerState == TimerState.Stopped,
                    onClick = {
                        totalSeconds = decrementTotalSeconds(totalSeconds)
                    },
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = null,
                        modifier = Modifier.size(48.dp)
                    )
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = hhmmss(remainingTotalSeconds),
                style = MaterialTheme.typography.h3
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier = Modifier.padding(16.dp),
                enabled = totalSeconds > 0,
                onClick = {
                    timerState = when (timerState) {
                        TimerState.Running -> {
                            countDownTimer?.cancel()
                            countDownTimer =
                                object :
                                    CountDownTimer(remainingTotalSeconds.toLong() * 1000L, 1000) {
                                    override fun onTick(millisUntilFinished: Long) {
                                        remainingTotalSeconds = millisUntilFinished.toInt() / 1000
                                    }

                                    override fun onFinish() {
                                        timerState = TimerState.Stopped
                                    }
                                }
                            TimerState.Paused
                        }
                        TimerState.Paused -> {
                            countDownTimer =
                                object :
                                    CountDownTimer(remainingTotalSeconds.toLong() * 1000L, 1000) {
                                    override fun onTick(millisUntilFinished: Long) {
                                        remainingTotalSeconds = millisUntilFinished.toInt() / 1000
                                    }

                                    override fun onFinish() {
                                        timerState = TimerState.Stopped
                                    }
                                }
                            countDownTimer?.start()
                            TimerState.Running
                        }
                        TimerState.Stopped -> {
                            remainingTotalSeconds = totalSeconds
                            countDownTimer =
                                object :
                                    CountDownTimer(remainingTotalSeconds.toLong() * 1000L, 1000) {
                                    override fun onTick(millisUntilFinished: Long) {
                                        remainingTotalSeconds = millisUntilFinished.toInt() / 1000
                                    }

                                    override fun onFinish() {
                                        timerState = TimerState.Stopped
                                    }
                                }
                            countDownTimer?.start()
                            TimerState.Running
                        }
                    }
                }
            ) {
                Crossfade(timerState) {
                    when (it) {
                        TimerState.Running -> {
                            Icon(
                                imageVector = Icons.Filled.PauseCircle,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(48.dp)
                            )
                        }
                        else -> {
                            Icon(
                                imageVector = Icons.Filled.PlayCircle,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(48.dp)
                            )
                        }
                    }
                }
            }
            Button(
                onClick = {
                    countDownTimer?.cancel()
                    remainingTotalSeconds = 0
                    timerState = TimerState.Stopped
                }
            ) {
                Text("Reset")
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (totalSeconds > 0) {
                CircularProgressIndicator(
                    modifier = Modifier.size(300.dp),
                    progress = remainingTotalSeconds.toFloat() / totalSeconds,
                    color = Color.Black,
                    strokeWidth = 10.dp
                )
            }
        }
    }
}
