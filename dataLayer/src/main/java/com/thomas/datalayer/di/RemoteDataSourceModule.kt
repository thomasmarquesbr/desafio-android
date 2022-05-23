package com.thomas.datalayer.di

import com.thomas.datalayer.features.users.remote.UsersAPI
import com.thomas.datalayer.features.users.remote.UsersAPIDataSource
import org.koin.dsl.module

val apiDataSourceModule = module {

    factory {
        UsersAPIDataSource(
            get() as UsersAPI
        )
    }

}
