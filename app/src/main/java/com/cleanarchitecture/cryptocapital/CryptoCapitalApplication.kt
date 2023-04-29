package com.cleanarchitecture.cryptocapital

import android.app.Application
import com.cleanarchitecture.data.koin.dataKoinModule
import com.cleanarchitecture.data.source.remote.settings.Setting
import com.cleanarchitecture.domain.koin.domainKoinModule
import com.cleanarchitecture.cryptocapital.di.appModule
import com.cleanarchitecture.cryptocapital.utils.AppConstants
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CryptoCapitalApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        Setting.BASE_URL = AppConstants.BASE_URL
        startKoin {
            androidContext(this@CryptoCapitalApplication)
            modules(appModule, dataKoinModule, domainKoinModule)
        }
    }

}