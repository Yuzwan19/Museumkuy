
package com.kw.museumkuy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TerminalModel {

    @SerializedName("nama_terminal")
    @Expose
    private String namaTerminal;
    @SerializedName("jenis")
    @Expose
    private String jenis;
    @SerializedName("tipe")
    @Expose
    private String tipe;
    @SerializedName("lokasi")
    @Expose
    private String lokasi;
    @SerializedName("luas_tanah_m2")
    @Expose
    private String luasTanahM2;
    @SerializedName("luas_bangunan_m2")
    @Expose
    private String luasBangunanM2;
    @SerializedName("tahun_peresmian")
    @Expose
    private String tahunPeresmian;
    @SerializedName("tahun_revitalisasi_terakhir")
    @Expose
    private String tahunRevitalisasiTerakhir;
    @SerializedName("kepala_terminal")
    @Expose
    private String kepalaTerminal;
    @SerializedName("kontak")
    @Expose
    private String kontak;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("id")
    @Expose
    private Integer id;

    public String getNamaTerminal() {
        return namaTerminal;
    }

    public void setNamaTerminal(String namaTerminal) {
        this.namaTerminal = namaTerminal;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getLuasTanahM2() {
        return luasTanahM2;
    }

    public void setLuasTanahM2(String luasTanahM2) {
        this.luasTanahM2 = luasTanahM2;
    }

    public String getLuasBangunanM2() {
        return luasBangunanM2;
    }

    public void setLuasBangunanM2(String luasBangunanM2) {
        this.luasBangunanM2 = luasBangunanM2;
    }

    public String getTahunPeresmian() {
        return tahunPeresmian;
    }

    public void setTahunPeresmian(String tahunPeresmian) {
        this.tahunPeresmian = tahunPeresmian;
    }

    public String getTahunRevitalisasiTerakhir() {
        return tahunRevitalisasiTerakhir;
    }

    public void setTahunRevitalisasiTerakhir(String tahunRevitalisasiTerakhir) {
        this.tahunRevitalisasiTerakhir = tahunRevitalisasiTerakhir;
    }

    public String getKepalaTerminal() {
        return kepalaTerminal;
    }

    public void setKepalaTerminal(String kepalaTerminal) {
        this.kepalaTerminal = kepalaTerminal;
    }

    public String getKontak() {
        return kontak;
    }

    public void setKontak(String kontak) {
        this.kontak = kontak;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
