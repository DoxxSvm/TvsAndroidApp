package com.doxx.tvscredit.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.doxx.tvscredit.R
import com.doxx.tvscredit.models.EmployeeRequest
import com.doxx.tvscredit.utils.NetworkResult
import com.doxx.tvscredit.utils.TokenManager
import com.doxx.tvscredit.viewmodel.BaseViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.proceed
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
    private val viewModel by viewModels<BaseViewModel>()
    @Inject
    lateinit var tokenManager: TokenManager
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(tokenManager.getToken() != null){
            findNavController().navigate(R.id.action_loginFragment_to_tracingInfoFragment)
        }
        setUpObservers()
        setUpClickListeners()
        startAnimation()

    }

    private fun startAnimation() {
        val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.upwards)
        proceed.animation = anim
    }

    private fun setUpClickListeners() {
        proceed.setOnClickListener {
            if(employeeID_et.text.isNullOrEmpty() || password_et.text.isNullOrEmpty()){
                Snackbar.make(requireView(),"Username or Password can't be empty", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val employeeID = employeeID_et.text.toString()
            val password = password_et.text.toString()
            viewModel.signIn(EmployeeRequest(employeeID,password))
        }
    }

    private fun setUpObservers() {
        viewModel.employeeResponseLD.observe(viewLifecycleOwner) {
            loginProgressBar.visibility = View.GONE
            btn_img.visibility = View.VISIBLE
            when(it){
                is NetworkResult.Success ->{
                    findNavController().navigate(R.id.action_loginFragment_to_tracingInfoFragment)
                    tokenManager.saveToken(it.data!!.token)

                }
                is NetworkResult.Error -> {
                    Snackbar.make(requireView(),it.message.toString(),Snackbar.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {
                    btn_img.visibility = View.GONE
                    loginProgressBar.visibility = View.VISIBLE

                }
                else -> {}
            }
        }
    }
}