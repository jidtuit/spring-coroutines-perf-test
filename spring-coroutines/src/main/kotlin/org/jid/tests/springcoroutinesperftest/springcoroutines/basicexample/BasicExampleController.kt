package org.jid.tests.springcoroutinesperftest.springcoroutines.basicexample

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/basicExample")
class BasicExampleController {

    @GetMapping("/hello")
    fun hello(): HelloResponse = HelloResponse("World!!")

    @GetMapping("/helloSuspend")
    suspend fun helloSuspended(): HelloResponse = HelloResponse("World of suspended functions!!")

    @GetMapping("/helloFlow/{size}")
    fun helloFlow(@PathVariable size : Int): Flow<HelloResponse> = flow {
        (0 until size).forEach {
            emit(HelloResponse("World of Flow num $it!!"))
        }
    }

    data class HelloResponse(val response: String, val action: String = "hello")
}

