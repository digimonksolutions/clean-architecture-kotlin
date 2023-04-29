package com.cleanarchitecture.domain.interactor

import com.cleanarchitecture.domain.repository.RemoteRepository
import com.cleanarchitecture.domain.response.Response

class GetCoinChartDataUseCase(private val remoteRepository: RemoteRepository) {

    suspend fun <T> execute(classObject: Class<T>,url:String): Response<T> {
        return remoteRepository.get(classObject,url)
    }
}