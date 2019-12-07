package org.jid.tests.sbperftest.coroutines.calcandsave

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.springframework.data.r2dbc.core.*
import org.springframework.stereotype.Component
import reactor.core.publisher.toMono

@Component
internal class CalcAndSaveRepo(private val db: DatabaseClient) {

    private val table = "coroutines_save_and_calc"

    suspend fun save(model: CalcAndSaveModel) {
        db.insert().into<CalcAndSaveModel>()
                .table(table)
                .using(model)
                .await()
    }

    fun saveSync(model: CalcAndSaveModel) = runBlocking {
        db.insert().into<CalcAndSaveModel>()
                .table(table)
                .using(model)
                .await()
    }


    fun count():Long = runBlocking {
        db.execute("SELECT COUNT(*) from $table")
                .asType<Long>()
                .fetch()
                .awaitOne()
    }


    suspend fun findAll(): Flow<CalcAndSaveModel> = db.select()
            .from(table)
            .asType<CalcAndSaveModel>()
            .fetch()
            .flow()

}