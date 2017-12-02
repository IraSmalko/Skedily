package com.skedily.screens.map

import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.skedily.R
import com.skedily.base.BaseBoundVmActivity
import com.skedily.databinding.ActivityMapBinding
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_map.*


/**
 * Created by smalk on 12/2/2017.
 */
class MapActivity : BaseBoundVmActivity<ActivityMapBinding, MapViewModel>(
        R.layout.activity_map, MapViewModel::class), MapInteractor {

    private var googleMap: GoogleMap? = null
    private var googleApiClient: GoogleApiClient? = null

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mapView.onCreate(savedInstanceState)
        RxPermissions(this)
                .request(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe({ granted ->
                    if (granted) {
                        mapView.getMapAsync({ map ->
                            googleMap = map
                            if (!googleMap?.isMyLocationEnabled!!) {
                                googleMap?.isMyLocationEnabled = true
                                buildGoogleApiClient()
                            }
                        })
                    }
                })
    }

    @Synchronized private fun buildGoogleApiClient() {
        googleApiClient = GoogleApiClient.Builder(this)
                .addConnectionCallbacks(object : GoogleApiClient.ConnectionCallbacks {
                    override fun onConnectionSuspended(p0: Int) {
                    }

                    @SuppressLint("MissingPermission")
                    override fun onConnected(p0: Bundle?) {
                        val locationRequest = LocationRequest()
                        locationRequest.interval = 1000
                        locationRequest.fastestInterval = 1000
                        locationRequest.priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
                        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, { location -> addMarker(location) })
                    }
                })
                .addOnConnectionFailedListener({})
                .addApi(LocationServices.API)
                .build()
        googleApiClient?.connect()
    }

    @SuppressLint("MissingPermission")
    fun addMarker(location: Location) {
        val cameraUpdate = CameraUpdateFactory.newLatLngZoom(LatLng(location.latitude, location.longitude), 12F)
        googleMap?.apply {
            addMarker(MarkerOptions().position(LatLng(location.latitude, location.longitude)))
            uiSettings.isMyLocationButtonEnabled = false
            isMyLocationEnabled = true
            uiSettings.isZoomControlsEnabled = true
            animateCamera(cameraUpdate)
        }
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onDestroy() {
        mapView.onDestroy()
        super.onDestroy()
    }

    override fun onStart() {
        super.onStart()

        mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }
}