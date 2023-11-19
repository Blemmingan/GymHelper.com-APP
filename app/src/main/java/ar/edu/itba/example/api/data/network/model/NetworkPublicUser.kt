package ar.edu.itba.example.api.data.network.model

import ar.edu.itba.example.api.data.model.PublicUser
import com.google.gson.annotations.SerializedName
import java.util.Date

class NetworkPublicUser(
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
) {
    fun asModel(): PublicUser {
        return PublicUser(
            id = id,
            name = name,
            detail = detail,
            avatarUrl = avatarUrl,
            date = date,
            lastActivity = lastActivity
        )
    }
}


