package com.kw.museumkuy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Izcax on 12/7/17.
 */

public class HalteModel {
    @SerializedName("koridor")
    @Expose
    private String koridor;
    @SerializedName("rute_koridor")
    @Expose
    private String ruteKoridor;
    @SerializedName("nama_halte")
    @Expose
    private String namaHalte;
    @SerializedName("lokasi_halte")
    @Expose
    private String lokasiHalte;
    @SerializedName("dibangun_tahun")
    @Expose
    private String dibangunTahun;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
    @SerializedName("id")
    @Expose
    private Integer id;

    public String getKoridor() {
        return koridor;
    }

    public void setKoridor(String koridor) {
        this.koridor = koridor;
    }

    public String getRuteKoridor() {
        return ruteKoridor;
    }

    public void setRuteKoridor(String ruteKoridor) {
        this.ruteKoridor = ruteKoridor;
    }

    public String getNamaHalte() {
        return namaHalte;
    }

    public void setNamaHalte(String namaHalte) {
        this.namaHalte = namaHalte;
    }

    public String getLokasiHalte() {
        return lokasiHalte;
    }

    public void setLokasiHalte(String lokasiHalte) {
        this.lokasiHalte = lokasiHalte;
    }

    public String getDibangunTahun() {
        return dibangunTahun;
    }

    public void setDibangunTahun(String dibangunTahun) {
        this.dibangunTahun = dibangunTahun;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
