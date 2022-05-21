package com.thomas.datalayer.di

import com.thomas.datalayer.features.users.UsersAPI
import com.thomas.datalayer.features.users.UsersAPIDataSource
import org.koin.dsl.module

val apiDataSourceModule = module {

    factory {
        UsersAPIDataSource(
            get() as UsersAPI
        )
    }

}
