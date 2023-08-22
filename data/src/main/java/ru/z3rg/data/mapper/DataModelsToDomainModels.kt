package ru.z3rg.data.mapper

import ru.z3rg.data.models.AllSportsApiAnswer
import ru.z3rg.data.models.Result
import ru.z3rg.domain.models.Event
import ru.z3rg.domain.models.Livescore

fun allSportsApiAnswerToLivescore(allSportsApiAnswer: AllSportsApiAnswer): Livescore {
    val livescore = Livescore()
    allSportsApiAnswer.result.forEach {
        livescore.addEvent(resultToEvent(it))
    }
    return livescore
}

fun resultToEvent(result: Result): Event {
    return Event(
        leagueName = result.league_name,
        eventHomeTeam = result.event_home_team,
        eventAwayTeam = result.event_away_team,
        homeTeamLogo = result.home_team_logo,
        awayTeamLogo = result.away_team_logo,
        eventFinalResult = result.event_final_result,
        eventStatus = result.event_status,
        homeGoals = result.event_final_result.split("-")[0],
        awayGoals = result.event_final_result.split("-")[1]
    )
}