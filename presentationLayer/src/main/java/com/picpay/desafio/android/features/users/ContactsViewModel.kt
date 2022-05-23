package com.picpay.desafio.android.features.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.base.BaseViewModel
import com.picpay.desafio.android.extensions.loadViewState
import com.picpay.desafio.android.features.users.model.UserUIModel
import com.thomas.archtecture_framework.state.ViewState
import com.thomas.archtecture_framework.wrapper.BaseError
import com.thomas.domainlayer.features.users.GetContactsFailureFactory
import com.thomas.domainlayer.features.users.GetContactsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class ContactsViewModel(
    override var dispatcherIO: CoroutineDispatcher,
    private val getContactsUseCase: GetContactsUseCase
) : BaseViewModel() {

    private val _getContactsState =
        MutableLiveData<ViewState<List<UserUIModel>, GetContactsFailureFactory<BaseError>>>()
    val getContactsState: LiveData<ViewState<List<UserUIModel>, GetContactsFailureFactory<BaseError>>> get() = _getContactsState

    fun loadDetails(cached: Boolean = false) {
        _getContactsState.postValue(ViewState.Loading())
        viewModelScope.launch(dispatcherIO) {
            val params = GetContactsUseCase.Params(fromCache = cached)
            val resultWrapper = getContactsUseCase.runAsync(params)
            val viewState = loadViewState(resultWrapper.transformSuccess {
                UserUIModel.mapFrom(it)
            })

            _getContactsState.postValue(viewState)
        }
    }

}
