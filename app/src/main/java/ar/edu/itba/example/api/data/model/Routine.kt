package ar.edu.itba.example.api.data.model

import ar.edu.itba.example.api.data.network.model.NetworkCategory
import ar.edu.itba.example.api.data.network.model.NetworkPublicUser
import ar.edu.itba.example.api.data.network.model.NetworkRoutine
import com.google.gson.annotations.SerializedName
import java.util.Date

class Routine(
    var id: Int?,
    var name: String?,
    var detail: String?,
    var date: Date?,
    var score: Float?,
    var isPublic: Boolean?,
    var difficulty: String?,
    var category: NetworkCategory?,
    var user: NetworkPublicUser?,
){
    fun asNetworkModel(): NetworkRoutine {
        return NetworkRoutine(
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
