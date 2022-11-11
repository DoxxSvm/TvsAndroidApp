package com.doxx.tvscredit.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.doxx.tvscredit.R
import kotlinx.android.synthetic.main.fragment_trace_owner.*
import kotlinx.android.synthetic.main.fragment_trace_owner.map_iv
import kotlinx.android.synthetic.main.fragment_trace_owner.tracing_info


class TraceOwnerFragment : Fragment(R.layout.fragment_trace_owner) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tracing_info.text = getRandomName()
        startAnimation()
        setUpClickListeners()

    }

    private fun setUpClickListeners() {
        trace_using_mNumber.setOnClickListener {
            findNavController().navigate(R.id.action_traceOwnerFragment_to_mobileNumbersFragment)
        }
        trace_using_transaction.setOnClickListener{
            findNavController().navigate(R.id.action_traceOwnerFragment_to_resultFragment2)
        }
    }

    private fun getRandomName():String{
        val names = arrayListOf("Shyam Lal Mishra", "Vinod Tripathi", "Keshav Singh", "Ravi Prakash Verma",
            "Ankit Choudhary", "Sanjay Prasad", "Sushil Goswami")
        return names.random()
    }
    private fun startAnimation() {

        val anim = AnimationUtils.loadAnimation(requireContext(),R.anim.upwards)
        trace_using_mNumber.animation = anim
        trace_using_transaction.animation = anim
        map_iv.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.pop)

    }

}