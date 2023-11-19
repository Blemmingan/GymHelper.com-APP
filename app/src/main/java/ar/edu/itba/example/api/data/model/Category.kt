package ar.edu.itba.example.api.data.model

import ar.edu.itba.example.api.data.network.model.NetworkCategory

class Category (
    var id: Int?,
    var name: String?,
    var detail: String?
){
    fun asNetworkModel(): NetworkCategory {
        return NetworkCategory(
            id = id,
            name = name,
            detail = detail
        )
    }
}