package org.jid.tests.sbperftest.coroutines

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringCoroutinesApp { }

fun main(args: Array<String>) {
    runApplication<SpringCoroutinesApp>(*args)
}


