package com.cleanarchitecture.data.koin

import com.cleanarchitecture.data.repository.RemoteRepositoryImpl
import com.cleanarchitecture.data.repository.SharedPrefRepositoryImpl
import com.cleanarchitecture.data.source.remote.api.RestClient
import com.cleanarchitecture.domain.repository.RemoteRepository
import com.cleanarchitecture.domain.repository.SharedPrefRepository
import org.koin.dsl.module

val dataKoinModule = module {

    factory<RemoteRepository> { RemoteRepositoryImpl(get()) }
    factory<SharedPrefRepository> { SharedPrefRepositoryImpl(get()) }
    single { RestClient(get()) }
}