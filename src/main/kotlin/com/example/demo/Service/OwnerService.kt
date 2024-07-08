package com.example.demo.Service

import com.example.demo.Repo.OwnerRepository
import com.example.demo.dataClasses.Owner
import org.springframework.stereotype.Service

@Service
class OwnerService(
    private val ownerRepository: OwnerRepository
) {

    fun getAllOwners():List<Owner>{
        return ownerRepository.findAll()
    }
}