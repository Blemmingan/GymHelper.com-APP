package ar.edu.itba.example.api.data.model

import ar.edu.itba.example.api.data.network.model.NetworkExercise
import java.util.Date

class Exercise(
    var id: Int?,
    var name: String?,
    var detail: String?,
    var type: String?,
    var duration: Int?,
    var date: Date?
) {
    fun asNetworkModel(): NetworkExercise {
        return NetworkExercise(
            id = id,
            name = name,
            detail = detail,
            type = type,
            duration = duration,
            date = date,
        )
    }
}