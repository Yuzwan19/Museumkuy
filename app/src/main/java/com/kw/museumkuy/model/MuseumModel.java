
package com.kw.museumkuy.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MuseumModel implements Parcelable {

    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("kecamatan")
    @Expose
    private String kecamatan;
    @SerializedName("wilayah")
    @Expose
    private String wilayah;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("latest_update")
    @Expose
    private String latestUpdate;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("id")
    @Expose
    private Integer id;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getWilayah() {
        return wilayah;
    }

    public void setWilayah(String wilayah) {
        this.wilayah = wilayah;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLatestUpdate() {
        return latestUpdate;
    }

    public void setLatestUpdate(String latestUpdate) {
        this.latestUpdate = latestUpdate;
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nama);
        dest.writeString(this.alamat);
        dest.writeString(this.kecamatan);
        dest.writeString(this.wilayah);
        dest.writeString(this.deskripsi);
        dest.writeString(this.website);
        dest.writeString(this.latestUpdate);
        dest.writeString(this.latitude);
        dest.writeString(this.longitude);
        dest.writeValue(this.id);
    }

    public MuseumModel() {
    }

    protected MuseumModel(Parcel in) {
        this.nama = in.readString();
        this.alamat = in.readString();
        this.kecamatan = in.readString();
        this.wilayah = in.readString();
        this.deskripsi = in.readString();
        this.website = in.readString();
        this.latestUpdate = in.readString();
        this.latitude = in.readString();
        this.longitude = in.readString();
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<MuseumModel> CREATOR = new Parcelable.Creator<MuseumModel>() {
        @Override
        public MuseumModel createFromParcel(Parcel source) {
            return new MuseumModel(source);
        }

        @Override
        public MuseumModel[] newArray(int size) {
            return new MuseumModel[size];
        }
    };
}
