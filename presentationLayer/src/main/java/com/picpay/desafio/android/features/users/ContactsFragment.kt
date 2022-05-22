package com.picpay.desafio.android.features.users

import android.os.Bundle
import android.view.View
import com.picpay.desafio.android.R
import com.picpay.desafio.android.base.BaseFragment
import com.picpay.desafio.android.databinding.FragmentContactsBinding
import com.thomas.archtecture_framework.state.ViewState
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
                    showMessage(getString(R.string.error))
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

}
