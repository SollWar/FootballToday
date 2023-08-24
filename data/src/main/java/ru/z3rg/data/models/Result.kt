package ru.z3rg.data.models

data class Result(
    val away_team_key: Int,
    val away_team_logo: String,
    val event_away_team: String,
    val event_final_result: String,
    val event_home_team: String,
    val event_live: String,
    val event_penalty_result: String,
    val event_stadium: String,
    val event_status: String,
    val home_team_logo: String,
    val league_name: String,
    val league_round: String,
    val league_season: String,
    val stage_name: String,
    val goalscorers: List<GoalScorers>
)