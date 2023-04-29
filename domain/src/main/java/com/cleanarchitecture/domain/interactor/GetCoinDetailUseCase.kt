package com.cleanarchitecture.domain.interactor

import com.cleanarchitecture.domain.model.CoinDetailResponse
import com.cleanarchitecture.domain.repository.RemoteRepository
import com.cleanarchitecture.domain.response.Response
import kotlin.reflect.KClass

class GetCoinDetailUseCase(private val remoteRepository: RemoteRepository) {

    suspend fun <T> execute(classObject: Class<T>, url:String): Response<T> {
        return remoteRepository.get(classObject,url)
    }
}