package ru.z3rg.domain.repository

import ru.z3rg.domain.models.Livescore
import ru.z3rg.domain.models.ResultDomain

interface AllSportsRepository {

    suspend fun getLivescore(): ResultDomain<Livescore>
}