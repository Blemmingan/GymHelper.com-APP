package ar.edu.itba.example.api.data.network.model

import com.google.gson.annotations.SerializedName

data class NetworkCycleExercise(
    @SerializedName("order")
    var order: Int?,
    @SerializedName("duration")
    var duration: Int?,
    @SerializedName("repetitions")
    var repetitions: Int?,
    @SerializedName("exercise")
    var exercise: NetworkExercise?
)
