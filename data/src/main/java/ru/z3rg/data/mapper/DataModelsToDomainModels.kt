package ru.z3rg.data.mapper

import ru.z3rg.data.models.AllSportsApiAnswer
import ru.z3rg.data.models.GoalScorers
import ru.z3rg.data.models.Result
import ru.z3rg.domain.models.Event
import ru.z3rg.domain.models.Goals
import ru.z3rg.domain.models.Livescore

fun allSportsApiAnswerToLivescore(allSportsApiAnswer: AllSportsApiAnswer): Livescore {
    val livescore = Livescore()
    allSportsApiAnswer.result.forEach {
        livescore.addEvent(resultToEvent(it))
    }
    return livescore
}

fun resultToEvent(result: Result): Event {
    val event = Event(
        leagueName = result.league_name,
        eventHomeTeam = result.event_home_team,
        eventAwayTeam = result.event_away_team,
        homeTeamLogo = result.home_team_logo,
        awayTeamLogo = result.away_team_logo,
        eventFinalResult = result.event_final_result,
        eventStatus = result.event_status
    )
    result.goalscorers.forEach {
        event.addGoals(goalScorersToGoal(it))
    }
    return event
}

fun goalScorersToGoal(goalScorers: GoalScorers): Goals {
    return Goals(
        score = goalScorers.score,
        awayScorer = goalScorers.away_scorer,
        homeScorer = goalScorers.home_scorer,
        time = goalScorers.time
    )
}