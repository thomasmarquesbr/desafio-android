package com.picpay.desafio.android.base

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.thomas.archtecture_framework.wrapper.Event
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.component.KoinComponent

open class BaseViewModel(
    open var dispatcherIO: CoroutineDispatcher = Dispatchers.IO
) : ViewModel(), KoinComponent {

    private val _navigation = MutableLiveData<Event<NavigationCommand>>()
    val navigation: LiveData<Event<NavigationCommand>> = _navigation

    private val _showProgress = MutableLiveData(false)
    val showProgress: MutableLiveData<Boolean> get() = _showProgress

    private val _showMessage = MutableLiveData<String?>(null)
    val showMessage: MutableLiveData<String?> get() = _showMessage

    fun goTo(directions: NavDirections) {
        navigate(NavigationCommand.To(directions))
    }

    fun goToNavigationCommand(command: NavigationCommand) {
        navigate(command)
    }

    fun goBack() {
        navigate(NavigationCommand.Back)
    }

    fun goBackToLogin() {
        navigate(NavigationCommand.BackToLogin)
    }

    fun goToHome() {
        navigate(NavigationCommand.GoToHome)
    }

    @VisibleForTesting
    fun navigate(command: NavigationCommand) {
        _navigation.value = Event(command)
    }

    fun setStatusProgress(status: Boolean) {
        _showProgress.postValue(status)
    }

    fun showMessage(message: String?) {
        _showMessage.postValue(message)
    }

    open fun onBackPressed() {
        goBack()
    }

}
