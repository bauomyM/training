# Car Management System

## Overview
This application is a Car Management System built using Spring Boot, Kotlin, Kafka, Redis, and MongoDB. It allows users to manage car records and supports caching for improved performance.

## Features
- *CRUD Operations*: Create, Read, Update, and Delete car records.
- *Kafka Integration*: Real-time message processing with Kafka.
- *Redis Caching*: Caching of frequently accessed car records.
- *GraphQL API*: Easy-to-use GraphQL API for querying and mutating data.
- *Dockerized Setup*: Easy deployment with Docker Compose.

## Prerequisites
- Java 17 installed.
- MongoDB installed (optional if not using Docker).
- Maven 3.6.3
- Redis cache
- Apache Kafka

## Install 
bash
docker run --name tr-database -d -p 27017:27017 -v ~/mongo-data:/data/db mongo

## Running in your local machine without docker

clone the repo from https://github.com/bauomyM/training/

bash
git clone https://github.com/bauomyM/training/

- MongoDB: Ensure MongoDB is running locally.
- Kafka: Ensure Kafka and Zookeeper are running locally.
- Redis: Ensure Redis server is running locally.

Run the file containing the main (KotlinTrainingApplication.kt)

#Running using docker
On linux:
  docker compose -f <"name of compose File"> up 
On IntelliJ enc:
  docker compose up
