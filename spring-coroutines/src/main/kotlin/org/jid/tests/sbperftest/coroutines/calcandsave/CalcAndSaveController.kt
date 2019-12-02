package org.jid.tests.sbperftest.coroutines.calcandsave

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.springframework.web.bind.annotation.*
import java.util.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

@RestController
@RequestMapping("/calcAndSave-suspend")
class CalcAndSaveController(private val service: CalcAndSaveService) {

    @GetMapping
    suspend fun findAll(): Flow<CalcAndSaveModel> = service.findAll()

    @PostMapping("/createDb")
    suspend fun createDbConcurrent(@RequestParam("rows") rows:Optional<Int>,
                        @RequestParam("delay") delay:Optional<Long>): Flow<Unit> {
         return (0 until rows.orElse(3)).asFlow()
                .map{ calculateProfiles(delay) }
                .map( service::save )
    }

    @ExperimentalCoroutinesApi
    @PostMapping("/createDbCtxChange")
    suspend fun createDbContextChange(@RequestParam("rows") rows:Optional<Int>,
                         @RequestParam("delay") delay:Optional<Long>): Flow<Unit> {
        return (0 until rows.orElse(3)).asFlow()
                .map{ calculateProfiles(delay) }
                .flowOn(Dispatchers.Default)
                .map( service::save)
                .flowOn(Dispatchers.IO)
    }

    @PostMapping("/createDbAsyncAwait")
    suspend fun createDbAsyncAwait(@RequestParam("rows") rows:Optional<Int>,
                                  @RequestParam("delay") delay:Optional<Long>): Flow<Unit> = coroutineScope {

        val numRows = rows.orElse(3)

        val calcs = mutableListOf<Deferred<CalcAndSaveModel>>()
        (0 until numRows).forEach {
            calcs.add( async (Dispatchers.Default) {
                calculateProfiles(delay)
            })
        }

        val saves = mutableListOf<Deferred<Unit>>()
        (0 until numRows).forEach {
            saves.add( async (Dispatchers.IO) { service.save(calcs[it].await()) })
        }

        saves.asFlow()
                .map{ it.await() }
    }

    suspend fun calculateProfiles(delay: Optional<Long>): CalcAndSaveModel {
        delay.ifPresent(Thread::sleep)
        return CalcAndSaveModel(id = null)
    }

}