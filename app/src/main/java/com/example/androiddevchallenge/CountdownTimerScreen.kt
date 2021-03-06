package com.example.androiddevchallenge

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CountDownTimerScreen(modifier: Modifier = Modifier) {
    var totalSeconds by rememberSaveable { mutableStateOf(0) }

//    val formatted = "%02d:%02d".format(minutes, seconds)

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
                onClick = { totalSeconds = incrementTotalSecondsByHour(totalSeconds) },
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
                onClick = { totalSeconds = decrementTotalSecondsByHour(totalSeconds) },
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
                onClick = { totalSeconds = incrementTotalSecondsByMinute(totalSeconds) },
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
                onClick = { totalSeconds = decrementTotalSecondsByMinute(totalSeconds) },
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
                onClick = { totalSeconds = incrementTotalSeconds(totalSeconds) },
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
                onClick = { totalSeconds = decrementTotalSeconds(totalSeconds) },
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = null,
                    modifier = Modifier.size(48.dp)
                )
            }
        }
    }
}
