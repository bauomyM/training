package com.example.demo.Resolvers

import com.example.demo.Repo.OwnerRepository
import com.example.demo.dataClasses.Car
import com.example.demo.dataClasses.Owner
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component


@Component
class CarResolver(private val ownerRepository: OwnerRepository) {

   fun getOwner(ownerId: String? = null): Owner? =  ownerRepository.findByIdOrNull(ownerId)
}