package com.devandfun.mytravel.login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.room.Room
import com.devandfun.mytravel.base.entities.Profile
import com.devandfun.mytravel.base.navigation.BaseNavigationFragment
import com.devandfun.mytravel.base.navigation.NavigationButton
import com.devandfun.mytravel.login.R
import com.devandfun.mytravel.login.create_account.CreateProfileFeature
import com.devandfun.mytravel.login.data.RoomProfileRepositoryImpl
import com.devandfun.mytravel.login.data.database.ProfileDatabase
import com.devandfun.mytravel.login.model.GetProfileUseCaseImpl
import com.devandfun.mytravel.login.ui.vm.LoginState
import com.devandfun.mytravel.login.ui.vm.LoginViewModel
import com.devandfun.mytravel.login.ui.vm.LoginViewModelFactory
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.synergyworldwide.mobile.ribs.logged_in.menu.view.list.EdgeDecorator
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*

class LoginFragment : BaseNavigationFragment<LoginDependencies>() {

    private lateinit var getProfilesUseCase: GetProfileUseCaseImpl
    private lateinit var mViewModel: LoginViewModel
    private lateinit var adapter: ProfileAdapter
    override val dependencies = LoginDependencies()
    private val createProfileFeatureStarter by lazy {
        CreateProfileFeature.CreateProfileStarter(
            childFragmentManager,
            vHeaderContainer,
            dependencies
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val layoutManager = FlexboxLayoutManager(context)
        layoutManager.justifyContent = JustifyContent.SPACE_BETWEEN
        view.vProfilesList.layoutManager = layoutManager
        adapter = ProfileAdapter()
        view.vProfilesList.adapter = adapter
        view.vProfilesList.addItemDecoration(
            EdgeDecorator(
                context!!.resources.getDimensionPixelSize(
                    R.dimen.margin_large
                ), context!!.resources.getDimensionPixelSize(
                    R.dimen.margin_xlarge
                )
            )
        )
        return view
    }

    override fun getMenuContainer(): Int = R.id.vNavigationContainer

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDependencies()
        initObserving()
        mViewModel.getProfiles()
    }

    private fun initObserving() {
        mViewModel.profiles
            .observe(this,
                Observer<List<Profile>> {
                    adapter.setItems(it)
                })

        mViewModel.state.observe(this, Observer {
            when (it!!) {
                LoginState.ADDING_PROFILE -> {
                    createProfileFeatureStarter.start()
                    vWelcomeText.visibility = View.GONE
                }
                LoginState.DEFAULT -> {
                    vWelcomeText.visibility = View.VISIBLE
                    createProfileFeatureStarter.stop()
                }
            }
            showNavigationButtons(getNavigationButtonsByState(it))
        })
    }

    private fun getNavigationButtonsByState(state: LoginState): List<NavigationButton> {
        return if (context == null) {
            emptyList()
        } else
            when (state) {
                LoginState.ADDING_PROFILE -> {
                    listOf(
                        NavigationButton(
                            context!!.getColor(R.color.cancel),
                            R.drawable.ic_close
                        ) {
                            mViewModel.cancelProfileAdding()
                        }
                    )
                }
                LoginState.DEFAULT -> {
                    listOf(
                        NavigationButton(
                            context!!.getColor(R.color.colorAccent),
                            R.drawable.ic_person_add
                        ) {
                            mViewModel.addProfile()
                        }
                    )
                }
            }
    }

    private fun initDependencies() {
        val profileDatabase =
            Room.databaseBuilder(context!!, ProfileDatabase::class.java, "profile-db")
                .fallbackToDestructiveMigration()
                .build()
        getProfilesUseCase =
            GetProfileUseCaseImpl(dependencies.provideProfileRepository(profileDatabase.profileDao()))

        val viewModelFactory = LoginViewModelFactory()
        viewModelFactory.getProfileUseCase = getProfilesUseCase
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
    }
}