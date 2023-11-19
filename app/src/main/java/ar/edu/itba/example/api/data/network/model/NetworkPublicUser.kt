package ar.edu.itba.example.api.data.network.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class NetworkPublicUser(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("gender")
    var detail: String?,
    @SerializedName("avatarUrl")
    var avatarUrl: String?,
    @SerializedName("date")
    var date: Date?,
    @SerializedName("lastActivity")
    var lastActivity: Date?
)


