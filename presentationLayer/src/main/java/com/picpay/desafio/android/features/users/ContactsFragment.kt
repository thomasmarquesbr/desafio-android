package com.picpay.desafio.android.features.users

import android.os.Bundle
import android.view.View
import com.picpay.desafio.android.R
import com.picpay.desafio.android.base.BaseFragment
import com.picpay.desafio.android.databinding.FragmentContactsBinding
import com.thomas.archtecture_framework.state.ViewState
import com.thomas.archtecture_framework.wrapper.BaseError
import com.thomas.domainlayer.features.users.GetContactsFailureFactory
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactsFragment : BaseFragment<FragmentContactsBinding>() {

    override val layoutId = R.layout.fragment_contacts
    override val viewModel by viewModel<ContactsViewModel>()

    private val adapter by lazy { UserListAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initList()
        initObservers()

        viewModel.loadDetails()
    }

    private fun initList() {
        binding.rvContacts.adapter = adapter
    }

    private fun initObservers() {
        viewModel.getContactsState.observe(viewLifecycleOwner) {
            when (it) {
                is ViewState.Empty -> {
                    hideLoadingView()
                    showMessage(getString(R.string.error))
                }
                is ViewState.Error -> {
                    hideLoadingView()
                    handleError(it.error)
                }
                is ViewState.Loading -> {
                    showLoadingView()
                }
                is ViewState.Success -> {
                    hideLoadingView()
                    adapter.submitList(it.success)
                }
            }
        }
    }

    private fun handleError(error: GetContactsFailureFactory<BaseError>?) {
        val errorMessage = when (error) {
            is GetContactsFailureFactory.CustomError1Failure ->
                getString(R.string.error_cause_1)
            is GetContactsFailureFactory.CustomError2Failure ->
                getString(R.string.error_cause_2)
            is GetContactsFailureFactory.CustomError3Failure ->
                getString(R.string.error_cause_3)
            is GetContactsFailureFactory.BaseFailure ->
                error.errorData?.message ?: getString(R.string.error)
            else ->
                getString(R.string.error)
        }
        showMessage(errorMessage)
    }

}
