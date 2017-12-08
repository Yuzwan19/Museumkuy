package com.kw.museumkuy.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.kw.museumkuy.R;
import com.kw.museumkuy.adapter.AcaraAdapter;
import com.kw.museumkuy.model.AcaraModel;
import com.kw.museumkuy.rest.ApiClient;
import com.kw.museumkuy.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Notification extends Fragment {

    ListView listView;


    public Notification() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notifiacation, container, false);
        listView = view.findViewById(R.id.listView);
        getData();
        return view;
    }

    private void getData(){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<AcaraModel>> call = apiInterface.getAcara();
        call.enqueue(new Callback<List<AcaraModel>>() {
            @Override
            public void onResponse(Call<List<AcaraModel>> call, Response<List<AcaraModel>> response) {
                List<AcaraModel> list = response.body();
                AcaraAdapter acaraAdapter = new AcaraAdapter(getActivity(),list);
                listView.setAdapter(acaraAdapter);
            }

            @Override
            public void onFailure(Call<List<AcaraModel>> call, Throwable t) {

            }
        });
    }

}
