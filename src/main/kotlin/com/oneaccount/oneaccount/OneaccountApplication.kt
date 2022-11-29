package com.oneaccount.oneaccount

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@SpringBootApplication
@EntityScan
class OneaccountApplication

fun main(args: Array<String>) {
    runApplication<OneaccountApplication>(*args)
}
