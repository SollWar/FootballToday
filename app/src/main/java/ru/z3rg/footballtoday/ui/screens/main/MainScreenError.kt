package ru.z3rg.footballtoday.ui.screens.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.z3rg.footballtoday.ui.screens.main.models.MainScreenEvent
import ru.z3rg.footballtoday.ui.screens.main.models.MainScreenState


@Preview
@Composable
fun MainScreenError(
    onState: (MainScreenState) -> Unit = {},
    onReloadClick: (MainScreenEvent) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            text = "Не удалось загрузить",
            style = TextStyle(
                textAlign = TextAlign.Center
            )
        )
        Button(
            modifier = Modifier
                .padding(top = 16.dp)
                .width(200.dp)
                .height(60.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            onClick = {
                onReloadClick(MainScreenEvent.ReloadLivescore)
            }
        ) {
            Text(
                text = "Повторить",
                style = TextStyle(
                    textAlign = TextAlign.Center
                )
            )
        }
    }

    LaunchedEffect(key1 = "", block = {
        onState(MainScreenState.Error)
    })
}