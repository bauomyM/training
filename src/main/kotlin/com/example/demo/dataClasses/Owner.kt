package com.example.demo.dataClasses

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import java.io.Serializable

data class Owner(
    @Id
    val id: ObjectId?,
    val name: String,
    val phoneNumber: String,
):Serializable {
}