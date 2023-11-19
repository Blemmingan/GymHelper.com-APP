package ar.edu.itba.example.api.data.network.model

import ar.edu.itba.example.api.data.model.Category
import com.google.gson.annotations.SerializedName


class NetworkCategory(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("detail")
    var detail: String?,
) {
    fun asModel(): Category {
        return Category(
            id = id,
            name = name,
            detail = detail
        )
    }
}
