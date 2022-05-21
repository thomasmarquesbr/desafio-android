package com.thomas.datalayer.di

import com.thomas.datalayer.features.users.UsersAPIDataSource
import com.thomas.datalayer.features.users.UsersRepositoryImpl
import com.thomas.domainlayer.features.users.UsersRepository
import org.koin.dsl.module

val repositoryModule = module {

    factory<UsersRepository> {
        UsersRepositoryImpl(
            get() as UsersAPIDataSource
        )
    }

}
