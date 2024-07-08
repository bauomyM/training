package com.example.demo.dataClasses

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.io.Serializable

@Document(collection = "owner")
data class Owner(
    @Id
    val id: ObjectId?,
    val name: String,
    val phoneNumber: String,
):Serializable {
}