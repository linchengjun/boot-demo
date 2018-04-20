package com.ndf.demo.domain;

import java.io.Serializable;

/**
 * Created by lin on 4/20/18.
 */
public class City implements Serializable{

    private static final long serialVersionUID = 1L;

    private int id;

    private int provinceId;

    private String cityName;

    private String description;

    public City() {
    }

    public City(int provinceId, String cityName, String description) {
        this.provinceId = provinceId;
        this.cityName = cityName;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", provinceId=" + provinceId +
                ", cityName='" + cityName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
