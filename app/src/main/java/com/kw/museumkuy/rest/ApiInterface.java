package com.kw.museumkuy.rest;

import com.kw.museumkuy.model.AcaraModel;
import com.kw.museumkuy.model.HalteModel;
import com.kw.museumkuy.model.MuseumModel;
import com.kw.museumkuy.model.TerminalModel;
import com.kw.museumkuy.model.WisModel;
import com.kw.museumkuy.model.market.MarketModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Izcax on 12/6/17.
 */

public interface ApiInterface {
    @GET("json?url=http://data.jakarta.go.id/dataset/8966bfe8-77f5-4bcb-a330-e686af7df5d2/resource/" +
            "3048815a-b4ba-4f19-b3e5-12c77b6f6b91/download/Museum-dan-Landmark-DKI-Jakarta.csv")
    Call<List<MuseumModel>> getDataMuseum();

    @GET("json?url=http://data.jakarta.go.id/dataset/f252f9c8-1b1f-4df6-a285-2fcb405f7fe2/resource/" +
            "ba125310-8499-4d8f-a8f2-7680663888f2/download/Data-Terminal-DKI-Jakarta-Tahun-2015.csv")
    Call<List<TerminalModel>> getDataTerminal();

    @GET("json?url=http://data.jakarta.go.id/dataset/70608c33-59e5-4967-ae80-4a070a246359/resource/" +
            "ee198ef7-d260-4d3d-a7a4-dd1460c5b738/download/" +
            "processed-data-lokasi-busway-jakarta-beserta-keterangan-per-februari-2013.csv")
    Call<List<HalteModel>> getDataHalte();

    @GET("json?url=http://data.jakarta.go.id/dataset/447edaa5-d0fa-409f-af7a-f45408b0bd3f/resource/" +
            "79cde944-7035-4a18-bc2e-87a95ec601c7/download/Data-Kunjungan-Museum-Daya-Tarik-Wisata-" +
            "DKI-Jakarta-Tahun-2014-Januari-Desember.csv")
    Call<List<WisModel>> getDataWisatawan();

    @GET("json?url=http://data.jakarta.go.id/dataset/8fc307e9-68c2-4dd4-9e41-f4ca8523d3a5/resource/" +
            "78bb0358-d9dc-4a1e-8d89-2fe8eb1b5d8e/download/Data-Acara-Wisata-dan-pengunjung--Balai-Kota-Tahun-2016-Editedd.csv")
    Call<List<AcaraModel>> getAcara();
}
