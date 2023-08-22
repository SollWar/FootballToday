package ru.z3rg.domain.models

data class Event(
    val leagueName: String? = "", // league_name
    val eventHomeTeam: String? = "", // event_home_team
    val eventAwayTeam: String? = "", // event_away_team
    val homeTeamLogo: String? = "", // home_team_logo
    val awayTeamLogo: String? = "", // away_team_logo
    val eventFinalResult: String? = "", // event_final_result
    val eventStatus: String? = "", // event_status
    val homeGoals: String = "",
    val awayGoals: String = ""
)
