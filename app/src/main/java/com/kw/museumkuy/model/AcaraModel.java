
package com.kw.museumkuy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AcaraModel {

    @SerializedName("tanggal")
    @Expose
    private String tanggal;
    @SerializedName("nama_acara")
    @Expose
    private String namaAcara;
    @SerializedName("jumlah_pengunjung")
    @Expose
    private String jumlahPengunjung;
    @SerializedName("fasilitas_wisata")
    @Expose
    private String fasilitasWisata;
    @SerializedName("id")
    @Expose
    private Integer id;

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getNamaAcara() {
        return namaAcara;
    }

    public void setNamaAcara(String namaAcara) {
        this.namaAcara = namaAcara;
    }

    public String getJumlahPengunjung() {
        return jumlahPengunjung;
    }

    public void setJumlahPengunjung(String jumlahPengunjung) {
        this.jumlahPengunjung = jumlahPengunjung;
    }

    public String getFasilitasWisata() {
        return fasilitasWisata;
    }

    public void setFasilitasWisata(String fasilitasWisata) {
        this.fasilitasWisata = fasilitasWisata;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
