package com.github.radtin.metallidservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MetalLidServiceApplication

fun main(args: Array<String>) {
    runApplication<MetalLidServiceApplication>(*args)
}