package com.picpay.desafio.android.base

import androidx.navigation.NavDirections

sealed class NavigationCommand {
    data class To(val directions: NavDirections) : NavigationCommand()
    object Back : NavigationCommand()
    object BackToLogin : NavigationCommand()
    object GoToHome : NavigationCommand()
}
