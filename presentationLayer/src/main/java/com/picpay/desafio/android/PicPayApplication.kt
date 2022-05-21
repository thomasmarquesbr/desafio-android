package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

open class PicPayApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@PicPayApplication)
            modules(mainModule)
        }
    }

}
