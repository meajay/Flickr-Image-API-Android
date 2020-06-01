package com.example.googleimages.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PhotoResponse {
    @SerializedName("page")
    private int pageNumber;
    @SerializedName("pages")
    private int pages;
    @SerializedName("photo")
    private List<PhotoDetail> photoList = new ArrayList<>();

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<PhotoDetail> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<PhotoDetail> photoList) {
        this.photoList = photoList;
    }
}
