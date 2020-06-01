package com.example.googleimages.model;

import com.example.googleimages.Constants;
import com.google.gson.annotations.SerializedName;

public class PhotoDetail {
    @SerializedName("id")
    private String id;
    @SerializedName("farm")
    private int farm;
    @SerializedName("secret")
    private String secret;
    @SerializedName("server")
    private String server;
    @SerializedName("title")
    private String title;

    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getFarm() {
        return farm;
    }

    public void setFarm(int farm) {
        this.farm = farm;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getUrl() {
            return Constants.IMAGE_URL.replace(Constants.FARM_ID,String.valueOf(getFarm()))
                    .replace(Constants.ID,getId())
                    .replace(Constants.SECRET,getSecret())
                    .replace(Constants.SERVER_ID,getServer());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
