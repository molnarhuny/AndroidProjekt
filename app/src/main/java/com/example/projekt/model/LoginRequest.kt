package com.example.projekt.model

import com.squareup.moshi.JsonClass

// The generated JsonAdapter serializing/deserializing to, and from JSON of the specified type.
@JsonClass(generateAdapter = true)
data class LoginRequest(var email: String, var passwordKey: String )
