package com.github.radtin.metallidservice.scenario

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ScenarioController {

    @GetMapping("/test")
    fun getForm(): String = "testing"

}