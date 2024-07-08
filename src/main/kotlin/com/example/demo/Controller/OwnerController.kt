package com.example.demo.Controller

import com.example.demo.Service.OwnerService
import com.example.demo.dataClasses.Owner
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class OwnerController( private val ownerService: OwnerService) {

    @QueryMapping
    fun getAllOwners():List<Owner>{
        return ownerService.getAllOwners()
    }

}