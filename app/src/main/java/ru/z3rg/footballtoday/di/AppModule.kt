package ru.z3rg.footballtoday.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.z3rg.data.repository.AllSportsRepositoryImpl
import ru.z3rg.data.retrofit.AllSportsApi
import ru.z3rg.domain.repository.AllSportsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://apiv2.allsportsapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideAllSportsApi(): AllSportsApi {
        return provideRetrofit()
            .create(AllSportsApi::class.java)
    }

    @Provides
    fun provideAllSportsRepository(allSportsApi: AllSportsApi): AllSportsRepository {
        return AllSportsRepositoryImpl(allSportsApi)
    }
}