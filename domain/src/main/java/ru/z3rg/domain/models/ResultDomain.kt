package ru.z3rg.domain.models

data class ResultDomain<T>(
    val body: T?,
    val errorMessage: String?
)
