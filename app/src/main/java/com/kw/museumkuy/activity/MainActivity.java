package com.kw.museumkuy.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.kw.museumkuy.R;
import com.kw.museumkuy.adapter.MuseumAdapter;
import com.kw.museumkuy.model.MuseumModel;
import com.kw.museumkuy.rest.ApiClient;
import com.kw.museumkuy.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.lv_museum);
        getData();
    }

    private void getData(){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<MuseumModel>> call = apiInterface.getDataMuseum();
        call.enqueue(new Callback<List<MuseumModel>>() {
            @Override
            public void onResponse(Call<List<MuseumModel>> call, Response<List<MuseumModel>> response) {
                List<MuseumModel> list = response.body();
                MuseumAdapter adapter = new MuseumAdapter(MainActivity.this, list);
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<MuseumModel>> call, Throwable t) {

            }
        });
    }
}
