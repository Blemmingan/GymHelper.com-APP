package ar.edu.itba.example.api.data.network.model

import com.google.gson.annotations.SerializedName


data class NetworkCategory(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("detail")
    var detail: String?,
)
