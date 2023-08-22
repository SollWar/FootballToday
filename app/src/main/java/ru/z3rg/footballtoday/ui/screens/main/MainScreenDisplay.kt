package ru.z3rg.footballtoday.ui.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import coil.compose.rememberAsyncImagePainter
import ru.z3rg.domain.models.Event
import ru.z3rg.domain.models.Livescore
import ru.z3rg.footballtoday.ui.screens.main.models.MainScreenState

@Preview(backgroundColor = 0xFFC1C1C1, showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    MainScreenDisplay(
        MainScreenState.Display(
            Livescore(
                events = arrayListOf(
                    Event(
                        leagueName = "Лига",
                        eventHomeTeam = "Домашние",
                        eventAwayTeam = "Гости",
                        homeTeamLogo = "",
                        awayTeamLogo = "",
                        eventFinalResult = "1 - 1",
                        eventStatus = "Пеналитити",
                        homeGoals = "1",
                        awayGoals = "1"
                    ),
                    Event(
                        leagueName = "Лига",
                        eventHomeTeam = "Домашние",
                        eventAwayTeam = "Гости",
                        homeTeamLogo = "https://apiv2.allsportsapi.com/logo/26467_mafra-u23.jpg",
                        awayTeamLogo = "",
                        eventFinalResult = "1 - 1",
                        eventStatus = "Пеналитити",
                        homeGoals = "1",
                        awayGoals = "1"
                    )
                )
            )
        )
    )
}

@Composable
fun MainScreenDisplay(
    state: MainScreenState.Display,
    onState: (MainScreenState) -> Unit = {},
    onItemClick: (Event) -> Unit = {}
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        content = {
            items(state.livescore.events) {
                Column(
                    modifier = Modifier
                        .padding(bottom = 6.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White)
                        .fillMaxWidth()
                        .clickable {
                            onItemClick(it)
                        }
                ) {
                    Text(
                        modifier = Modifier
                            .padding(bottom = 6.dp)
                            .fillMaxWidth(),
                        text = it.leagueName!!
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                modifier = Modifier
                                    .width(60.dp)
                                    .height(60.dp),
                                painter = rememberAsyncImagePainter(model = it.homeTeamLogo),
                                contentDescription = "Home Team Logo"
                            )
                            Text(
                                text = it.eventHomeTeam!!,
                                style = TextStyle(
                                    textAlign = TextAlign.Center
                                )
                            )
                        }
                        Text(
                            modifier = Modifier
                                .weight(0.5f),
                            text = it.eventFinalResult!!,
                            style = TextStyle(
                                textAlign = TextAlign.Center
                            )
                        )
                        Column(
                            modifier = Modifier
                                .weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                modifier = Modifier
                                    .width(60.dp)
                                    .height(60.dp),
                                painter = rememberAsyncImagePainter(model = it.awayTeamLogo),
                                contentDescription = "Away Team Logo"
                            )
                            Text(
                                text = it.eventAwayTeam!!,
                                style = TextStyle(
                                    textAlign = TextAlign.Center
                                )
                            )
                        }
                    }
                    if (it.eventStatus!!.isDigitsOnly()) {
                        Text(
                            modifier = Modifier
                                .padding(top = 6.dp)
                                .fillMaxWidth(),
                            text = it.eventStatus!! + "'"
                        )
                    } else {
                        Text(
                            modifier = Modifier
                                .padding(top = 6.dp)
                                .fillMaxWidth(),
                            text = it.eventStatus!!
                        )
                    }

                }
            }
        })

    LaunchedEffect(key1 = "", block = {
        onState(MainScreenState.Display())
    })
}