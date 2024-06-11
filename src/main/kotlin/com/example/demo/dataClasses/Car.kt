package com.example.demo.dataClasses

import org.springframework.data.annotation.Id


data class Car(
    @Id val id: Int,
    val name: String,
    val model: Int,
    val owner: String


){
    override fun toString(): String {
        return "($name, $model), $owner"
    }
}