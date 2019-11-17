package org.jid.tests.sbperftest.coroutines.calcandsave

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/calcAndSave-suspend")
class CalcAndSaveController(private val service: CalcAndSaveService) {

    @PostMapping("/createDb")
    suspend fun createDbConcurrent(@RequestParam("rows") rows:Optional<Int>,
                        @RequestParam("delay") delay:Optional<Long>) {
         (0 until rows.orElse(3)).asFlow()
                .map{ CalcAndSaveModel(id = null) }
                .map{
                    delay.ifPresent(Thread::sleep)
                    service.save(it)
                }.collect()
    }

    @PostMapping("/createDbParallel")
    suspend fun createDbParallel(@RequestParam("rows") rows:Optional<Int>,
                         @RequestParam("delay") delay:Optional<Long>) {
        (0 until rows.orElse(3)).asFlow()
                .map{ CalcAndSaveModel(id = null) }
                .map{
                    GlobalScope.async {
                        delay.ifPresent(Thread::sleep)
                        service.save(it)
                    }
                }.collect{ it.await() }
    }

    @GetMapping
    suspend fun findAll(): Flow<CalcAndSaveModel> = service.findAll()

}