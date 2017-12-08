package com.kw.museumkuy.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kw.museumkuy.R;
import com.kw.museumkuy.model.HalteModel;
import com.kw.museumkuy.model.MuseumModel;
import com.kw.museumkuy.model.market.Datum;
import com.kw.museumkuy.model.market.MarketModel;
import com.kw.museumkuy.model.place.TempatModel;
import com.kw.museumkuy.model.spbu.SpbuModel;
import com.kw.museumkuy.rest.ApiClient;
import com.kw.museumkuy.rest.ApiInterface;
import com.kw.museumkuy.rest.DevClient;
import com.kw.museumkuy.rest.DevInterface;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final String TAG = DetailActivity.class.getSimpleName();
    public static final String KEY_ITEM = "item";
    private ArrayList<HalteModel> filterList = new ArrayList<>();
    MuseumModel museumModel;
    MapView mMapView;
    private GoogleMap mMap;
    TextView txtDesc, txtAlamat, txtWebsite, txtHalte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mMapView = (MapView) findViewById(R.id.mapview);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(this.getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(this);

        txtDesc = findViewById(R.id.txtDesc);
        txtAlamat = findViewById(R.id.txtAlamat);
        txtWebsite = findViewById(R.id.txtWebsite);
        txtHalte = findViewById(R.id.halte);

        museumModel = getIntent().getParcelableExtra(KEY_ITEM);

        getSupportActionBar().setTitle(museumModel.getNama());
        txtDesc.setText(museumModel.getDeskripsi());
        txtWebsite.setText(museumModel.getWebsite());
        txtAlamat.setText(String.format("%s, %s\n%s", museumModel.getAlamat(),
                museumModel.getKecamatan(), museumModel.getWilayah()));

        getDataHalte();
        getDataMarket();
        getDataPlace();
        getDataSPBU();

    }

    private void getDataHalte() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<HalteModel>> call = apiInterface.getDataHalte();
        call.enqueue(new Callback<List<HalteModel>>() {
            @Override
            public void onResponse(Call<List<HalteModel>> call, Response<List<HalteModel>> response) {
                List<HalteModel> list = response.body();
                filter("a", list);
            }

            @Override
            public void onFailure(Call<List<HalteModel>> call, Throwable t) {
                Log.e(TAG, "onFailure: Halte", t);
            }
        });
    }

    public void filter(String charText, List<HalteModel> listItem) {
        charText = charText.toLowerCase(Locale.getDefault());
        listItem.clear();
        if (charText.length() == 0) {
            listItem.addAll(filterList);
        } else {
            for (HalteModel fpList : filterList) {
                if (fpList.getLokasiHalte().toLowerCase(Locale.getDefault()).contains(charText)) {
                    listItem.add(fpList);
                    List<String> stringList = new ArrayList<>();
                    for (int i = 0; i<listItem.size(); i++){
                        stringList.add(listItem.get(i).getNamaHalte());
                    }

                    txtHalte.setText(stringList.toString());
                }
            }
        }

    }

    public void getDataMarket() {
        DevInterface devInterface = DevClient.getClientDev().create(DevInterface.class);
        Call<MarketModel> call = devInterface.getMarket();
        call.enqueue(new Callback<MarketModel>() {
            @Override
            public void onResponse(Call<MarketModel> call, Response<MarketModel> response) {
                List<Datum> list = response.body().getData();
                for (int i = 0; i < list.size(); i++) {
                    LatLng lng = new LatLng(list.get(i).getLocation().getLatitude(),
                            list.get(i).getLocation().getLongitude());
                    mMap.addMarker(new MarkerOptions()
                            .position(lng)
                            .title(list.get(i).getOutlet())
                            .zIndex(i)
                            .snippet(list.get(i).getAlamat())
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
//                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lng, 14));
                }
            }

            @Override
            public void onFailure(Call<MarketModel> call, Throwable t) {
                Log.e(TAG, "onFailure: Market", t);
            }
        });
    }

    public void getDataPlace(){
        DevInterface devInterface = DevClient.getClientDev().create(DevInterface.class);
        Call<TempatModel> call = devInterface.getTempat();
        call.enqueue(new Callback<TempatModel>() {
            @Override
            public void onResponse(Call<TempatModel> call, Response<TempatModel> response) {
                List<com.kw.museumkuy.model.place.Datum> list = response.body().getData();
                for (int i = 0; i < list.size(); i++) {
                    LatLng lng = new LatLng(list.get(i).getLocation().getLatitude(),
                            list.get(i).getLocation().getLongitude());
                    mMap.addMarker(new MarkerOptions()
                            .position(lng)
                            .title(list.get(i).getNama())
                            .zIndex(i)
                            .snippet(list.get(i).getAlamat())
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
//                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lng, 14));
                }
            }

            @Override
            public void onFailure(Call<TempatModel> call, Throwable t) {
                Log.e(TAG, "onFailure: Place", t);
            }
        });
    }

    private void getDataSPBU(){
        DevInterface devInterface = DevClient.getClientDev().create(DevInterface.class);
        Call<SpbuModel> call = devInterface.getSpbu();
        call.enqueue(new Callback<SpbuModel>() {
            @Override
            public void onResponse(Call<SpbuModel> call, Response<SpbuModel> response) {
                List<com.kw.museumkuy.model.spbu.Datum> list = response.body().getData();
                for (int i = 0; i < list.size(); i++) {
                    LatLng lng = new LatLng(list.get(i).getLocation().getLatitude(),
                            list.get(i).getLocation().getLongitude());
                    mMap.addMarker(new MarkerOptions()
                            .position(lng)
                            .title(list.get(i).getJenis())
                            .zIndex(i)
                            .snippet(list.get(i).getLokasi())
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
//                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lng, 14));
                }
            }

            @Override
            public void onFailure(Call<SpbuModel> call, Throwable t) {
                Log.e(TAG, "onFailure: spbu", t);

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng lng = new LatLng(Double.parseDouble(museumModel.getLatitude()),
                Double.parseDouble(museumModel.getLongitude()));
        mMap.addMarker(new MarkerOptions()
                .position(lng)
                .title(museumModel.getNama())
                .snippet(museumModel.getAlamat())
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lng, 15));

    }
}
