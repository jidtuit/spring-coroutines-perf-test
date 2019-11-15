package org.jid.tests.springcoroutinesperftest.springcoroutines.calcandsave

import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.core.*
import org.springframework.stereotype.Component

@Component
internal class CalcAndSaveRepo(private val db: DatabaseClient) {

    private val table = "coroutines_save_and_calc"

    suspend fun save(model: CalcAndSaveModel) {
        db.insert().into<CalcAndSaveModel>()
                .table(table)
                .using(model)
                .await()
    }


    suspend fun findAll(): Flow<CalcAndSaveModel> = db.select()
            .from(table)
            .asType<CalcAndSaveModel>()
            .fetch()
            .flow()

}