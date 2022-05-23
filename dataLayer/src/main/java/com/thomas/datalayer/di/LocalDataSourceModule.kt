package com.thomas.datalayer.di

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.thomas.datalayer.features.users.local.model.UserEntity
import com.thomas.datalayer.features.users.local.UsersDAO
import com.thomas.datalayer.features.users.local.UsersLocalDataSource
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

const val databaseName = "database_picpay"
val localDataSourceModule = module {
    single {
        provideDatabase(androidApplication())
    }

    factory {
        UsersLocalDataSource(
            get() as AppDatabase
        )
    }
}

fun provideDatabase(application: Application) = Room
    .databaseBuilder(
        application,
        AppDatabase::class.java, databaseName
    ).build()

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UsersDAO
}
