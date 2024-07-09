package ua.ecommerce.configurablediscounts

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = ["ua.ecommerce.configurablediscounts.config"])
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
