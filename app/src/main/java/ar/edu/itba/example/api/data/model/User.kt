package ar.edu.itba.example.api.data.model

import java.util.Date

data class User(
    var id: Int?,
    var username: String,
    var firstName: String,
    var lastName: String,
    var email: String,
    var lastActivity: Date? = null
)

