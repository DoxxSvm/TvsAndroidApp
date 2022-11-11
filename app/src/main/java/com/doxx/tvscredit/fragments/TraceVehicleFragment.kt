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
import kotlinx.android.synthetic.main.fragment_trace_vehicle.*


class TraceVehicleFragment : Fragment(R.layout.fragment_trace_vehicle) {
    val args: TraceOwnerFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tracing_info.text = args.tracingInfo

        startAnimation()
        setUpClickListeners()

    }

    private fun setUpClickListeners() {
        trace_using_gps.setOnClickListener {
            findNavController().navigate(R.id.action_traceVehicleFragment_to_resultFragment2)
        }
        trace_using_fastag.setOnClickListener {
            findNavController().navigate(R.id.action_traceVehicleFragment_to_resultFragment2)
        }
        trace_using_challan.setOnClickListener {
            findNavController().navigate(R.id.action_traceVehicleFragment_to_resultFragment2)
        }
    }

    private fun startAnimation() {
        val anim = AnimationUtils.loadAnimation(requireContext(),R.anim.upwards)
        trace_using_gps.animation = anim
        trace_using_fastag.animation = anim
        trace_using_challan.animation = anim
        map_iv.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.pop)

    }
}