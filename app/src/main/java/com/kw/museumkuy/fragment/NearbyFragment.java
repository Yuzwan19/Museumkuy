package com.kw.museumkuy.fragment;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kw.museumkuy.R;
import com.kw.museumkuy.activity.DetailActivity;
import com.kw.museumkuy.helper.Constant;
import com.kw.museumkuy.model.MuseumModel;
import com.kw.museumkuy.rest.ApiClient;
import com.kw.museumkuy.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NearbyFragment extends Fragment implements OnMapReadyCallback {
    private static final String TAG = NearbyFragment.class.getSimpleName();
    MapView mMapView;
    private GoogleMap mMap;


    public NearbyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

//        methodRequiresPermission();

        View view = inflater.inflate(R.layout.fragment_nearby, container, false);
        mMapView = (MapView) view.findViewById(R.id.mapview);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(this);

        getData();

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(false);
    }

    private void getData() {
//        mMap.clear();
        final ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.show();
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<MuseumModel>> call = apiInterface.getDataMuseum();
        call.enqueue(new Callback<List<MuseumModel>>() {
            @Override
            public void onResponse(Call<List<MuseumModel>> call, Response<List<MuseumModel>> response) {
                try {
                    final List<MuseumModel> list = response.body();
                    for (int i = 0; i < list.size(); i++) {
                        LatLng lng = new LatLng(Double.parseDouble(list.get(i).getLatitude()),
                                Double.parseDouble(list.get(i).getLongitude()));
                        mMap.addMarker(new MarkerOptions().position(lng)
                                .title(list.get(i).getNama())
                                .zIndex(i)
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.museum))
                                .snippet(list.get(i).getAlamat()));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lng, 14));
                    }

                    mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                        @Override
                        public void onInfoWindowClick(Marker marker) {
                            Intent intent = new Intent(getActivity(),DetailActivity.class);
                            MuseumModel museumModel = new MuseumModel();
                            museumModel.setNama(list.get((int) marker.getZIndex()).getNama());
                            museumModel.setWebsite(list.get((int) marker.getZIndex()).getWebsite());
                            museumModel.setWilayah(list.get((int) marker.getZIndex()).getWilayah());
                            museumModel.setKecamatan(list.get((int) marker.getZIndex()).getKecamatan());
                            museumModel.setAlamat(list.get((int) marker.getZIndex()).getAlamat());
                            museumModel.setDeskripsi(list.get((int) marker.getZIndex()).getDeskripsi());
                            museumModel.setLatitude(list.get((int) marker.getZIndex()).getLatitude());
                            museumModel.setLongitude(list.get((int) marker.getZIndex()).getLongitude());
                            museumModel.setImageUrl(Constant.imgMuseum1[(int) marker.getZIndex()]);
                            intent.putExtra(DetailActivity.KEY_ITEM, museumModel);
                            startActivity(intent);
                        }
                    });
                    dialog.dismiss();

                } catch (NullPointerException e) {
                    Log.e(TAG, "onResponse: ", e);
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<MuseumModel>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
                dialog.dismiss();
            }
        });
    }
}
