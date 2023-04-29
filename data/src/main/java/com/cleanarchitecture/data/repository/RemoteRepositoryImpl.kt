package com.cleanarchitecture.data.repository

import android.content.Context
import com.cleanarchitecture.data.source.remote.api.RestClient
import com.cleanarchitecture.domain.response.Response
import com.cleanarchitecture.domain.repository.RemoteRepository

class RemoteRepositoryImpl(private val restClient: RestClient): RemoteRepository {

    override suspend fun <T> get(classDataObject: Class<T>, url: String): Response<T> {
        return restClient.get(classDataObject,url)
    }

    override suspend fun <T> post(
        classDataObject: Class<T>,
        url: String,
        hashMap: HashMap<String, String>
    ): Response<T> {
        return restClient.post(classDataObject,url,hashMap)
    }

}