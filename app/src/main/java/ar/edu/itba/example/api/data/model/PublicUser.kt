package ar.edu.itba.example.api.data.model

import ar.edu.itba.example.api.data.network.model.NetworkPublicUser
import java.util.Date

class PublicUser (
    var id: Int?,
    var name: String?,
    var detail: String?,
    var avatarUrl: String?,
    var date: Date?,
    var lastActivity: Date?
){
    fun asNetworkModel(): NetworkPublicUser {
        return NetworkPublicUser(
            id = id,
            name = name,
            detail = detail,
            avatarUrl = avatarUrl,
            date = date,
            lastActivity = lastActivity
        )
    }
}