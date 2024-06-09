package com.example.demo.dataclass

import com.fasterxml.jackson.annotation.JsonProperty

data class User(
    @JsonProperty("id") val id: String,
    @JsonProperty("name") val name: String,
    @JsonProperty("email") val email: String,
    @JsonProperty("gender") val gender: String,
    @JsonProperty("archived") val archived: Boolean?= null,
    @JsonProperty("city") val city: Array<String>?=null
)
