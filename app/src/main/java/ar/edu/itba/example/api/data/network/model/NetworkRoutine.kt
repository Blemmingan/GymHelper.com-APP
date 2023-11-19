package ar.edu.itba.example.api.data.network.model

import ar.edu.itba.example.api.data.model.Routine
import com.google.gson.annotations.SerializedName
import java.util.Date

class NetworkRoutine(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("detail")
    var detail: String?,
    @SerializedName("date")
    var date: Date?,
    @SerializedName("score")
    var score: Float?,
    @SerializedName("isPublic")
    var isPublic: Boolean?,
    @SerializedName("difficulty")
    var difficulty: String?,
    @SerializedName("Category")
    var category: NetworkCategory?,
    @SerializedName("user")
    var user: NetworkPublicUser?,
) {
    fun asModel(): Routine {
        return Routine(
            id = id,
            name = name,
            detail = detail,
            date = date,
            score = score,
            isPublic = isPublic,
            difficulty = difficulty,
            category = category,
            user = user
        )
    }
}
