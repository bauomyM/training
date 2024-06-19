package com.example.demo.dataClasses

import org.springframework.data.annotation.Id
import java.io.Serializable


data class Car(
    @Id val id: Int,
    val name: String,
    val model: Int,
    val owner: String


): Serializable {
    override fun toString(): String {
        return "($name, $model), $owner"
    }
}