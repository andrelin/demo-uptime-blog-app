package com.example.demo

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("demo")
class DemoProperties {

    lateinit var title: String
    lateinit var tagline: String
    val banner = Banner()

    class Banner {
        var title: String? = null
        lateinit var content: String
    }

}