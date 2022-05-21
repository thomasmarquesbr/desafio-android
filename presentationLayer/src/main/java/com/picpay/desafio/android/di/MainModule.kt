package com.picpay.desafio.android.di

import com.thomas.datalayer.di.dataModule
import com.thomas.domainlayer.di.domainModule

val mainModule = dataModule + domainModule + presentationModule
