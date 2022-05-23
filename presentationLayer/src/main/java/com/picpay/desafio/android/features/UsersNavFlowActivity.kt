package com.picpay.desafio.android.features

import com.picpay.desafio.android.R
import com.picpay.desafio.android.base.BaseActivity
import com.picpay.desafio.android.base.BaseViewModel
import com.picpay.desafio.android.databinding.ActivityUsersNavFlowBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class UsersNavFlowActivity : BaseActivity<ActivityUsersNavFlowBinding>() {

    override val layoutId = R.layout.activity_users_nav_flow
    override val viewModel by viewModel<BaseViewModel>()

}
