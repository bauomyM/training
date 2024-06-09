package com.example.demo.Service

import com.example.demo.Repo.BusRepository
import org.springframework.stereotype.Service
import com.example.demo.dataClasses.Bus


@Service
class BusService(val busRepository: BusRepository) {

    fun load():List<Bus>{
        return busRepository.findAll()
    }

    fun save( bus: Bus){
        busRepository.save(bus)
    }

    fun findByCapacity(capacity:Int):List<Bus>{
        return busRepository.findByCapacity(capacity)
    }

}