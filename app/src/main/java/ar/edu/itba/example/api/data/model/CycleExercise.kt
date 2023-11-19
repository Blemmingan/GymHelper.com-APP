package ar.edu.itba.example.api.data.model

import ar.edu.itba.example.api.data.network.model.NetworkCycleExercise

class CycleExercise(
    var order: Int?,
    var duration: Int?,
    var repetitions: Int?,
    var exercise: Exercise?,
) {
    fun asNetworkModel(): NetworkCycleExercise{
        return NetworkCycleExercise(
            order = order,
            duration = duration,
            repetitions = repetitions,
            exercise = exercise?.asNetworkModel()
        )
    }
}