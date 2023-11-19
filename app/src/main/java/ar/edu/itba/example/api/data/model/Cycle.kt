package ar.edu.itba.example.api.data.model

import ar.edu.itba.example.api.data.network.model.NetworkCycle

class Cycle(
    var id: Int?,
    var name: String?,
    var detail: String?,
    var type: String?,
    var order: Int?,
    var repetitions: Int?,
){
    fun asNetworkModel(): NetworkCycle {
        return NetworkCycle(
            id = id,
            name = name,
            detail = detail,
            type = type,
            order = order,
            repetitions = repetitions
        )
    }
}