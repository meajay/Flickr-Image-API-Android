package com.example.googleimages.network;

import com.example.googleimages.Constants;
import com.example.googleimages.model.FlickerAPIResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET(Constants.END_POINT)
    Call<FlickerAPIResponse> getFlickerPhotos(@Query("method")String methodType,
                                              @Query("text") String serachQuery,
                                              @Query("format") String format,
                                              @Query("api_key")String key,
                                              @Query("page")int page,
                                              @Query("per_page") int perPage,
                                              @Query("nojsoncallback") int num);
}
