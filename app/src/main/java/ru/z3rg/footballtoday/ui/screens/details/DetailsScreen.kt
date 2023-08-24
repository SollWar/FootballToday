package ru.z3rg.footballtoday.ui.screens.details

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import coil.compose.rememberAsyncImagePainter
import ru.z3rg.footballtoday.ui.screens.details.models.DetailsScreenState

@Composable
fun DetailsScreen(
    state: DetailsScreenState
) {
    Log.d("DetailsScreenState", state.event.toString())
    Column(
        modifier = Modifier
            .padding(bottom = 6.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .padding(bottom = 6.dp)
                .fillMaxWidth(),
            text = state.event.leagueName!!
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
                    painter = rememberAsyncImagePainter(model = state.event.homeTeamLogo),
                    contentDescription = "Home Team Logo"
                )
                Text(
                    text = state.event.eventHomeTeam!!,
                    style = TextStyle(
                        textAlign = TextAlign.Center
                    )
                )
            }
            Text(
                modifier = Modifier
                    .weight(0.5f),
                text = state.event.eventFinalResult!!,
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
                    painter = rememberAsyncImagePainter(model = state.event.awayTeamLogo),
                    contentDescription = "Away Team Logo"
                )
                Text(
                    text = state.event.eventAwayTeam!!,
                    style = TextStyle(
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
        Text(
            modifier = Modifier
                .padding(top = 6.dp, bottom = 6.dp)
                .fillMaxWidth(),
            text = "Goals:"
        )
        state.event.goals.forEach {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .weight(0.3f),
                    text = it.time + "'",
                    style = TextStyle(
                        textAlign = TextAlign.Center
                    )
                )
                Text(
                    modifier = Modifier
                        .weight(1f),
                    text = it.homeScorer,
                    style = TextStyle(
                        textAlign = TextAlign.Center
                    )
                )
                Column(
                    modifier = Modifier
                        .weight(0.5f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = it.score,
                        style = TextStyle(
                            textAlign = TextAlign.Center
                        )
                    )
                }
                Text(
                    modifier = Modifier
                        .weight(1f),
                    text = it.awayScorer,
                    style = TextStyle(
                        textAlign = TextAlign.Center
                    )
                )
                Spacer(
                    modifier = Modifier
                        .weight(0.3f)
                )
            }
        }
        if (state.event.eventStatus!!.isDigitsOnly()) {
            Text(
                modifier = Modifier
                    .padding(top = 6.dp)
                    .fillMaxWidth(),
                text = state.event.eventStatus!! + "'"
            )
        } else {
            Text(
                modifier = Modifier
                    .padding(top = 6.dp)
                    .fillMaxWidth(),
                text = state.event.eventStatus!!
            )
        }
    }
}