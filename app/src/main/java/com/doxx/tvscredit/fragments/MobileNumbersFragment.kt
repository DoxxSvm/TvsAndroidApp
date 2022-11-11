package com.doxx.tvscredit.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.doxx.tvscredit.R
import com.doxx.tvscredit.adapters.MobileNumberAdapter
import com.doxx.tvscredit.models.MobileNumber
import com.doxx.tvscredit.utils.NetworkResult
import com.doxx.tvscredit.viewmodel.BaseViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_mobile_numbers.*

@AndroidEntryPoint
class MobileNumbersFragment : Fragment(R.layout.fragment_mobile_numbers) {

    private lateinit var mobileNumberAdapter:MobileNumberAdapter
    private val viewModel by viewModels<BaseViewModel> ()
    private var list= arrayListOf<String>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMobileNo()
        setUpObservers(view)

    }

    private fun setUpObservers(view: View) {
        viewModel.getMobileNoLD.observe(viewLifecycleOwner) {
            showViews()
            when (it) {
                is NetworkResult.Success -> {
                    setupRecyclerView(it.data!!)
                }
                is NetworkResult.Error -> {
                    Snackbar.make(view, "Something went wrong", Snackbar.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {
                    hideViews()
                }
            }
        }
    }

    private fun hideViews() {
        progressBar.visibility = View.VISIBLE
        list_tv.visibility = View.GONE
        mobile_rv.visibility = View.GONE
    }
    private fun showViews() {
        progressBar.visibility = View.GONE
        list_tv.visibility = View.VISIBLE
        mobile_rv.visibility = View.VISIBLE
    }
    private fun setupRecyclerView(data: MobileNumber) {
        list = data.mobileNumbers as ArrayList<String>
        mobileNumberAdapter= MobileNumberAdapter(::onClick,list)
        mobile_rv.apply {
            layoutManager=LinearLayoutManager(requireContext())
            adapter=mobileNumberAdapter
        }
    }
    private fun onClick(mobileNumber:String, position: Int){
        mobileNumberAdapter.notifyDataSetChanged()
        if(position == list.size -1){
            findNavController().navigate(R.id.action_mobileNumbersFragment_to_errorFragment)
        }
        else{
            findNavController().navigate(R.id.action_mobileNumbersFragment_to_resultFragment2)
        }
    }
}