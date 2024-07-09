package com.example.demo.Repo

import com.example.demo.dataClasses.Owner
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface OwnerRepository:MongoRepository<Owner,String> {

}

