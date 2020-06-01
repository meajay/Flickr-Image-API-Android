package com.example.googleimages.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.googleimages.Constants;
import com.example.googleimages.R;
import com.example.googleimages.model.FlickerAPIResponse;
import com.example.googleimages.network.ApiInterface;
import com.example.googleimages.network.RetrofitClient;
import com.example.googleimages.view.SearchViewContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPresenter implements SearchPresenterContract {

    private static SearchViewContract searchViewContract;
    private static Context context;

    public static SearchPresenter getInstance(SearchViewContract contract, Context ctx){
        searchViewContract = contract;
        context = ctx;
        return new SearchPresenter();
    }

    public SearchPresenter() {
    }

    @Override
    public void searchQueryData(String text,int pageNumber) {
        RetrofitClient.getRetrofit(context).create(ApiInterface.class).getFlickerPhotos(
                Constants.SEARCH_METHOD_NAME, text,Constants.RESPONSE_FORMAT, Constants.API_KEY,
                pageNumber,Constants.PER_PAGE_ITEM,1).enqueue(new Callback<FlickerAPIResponse>() {
            @Override
            public void onResponse(Call<FlickerAPIResponse> call,
                                   Response<FlickerAPIResponse> response) {
                searchViewContract.updateProgressbar(false);
                if(response.code() == 200){
                    FlickerAPIResponse responsex = response.body();
                    if(responsex!=null && responsex.getPhotos()!=null) {
                        if (responsex.getPhotos().getPhotoList().size() > 0) {
                            searchViewContract.showQueryData(responsex.getPhotos());
                        }
                        else{
                            Toast.makeText(context, R.string.no_items_found,Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<FlickerAPIResponse> call, Throwable t) {
                searchViewContract.updateProgressbar(false);
                searchViewContract.showError(t.getMessage());
            }
        });
    }
}
