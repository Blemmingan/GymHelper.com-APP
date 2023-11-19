package ar.edu.itba.example.api.data.network.model

import com.google.gson.annotations.SerializedName

data class NetworkCycle(
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
)
