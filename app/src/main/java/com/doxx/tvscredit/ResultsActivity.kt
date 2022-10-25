package com.doxx.tvscredit

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_results.*


class ResultsActivity : AppCompatActivity(),OnMapReadyCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)
        supportActionBar?.hide()
        window.statusBarColor=Color.BLACK


        val mapFragment =supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


        view_on_gMap.setOnClickListener {
            val geoUri =
                "http://maps.google.com/maps?q=loc:" + "27.1751" + ","+ "78.04"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(geoUri))
            startActivity(intent)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val sydney = LatLng(27.1751, 78.040001)
        val markerOptions = MarkerOptions().position(sydney).title("Taj mahal").snippet(
            "Best monuments"
        )
        googleMap.addMarker(markerOptions)
        val sydney2 = LatLng(26.1751, 77.040001)
        val markerOptions2 = MarkerOptions().position(sydney2).title("Taj mahal").snippet(
            "Best monuments"
        )
        googleMap.addMarker(markerOptions2)
        val cameraUpdate = CameraUpdateFactory.newLatLngZoom(sydney, 16f)
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        googleMap.animateCamera(cameraUpdate)
    }
}