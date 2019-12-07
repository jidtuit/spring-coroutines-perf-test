package org.jid.tests.sbperftest.coroutines.calcandsave

import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Service

@Service
internal class CalcAndSaveServiceImpl(private val repo: CalcAndSaveRepo): CalcAndSaveService {

    override suspend fun save(model: CalcAndSaveModel) = repo.save(model)
    override fun saveSync(model: CalcAndSaveModel) = repo.saveSync(model)

    override suspend fun findAll(): Flow<CalcAndSaveModel> = repo.findAll()
}