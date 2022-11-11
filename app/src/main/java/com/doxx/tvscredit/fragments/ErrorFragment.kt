package com.doxx.tvscredit.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.doxx.tvscredit.R
import kotlinx.android.synthetic.main.fragment_error.*


class ErrorFragment : Fragment(R.layout.fragment_error) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        retry.setOnClickListener {
            findNavController().navigate(R.id.action_errorFragment_to_traceOwnerFragment)
        }
    }
}