package ru.z3rg.footballtoday.ui.screens.main

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import ru.z3rg.domain.models.Event
import ru.z3rg.domain.models.Livescore

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    MainScreen(
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
}

@Composable
fun MainScreen(
    state: Livescore
) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 32.dp, vertical = 16.dp),
        content = {
        items(state.events) {
            Column(
                modifier = Modifier
                    .padding(bottom = 16.dp)
            ) {
                Text(
                    modifier = Modifier
                        .padding(bottom = 6.dp)
                        .fillMaxWidth(),
                    text = it.leagueName!!,
                    style = TextStyle(
                        fontWeight = FontWeight.Light
                    )
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                rememberAsyncImagePainter(
                                    model = it.homeTeamLogo
                                ),
                                "homeTeamLogo",
                                modifier = Modifier
                                    .padding(bottom = 2.dp)
                                    .height(30.dp)
                                    .width(30.dp)
                            )
                            Text(
                                text = it.eventHomeTeam!!,
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold
                                ),
                                modifier = Modifier
                                    .padding(start = 6.dp)
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                rememberAsyncImagePainter(
                                    model = it.awayTeamLogo
                                ),
                                "awayTeamLogo",
                                modifier = Modifier
                                    .height(30.dp)
                                    .width(30.dp)
                            )
                            Text(
                                text = it.eventAwayTeam!!,
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold
                                ),
                                modifier = Modifier
                                    .padding(start = 6.dp)
                            )

                        }

                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(top = 2.dp)
                                    .height(30.dp)
                                    .width(30.dp),
                                text = it.homeGoals,
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                            )
                            Text(
                                modifier = Modifier
                                    .height(30.dp)
                                    .width(30.dp),
                                text = it.awayGoals,
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                            )
                        }
                        Text(
                            text = "Status: " + it.eventStatus!!,
                            style = TextStyle(

                            )
                        )
                    }
                }
            }
        }
    })
}