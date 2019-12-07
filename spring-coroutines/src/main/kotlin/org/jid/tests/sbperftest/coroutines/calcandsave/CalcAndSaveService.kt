package org.jid.tests.sbperftest.coroutines.calcandsave

import kotlinx.coroutines.flow.Flow

interface CalcAndSaveService {
    suspend fun save(model: CalcAndSaveModel)
    fun saveSync(model: CalcAndSaveModel)
    suspend fun findAll(): Flow<CalcAndSaveModel>
}