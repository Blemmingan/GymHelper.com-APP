package ar.edu.itba.example.api.data.network.model

import ar.edu.itba.example.api.data.model.CycleExercise
import com.google.gson.annotations.SerializedName

class NetworkCycleExercise(
    @SerializedName("order")
    var order: Int?,
    @SerializedName("duration")
    var duration: Int?,
    @SerializedName("repetitions")
    var repetitions: Int?,
    @SerializedName("exercise")
    var exercise: NetworkExercise?
) {
    fun asModel(): CycleExercise{
        return CycleExercise(
            order = order,
            duration = duration,
            repetitions = repetitions,
            exercise = exercise?.asModel()
        )
    }
}
