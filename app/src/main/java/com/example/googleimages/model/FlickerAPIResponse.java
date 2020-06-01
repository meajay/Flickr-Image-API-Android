package com.example.googleimages.model;

import com.google.gson.annotations.SerializedName;

public class FlickerAPIResponse {

    @SerializedName("photos")
    private PhotoResponse photos;

    public PhotoResponse getPhotos() {
        return photos;
    }

    public void setPhotos(PhotoResponse photos) {
        this.photos = photos;
    }
}
