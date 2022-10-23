package com.doxx.tvscredit.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.doxx.tvscredit.R
import com.doxx.tvscredit.adapters.MobileNumberAdapter
import kotlinx.android.synthetic.main.fragment_mobile_numbers.*


class MobileNumbersFragment : Fragment(R.layout.fragment_mobile_numbers) {
    lateinit var mobileNumberAdapter:MobileNumberAdapter
    var list= arrayListOf<String>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        for(i in 0..10){
            list.add("Mobile No $i")
        }
        mobileNumberAdapter= MobileNumberAdapter(::onClick,list)
        mobile_rv.apply {
            layoutManager=LinearLayoutManager(requireContext())
            adapter=mobileNumberAdapter
        }
    }
    private fun onClick(mobileNumber:String){
        mobileNumberAdapter.notifyDataSetChanged()
        Toast.makeText(requireContext(), mobileNumber, Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_mobileNumbersFragment_to_resultsActivity2)
    }
}