package com.example.projekt.model

import com.squareup.moshi.JsonClass

// The generated JsonAdapter serializing/deserializing to, and from JSON of the specified type.
@JsonClass(generateAdapter = true)
data class User(
    var ID: Long,
    var last_name: String,
    var first_name: String,
    var email: String,
    var type: Int,
//    @Nullable
//    var location: String,
//    @Nullable
//    var phone_number: String,
    var department_id: Int
)
