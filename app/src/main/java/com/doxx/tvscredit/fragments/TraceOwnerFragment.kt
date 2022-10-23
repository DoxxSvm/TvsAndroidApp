package com.doxx.tvscredit.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.doxx.tvscredit.R
import kotlinx.android.synthetic.main.fragment_trace_owner.*


class TraceOwnerFragment : Fragment(R.layout.fragment_trace_owner) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        trace_using_mNumber.setOnClickListener {
            findNavController().navigate(R.id.action_traceOwnerFragment_to_mobileNumbersFragment)
        }
    }
}