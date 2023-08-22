package ru.z3rg.domain.models

data class Livescore(
    val events: ArrayList<Event> = arrayListOf()
) {
    fun addEvent(event: Event) {
        events.add(event)
    }
}
