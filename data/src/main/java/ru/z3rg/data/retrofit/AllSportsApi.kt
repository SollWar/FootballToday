package ru.z3rg.data.retrofit

import dagger.Provides
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.z3rg.data.models.AllSportsApiAnswer

interface AllSportsApi {

    @Provides
    @GET("/football/")
    suspend fun getLivescore(
        @Query("met") met: String = "Livescore",
        @Query("APIkey") key: String = "94ac406ffed72d82e0c662dc1274c7d0b0020dff00c45577c3f8ee782264ac3f",
        //@Query("timezone") timezone: String = "+03:00"
    ): Response<AllSportsApiAnswer>
}