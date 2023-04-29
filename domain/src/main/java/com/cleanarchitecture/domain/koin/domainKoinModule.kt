package com.cleanarchitecture.domain.koin

import com.cleanarchitecture.domain.interactor.GetCoinChartDataUseCase
import com.cleanarchitecture.domain.interactor.GetCoinDetailUseCase
import com.cleanarchitecture.domain.interactor.GetCoinListUseCase
import org.koin.dsl.module

val domainKoinModule = module {
        factory { GetCoinListUseCase(get()) }
        factory { GetCoinDetailUseCase(get()) }
        factory { GetCoinChartDataUseCase(get()) }
}