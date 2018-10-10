package com.example.demo

import com.samskivert.mustache.Mustache
import org.springframework.boot.Banner
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
@EnableConfigurationProperties(DemoProperties::class)
class DemoApplication  {

    @Bean
    fun mustacheCompiler(loader: Mustache.TemplateLoader?) =
            Mustache.compiler().escapeHTML(false).withLoader(loader)

    @Bean
    fun databaseInitializer(userRepository: UserRepository,
                            articleRepository: ArticleRepository) = CommandLineRunner {
        val andrelin = User("andrelin", "Andreas", "Lind-Johansen")
        userRepository.save(andrelin)

        articleRepository.save(Article(
                "Google Cloud Kubernetes Engine",
                "Great orchestration tool!",
                "Orchestration is Cool!",
                andrelin,
                3
        ))
        articleRepository.save(Article(
                "Spring Boot Kotlin application on Google Kubernetes Engine Tutorial",
                "Tutorial from cloud.google.com",
                "https://cloud.google.com/community/tutorials/kotlin-springboot-container-engine",
                andrelin,
                4
        ))
        articleRepository.save(Article(
                "Spring Boot Kotlin Blog Tutorial",
                "Tutorial from Spring.io",
                "https://spring.io/guides/tutorials/spring-boot-kotlin/",
                andrelin,
                5
        ))
        articleRepository.save(Article(
                "Docker Containers",
                "What are Docker containers?",
                "Docker Containers are like tiny VMs that you can easily build and send out into the world",
                andrelin,
                2
        ))
        articleRepository.save(Article(
                "Kotlin Programming Language",
                "What is Kotlin?",
                "Kotlin is made by a developer team from JetBrains.",
                andrelin,
                1
        ))
    }
}

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}
