package com.thomas.datalayer.di

import com.thomas.datalayer.features.users.remote.UsersAPIDataSource
import com.thomas.datalayer.features.users.UsersRepositoryImpl
import com.thomas.datalayer.features.users.local.UsersLocalDataSource
import com.thomas.domainlayer.features.users.UsersRepository
import org.koin.dsl.module

val repositoryModule = module {

    factory<UsersRepository> {
        UsersRepositoryImpl(
            get() as UsersAPIDataSource,
            get() as UsersLocalDataSource
        )
    }

}
