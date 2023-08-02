package com.alien.math.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun HistoryScreen(modifier: Modifier) {

    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            TODO()
        },
        confirmButton = {
            Box(modifier = modifier
                .fillMaxWidth()
                .height(150.dp)
                ,
                contentAlignment = Alignment.CenterEnd
            ){

            }
        }
    )
}