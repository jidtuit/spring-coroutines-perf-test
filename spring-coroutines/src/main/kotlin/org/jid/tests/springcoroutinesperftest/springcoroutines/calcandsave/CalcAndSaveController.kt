package org.jid.tests.springcoroutinesperftest.springcoroutines.calcandsave

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/calcAndSave-suspend")
class CalcAndSaveController(private val service: CalcAndSaveService) {

    @PostMapping("/createDb")
    suspend fun createDb(@RequestParam("rows") rows:Optional<Int>,
                        @RequestParam("delay") delay:Optional<Long>): Flow<Unit> {
        return (0 until rows.orElse(3)).asFlow()
                .map{ CalcAndSaveModel(id = null) }
                .map{
                    delay.ifPresent(Thread::sleep)
                    service.save(it)
                }
    }

    @GetMapping
    suspend fun findAll(): Flow<CalcAndSaveModel> = service.findAll()

}