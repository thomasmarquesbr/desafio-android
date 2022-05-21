package com.picpay.desafio.android.features.users

import android.os.Bundle
import android.view.View
import com.picpay.desafio.android.R
import com.picpay.desafio.android.base.BaseFragment
import com.picpay.desafio.android.databinding.FragmentContactsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactsFragment : BaseFragment<FragmentContactsBinding>() {

    override val layoutId = R.layout.fragment_contacts
    override val viewModel by viewModel<ContactsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}
