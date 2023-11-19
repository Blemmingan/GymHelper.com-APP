package ar.edu.itba.example.api.data.network.model

import ar.edu.itba.example.api.data.model.Cycle
import com.google.gson.annotations.SerializedName

class NetworkCycle(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("detail")
    var detail: String?,
    @SerializedName("type")
    var type: String?,
    @SerializedName("order")
    var order: Int?,
    @SerializedName("repetitions")
    var repetitions: Int?,
) {
    fun asModel(): Cycle {
        return Cycle(
            id = id,
            name = name,
            detail = detail,
            type = type,
            order = order,
            repetitions = repetitions
        )
    }
}
