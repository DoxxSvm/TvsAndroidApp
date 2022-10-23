package com.doxx.tvscredit.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import javax.annotation.meta.When
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
    private val viewModel by viewModels<BaseViewModel>()
    @Inject
    lateinit var tokenManager: TokenManager
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        proceed.setOnClickListener {
            val employeeID = employeeID_et.text.toString()
            val password = password_et.text.toString()
            viewModel.signIn(EmployeeRequest(employeeID,password))
        }
        viewModel.employeeResponseLD.observe(viewLifecycleOwner) {
            loginProgressBar.visibility = View.GONE
            btn_img.visibility = View.VISIBLE
            when(it){
                is NetworkResult.Success ->{
                    findNavController().navigate(R.id.action_loginFragment_to_tracingInfoFragment)
                    tokenManager.saveToken(it.data!!.token)
                }
                is NetworkResult.Error -> {
                    Snackbar.make(view,it.message.toString(),Snackbar.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {
                    btn_img.visibility = View.GONE
                    loginProgressBar.visibility = View.VISIBLE

                }
            }
        }
    }
}