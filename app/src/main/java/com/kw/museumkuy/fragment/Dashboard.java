package com.kw.museumkuy.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.kw.museumkuy.R;
import com.kw.museumkuy.activity.DetailActivity;
import com.kw.museumkuy.adapter.MuseumAdapter;
import com.kw.museumkuy.helper.Constant;
import com.kw.museumkuy.model.MuseumModel;
import com.kw.museumkuy.model.WisModel;
import com.kw.museumkuy.rest.ApiClient;
import com.kw.museumkuy.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Dashboard extends Fragment {

    ListView listView;


    public Dashboard() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        listView = view.findViewById(R.id.lv_museum);
        getData();
        return view;
    }

    private void getData() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<MuseumModel>> call = apiInterface.getDataMuseum();
        call.enqueue(new Callback<List<MuseumModel>>() {
            @Override
            public void onResponse(Call<List<MuseumModel>> call, Response<List<MuseumModel>> response) {
                final List<MuseumModel> list = response.body();
                MuseumAdapter adapter = new MuseumAdapter(getActivity(), list);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent intent = new Intent(getActivity(), DetailActivity.class);
                        MuseumModel museumModel = new MuseumModel();
                        if (list != null) {
                            museumModel.setNama(list.get(position).getNama());
                            museumModel.setWebsite(list.get(position).getWebsite());
                            museumModel.setWilayah(list.get(position).getWilayah());
                            museumModel.setKecamatan(list.get(position).getKecamatan());
                            museumModel.setAlamat(list.get(position).getAlamat());
                            museumModel.setDeskripsi(list.get(position).getDeskripsi());
                            museumModel.setLongitude(list.get(position).getLongitude());
                            museumModel.setLatitude(list.get(position).getLatitude());
                            museumModel.setImageUrl(Constant.imgMuseum1[position]);
                            intent.putExtra(DetailActivity.KEY_ITEM, museumModel);
                            startActivity(intent);
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<List<MuseumModel>> call, Throwable t) {

            }
        });
    }

    private void getDataWisatawan(){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<WisModel>> call = apiInterface.getDataWisatawan();
        call.enqueue(new Callback<List<WisModel>>() {
            @Override
            public void onResponse(Call<List<WisModel>> call, Response<List<WisModel>> response) {
                List<WisModel> list = response.body();
            }

            @Override
            public void onFailure(Call<List<WisModel>> call, Throwable t) {

            }
        });
    }

}
