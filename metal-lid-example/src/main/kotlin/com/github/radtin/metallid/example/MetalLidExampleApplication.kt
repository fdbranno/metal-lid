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

    @Bean
    fun dataSource(): DataSource = YamlFileDataSource(file)

    @Bean
    fun runner(dataSource: DataSource): Runner = TestNGRunner(dataSource)

}

@Service
class MetalLidExampleService(private val runner: Runner) {

    @PostConstruct
    fun execute() = runner.run()

}



