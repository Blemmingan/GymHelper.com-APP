package ar.edu.itba.example.api.data.network.model

import ar.edu.itba.example.api.data.model.Exercise
import com.google.gson.annotations.SerializedName
import java.util.Date

class NetworkExercise(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("detail")
    var detail: String?,
    @SerializedName("type")
    var type: String?,
    @SerializedName("duration")
    var duration: Int?,
    @SerializedName("date")
    var date: Date?
) {
    fun asModel(): Exercise {
        return Exercise(
            id = id,
            name = name,
            detail = detail,
            type = type,
            duration = duration,
            date = date,
        )
    }
}