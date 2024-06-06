package com.example.demo
import org.springframework.web.bind.annotation.*
import org.springframework.beans.factory.annotation.Autowired

@RestController
@RequestMapping("/api/users")

class UserController {
    @Autowired
    lateinit var userRepository: UserRepository

    @GetMapping
    fun getAllUsers(): List<User> = userRepository.findAll()

    @PostMapping
    fun createUser(@RequestBody user: User): User = userRepository.save(user)
}