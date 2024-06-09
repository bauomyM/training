package com.example.demo.Controller

import com.example.demo.Service.BusService
import com.example.demo.dataClasses.Bus
import org.springframework.graphql.data.method.annotation.Argument

import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller



@Controller
class BusController(val busService: BusService) {

    @QueryMapping
    fun buses():List<Bus>{
        return busService.load()
    }

    @QueryMapping
    fun findBusByCapacity(@Argument capacity:Int):List<Bus>{
        return busService.findByCapacity(capacity)
    }




}