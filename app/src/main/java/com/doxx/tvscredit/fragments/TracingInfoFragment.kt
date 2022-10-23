package com.doxx.tvscredit.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.doxx.tvscredit.R
import kotlinx.android.synthetic.main.fragment_tracing_info.*

class TracingInfoFragment : Fragment(R.layout.fragment_tracing_info) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        proceed.setOnClickListener {
            findNavController().navigate(R.id.action_tracingInfoFragment_to_traceOwnerFragment)
        }
    }
}