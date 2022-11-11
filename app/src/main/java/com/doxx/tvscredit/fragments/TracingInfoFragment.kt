package com.doxx.tvscredit.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.doxx.tvscredit.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_tracing_info.*

class TracingInfoFragment : Fragment(R.layout.fragment_tracing_info) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startAnimation()
        setUpClickListener()
    }

    private fun setUpClickListener() {
        proceed.setOnClickListener {
            if(!trace_owner_et.text.isNullOrEmpty() && !trace_vehicle_et.text.isNullOrEmpty()){
                Snackbar.make(requireView(),"Please enter only one of them",Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(trace_owner_et.text.isNullOrEmpty() && trace_vehicle_et.text.isNullOrEmpty()){
                Snackbar.make(requireView(),"Please enter one value",Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (trace_owner_et.text.isNullOrEmpty()) {
                val directions =
                    TracingInfoFragmentDirections.actionTracingInfoFragmentToTraceVehicleFragment(
                        trace_vehicle_et.text.toString()
                    )

                findNavController().navigate(directions)
                return@setOnClickListener
            }
            if (trace_vehicle_et.text.isNullOrEmpty()) {
                val directions =
                    TracingInfoFragmentDirections.actionTracingInfoFragmentToTraceOwnerFragment(
                        trace_owner_et.text.toString()
                    )

                findNavController().navigate(directions)
                return@setOnClickListener
            }

        }
    }

    private fun startAnimation() {
        val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.upwards)
        proceed.animation = anim
    }
}