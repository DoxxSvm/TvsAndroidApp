package com.doxx.tvscredit.fragments

import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.doxx.tvscredit.R
import com.doxx.tvscredit.models.LatLongValue
import com.doxx.tvscredit.utils.NetworkResult
import com.doxx.tvscredit.viewmodel.BaseViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_result.*
import java.util.*
import kotlin.properties.Delegates


@AndroidEntryPoint
class ResultFragment : Fragment(R.layout.fragment_result), OnMapReadyCallback{
    private val viewModel by viewModels<BaseViewModel> ()
    private var latitude by Delegates.notNull<Double>()
    private var longitude by Delegates.notNull<Double>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUtil()
        setUpObservers()
        setUpClickListeners()

    }

    private fun initUtil() {
        viewModel.getLatLong()
        if(findNavController().previousBackStackEntry?.destination?.id == R.id.traceVehicleFragment){
            "Last Location".also { closest_tv.text = it }
        }
    }

    private fun setUpClickListeners() {
        view_on_gMap.setOnClickListener {
            val geoUri =
                "http://maps.google.com/maps?q=loc:$latitude,$longitude"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(geoUri))
            startActivity(intent)
        }
    }

    private fun setUpObservers() {
        viewModel.getLatLongResponseLD.observe(viewLifecycleOwner) {
            showViews()
            when (it) {
                is NetworkResult.Success -> {
                    setUpMap(it.data!!)
                }
                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {
                    hideViews()
                }
            }
        }
    }


    private fun hideViews() {
        progressBar.visibility = View.VISIBLE
        result_tv.visibility = View.GONE
        result_iv.visibility = View.GONE
        closest_tv.visibility = View.GONE
        map_card.visibility = View.GONE
        map_card.visibility = View.GONE
        view_on_gMap.visibility = View.GONE
        address_tv.visibility = View.GONE
    }

    private fun setUpMap(data: LatLongValue) {
        latitude = data.latitude.toDouble()
        longitude = data.longitude.toDouble()

        address_tv.text = getAddress()
        val mapFragment =childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    private fun showViews() {
        progressBar.visibility = View.GONE
        result_tv.visibility = View.VISIBLE
        result_iv.visibility = View.VISIBLE
        closest_tv.visibility = View.VISIBLE
        map_card.visibility = View.VISIBLE
        map_card.visibility = View.VISIBLE
        view_on_gMap.visibility = View.VISIBLE
        address_tv.visibility = View.VISIBLE

    }

    override fun onMapReady(googleMap: GoogleMap) {
        val loc = LatLng(latitude, longitude)
        val markerOptions = MarkerOptions().position(loc)
        googleMap.addMarker(markerOptions)
        val cameraUpdate = CameraUpdateFactory.newLatLngZoom(loc, 12f)
        googleMap.animateCamera(cameraUpdate)
    }

    private fun getAddress(): String {
        val geocoder = Geocoder(requireContext(), Locale.getDefault())

        val addresses: List<Address> = geocoder.getFromLocation(
            latitude,
            longitude,10
        )
        return addresses[0].getAddressLine(0)
    }
}