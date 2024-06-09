package com.example.demo.dataClasses

import org.springframework.data.annotation.Id


data class Car(
    @Id val id: Int,
    var name: String,
    var model: Int,
    var owner: String


){
    override fun toString(): String {
        return "($name, $model), $owner"
    }
}