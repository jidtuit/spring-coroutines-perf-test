package org.jid.tests.springcoroutinesperftest.springcoroutines.calcandsave

import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Service

@Service
internal class CalcAndSaveServiceImpl(private val repo: CalcAndSaveRepo):CalcAndSaveService {

    override suspend fun save(model: CalcAndSaveModel) = repo.save(model)
    override suspend fun findAll(): Flow<CalcAndSaveModel> = repo.findAll()
}