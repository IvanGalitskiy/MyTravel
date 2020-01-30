package com.devandfun.mytravel.login.create_account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.devandfun.mytravel.base.ui.BaseFragment
import com.devandfun.mytravel.login.R
import com.devandfun.mytravel.login.create_account.model.CreateAccountUseCaseImpl
import kotlinx.android.synthetic.main.fragment_create_user.*

class CreateProfileFragment : BaseFragment<CreateProfileDependencies>() {
    override val dependencies = CreateProfileDependencies()
    lateinit var parentDependencies: CreateProfileFeature.Dependencies
    private lateinit var mViewModel: CreateProfileViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDependencies()
        observeViewModel()
        initListeners()
    }

    private fun observeViewModel() {
        mViewModel.name.observe(this, Observer {
            vProfileNameView.name = it
        })

        mViewModel.accountCreation.observe(this, Observer {  })
    }

    private fun initListeners(){
        vProfileName.afterTextChanged {
            mViewModel.setProfileName(it)
        }
    }

    private fun initDependencies() {
        val createAccountUseCase =
            CreateAccountUseCaseImpl(parentDependencies.profileRepository())

        val viewModelFactory = CreateProfileViewModelFactory()
        viewModelFactory.createAccountUseCase = createAccountUseCase
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(CreateProfileViewModel::class.java)
    }
}