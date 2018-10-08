package com.example.andrelindemo

import org.springframework.web.bind.annotation.*

data class Message(val text: String, val priority: String)

@RestController
class MessageController {
    @RequestMapping("/message")
    fun message(): Message {
        return Message("Google Cloud says Hello", "High")
    }
}
