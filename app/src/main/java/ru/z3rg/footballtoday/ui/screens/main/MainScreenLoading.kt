package ru.z3rg.footballtoday.ui.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.z3rg.footballtoday.ui.screens.main.models.MainScreenState


@Composable
fun MainScreenLoading(
    onState: (MainScreenState) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .height(60.dp)
                .width(60.dp)
        )
    }

    LaunchedEffect(key1 = "", block = {
        onState(MainScreenState.Loading)
    })
}