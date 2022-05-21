package com.picpay.desafio.android.di

import com.picpay.desafio.android.base.BaseViewModel
import com.picpay.desafio.android.features.users.ContactsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        BaseViewModel()
    }

    viewModel {
        ContactsViewModel()
    }
}
