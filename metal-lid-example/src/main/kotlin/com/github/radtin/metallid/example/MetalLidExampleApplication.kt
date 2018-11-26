package com.github.radtin.metallid.example

import com.github.radtin.metallid.datasource.DataSource
import com.github.radtin.metallid.datasource.YamlFileDataSource
import com.github.radtin.metallid.runner.Runner
import com.github.radtin.metallid.runner.TestNGRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Service
import java.io.File
import javax.annotation.PostConstruct

@SpringBootApplication
class MetalLidExampleApplication

fun main(args: Array<String>) {
    runApplication<MetalLidExampleApplication>(*args)
}

@Configuration
class MetalLidExampleConfiguration {

    private val file = File("metal-lid-example/src/main/resources/test.yaml")
    private val configFile = File("metal-lid-example/src/main/resources/config.yaml")

    @Bean
    fun yamlFileDataSource(): DataSource = YamlFileDataSource(file, configFile)

    @Bean
    fun runner(yamlFileDataSource: DataSource): Runner = TestNGRunner(yamlFileDataSource)

}

@Service
class MetalLidExampleService(private val runner: Runner) {

    @PostConstruct
    fun execute() = runner.run()

}



