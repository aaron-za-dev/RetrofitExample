package com.zadev.aaron.retrofitexample;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pokemon {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("name")
    @Expose
    private String name;

    private int IDPokemon;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIDPokemon() {
        String [] urlDiv = url.split("/");
        return Integer.valueOf(urlDiv[urlDiv.length-1]);
    }

    public void setIDPokemon(int IDPokemon) {
        this.IDPokemon = IDPokemon;
    }
}