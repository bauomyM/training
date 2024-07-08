package com.example.demo.Resolvers

import com.example.demo.Repo.OwnerRepository
import com.example.demo.dataClasses.Car
import com.example.demo.dataClasses.Owner
import org.springframework.stereotype.Component


@Component
class CarResolver(private val ownerRepository: OwnerRepository) {

    fun getOwner(car: Car): Owner? {
        println(car.ownerId)
        return car.ownerId?.let { ownerId ->
            ownerRepository.findByObjectId(ownerId)
        }
    }
}