package ru.z3rg.data.repository

import ru.z3rg.data.mapper.allSportsApiAnswerToLivescore
import ru.z3rg.data.retrofit.AllSportsApi
import ru.z3rg.domain.models.Livescore
import ru.z3rg.domain.models.ResultDomain
import ru.z3rg.domain.repository.AllSportsRepository
import javax.inject.Inject

class AllSportsRepositoryImpl @Inject constructor(
    private val allSportsApi: AllSportsApi
): AllSportsRepository {

    override suspend fun getLivescore(): ResultDomain<Livescore> {
        return try {
            val request = allSportsApi.getLivescore()
            ResultDomain(
                body = allSportsApiAnswerToLivescore(request.body()!!),
                errorMessage = null
            )
        } catch (e: Exception) {
            ResultDomain(
                body = null,
                errorMessage = e.message.toString()
            )
        }
    }

}