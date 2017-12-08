
package com.kw.museumkuy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WisModel {

    @SerializedName("nama_destinasi")
    @Expose
    private String namaDestinasi;
    @SerializedName("wisnus")
    @Expose
    private String wisnus;
    @SerializedName("wisman")
    @Expose
    private String wisman;
    @SerializedName("id")
    @Expose
    private Integer id;

    public String getNamaDestinasi() {
        return namaDestinasi;
    }

    public void setNamaDestinasi(String namaDestinasi) {
        this.namaDestinasi = namaDestinasi;
    }

    public String getWisnus() {
        return wisnus;
    }

    public void setWisnus(String wisnus) {
        this.wisnus = wisnus;
    }

    public String getWisman() {
        return wisman;
    }

    public void setWisman(String wisman) {
        this.wisman = wisman;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
