package com.example.demo.dataClasses

import org.springframework.data.annotation.Id

data class Bus(@Id val id: Int,
               val name: String,
               val model: Int,
               val capacity: Int
)
