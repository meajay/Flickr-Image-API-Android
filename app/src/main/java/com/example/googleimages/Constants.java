package com.example.googleimages;

public class Constants {
    public static final String BASE_URL  = "https://api.flickr.com/services/";
    public static final String API_KEY = "062a6c0c49e4de1d78497d13a7dbb360";
    public static final String END_POINT  = "rest/";

    public static final String FARM_ID = "{farm-id}";
    public static final String SERVER_ID = "{server-id}";
    public static final String SECRET = "{secret}";
    public static final String ID = "{id}";
    public static final String IMAGE_URL = "https://farm"+FARM_ID+ ".staticflickr.com/"+SERVER_ID+"/"+ID+"_"+SECRET+".jpg";


    public static final String SEARCH_METHOD_NAME = "flickr.photos.search";
    public static final String RESPONSE_FORMAT = "json";
    public static final int PER_PAGE_ITEM = 10;
}
