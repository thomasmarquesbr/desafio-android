package com.picpay.desafio.android.di

import com.picpay.desafio.android.base.BaseViewModel
import com.picpay.desafio.android.features.users.ContactsViewModel
import com.thomas.domainlayer.features.users.GetContactsUseCase
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        BaseViewModel()
    }

    viewModel {
        ContactsViewModel(
            Dispatchers.IO,
            get() as GetContactsUseCase
        )
    }
}
