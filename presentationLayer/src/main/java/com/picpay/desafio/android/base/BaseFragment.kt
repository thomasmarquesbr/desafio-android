package com.picpay.desafio.android.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.picpay.desafio.android.extensions.observeEvent
import com.picpay.desafio.android.BR

abstract class BaseFragment<DATA_BINDING : ViewDataBinding> : Fragment() {

    abstract val layoutId: Int
    abstract val viewModel: BaseViewModel

    private var _binding: DATA_BINDING? = null
    open val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        if (_binding == null) {
            _binding = DataBindingUtil.inflate(
                inflater,
                layoutId,
                container,
                false
            )

            binding.lifecycleOwner = this
            binding.setVariable(BR.viewmodel, this.viewModel)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeNavigation(viewModel)
        assignCallBackOnBackPressed()
    }

    private fun assignCallBackOnBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback {
            viewModel.onBackPressed()
        }
    }

    private fun observeNavigation(viewModel: BaseViewModel) {
        viewModel.navigation.observeEvent(viewLifecycleOwner) { command ->
            when (command) {
                is NavigationCommand.To -> findNavController().navigate(
                    command.directions,
                    getExtras()
                )
                is NavigationCommand.Back -> findNavController().popBackStack()
                is NavigationCommand.GoToHome -> {
                    // Open HomeActivity or navigate to HomeFragment
                }
                is NavigationCommand.BackToLogin -> {
                    // Open LoginActivity or navigate to LoginFragment
                }
            }
        }
    }

    open fun getExtras(): FragmentNavigator.Extras = FragmentNavigatorExtras()

    open fun hideLoadingView() {
        this.viewModel.setStatusProgress(false)
    }

    open fun showLoadingView() {
        this.viewModel.setStatusProgress(true)
    }

    open fun showMessage(message: String? = null) {
        this.viewModel.showMessage(message)
    }
}
