package com.example.demo.dataClasses

import com.example.demo.Repo.OwnerRepository
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.io.Serializable

@Document(collection = "car")
data class Car(
    @Id val id: Int,
    val name: String,
    val model: Int,
    val ownerId: ObjectId?,

    ): Serializable{
    // Needs to accommodate changing owner attributes
    override fun toString(): String {
        return "($name, $model), $ownerId"
    }

}