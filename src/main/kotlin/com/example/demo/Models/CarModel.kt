package com.example.demo.Models

import com.example.demo.dataClasses.Owner
import org.springframework.data.annotation.Id
import java.io.Serializable

class CarModel(
    @Id val id: Int,
    val name: String,
    val model: Int,
    val ownerId: String? = null,
    val phoneNumber: String,
    val ownerName: String

) : Serializable {
    // Needs to accommodate changing owner attributes
    override fun toString(): String {
        return "($name, $model), $ownerName"
    }
}
