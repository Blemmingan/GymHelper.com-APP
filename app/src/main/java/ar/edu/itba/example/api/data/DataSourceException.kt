package ar.edu.itba.example.api.data

class DataSourceException(
    var code: Int,
    message: String,
    var details: List<String>?
) : Exception(message)