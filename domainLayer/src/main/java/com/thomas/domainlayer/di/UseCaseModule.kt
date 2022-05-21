package com.thomas.domainlayer.di

import com.thomas.domainlayer.features.users.GetContactsUseCase
import com.thomas.domainlayer.features.users.UsersRepository
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        GetContactsUseCase(
            get() as UsersRepository
        )
    }
}
