package com.example.googleimages.view;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.googleimages.R;
import com.example.googleimages.model.PhotoDetail;
import com.example.googleimages.model.PhotoResponse;
import com.example.googleimages.presenter.SearchPresenter;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements SearchViewContract {


    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private ImageItemAdapter imageItemAdapter;
    private SearchPresenter searchPresenter;


    private List<PhotoDetail> itemList = new ArrayList<>();
    private String DEFAULT_SEARCH = "Google";
    private String query = "";
    private int pageFetched = 1;
    private int totalPages = Integer.MAX_VALUE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        setUpPresenter();
    }

    private void setUpPresenter() {
        searchPresenter = SearchPresenter.getInstance(this, this);
        searchQueryData(DEFAULT_SEARCH, pageFetched);
    }

    private void setUpRecyclerView(List<PhotoDetail> imageItemList) {
        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        imageItemAdapter = new ImageItemAdapter(imageItemList);
        recyclerView.setAdapter(imageItemAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)) {
                    pageFetched++;
                    if (pageFetched <= totalPages) {
                        searchQueryData(query, pageFetched);
                    } else {
                        Toast.makeText(SearchActivity.this,
                                getString(R.string.no_more_items), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private void initUI() {
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progress_bar);
        setUpRecyclerView(itemList);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        pageFetched = 0;
        query="";
        totalPages = Integer.MAX_VALUE;
        itemList.clear();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            searchQueryData(intent.getStringExtra(SearchManager.QUERY), pageFetched);
        }
    }

    private void searchQueryData(String stringExtra, int pageFetch) {
        query = stringExtra;
        searchPresenter.searchQueryData(stringExtra, pageFetch);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        setUpSearch(menu);
        return true;
    }

    private void setUpSearch(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) SearchActivity.this.getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }
    }


    @Override
    public void showQueryData(PhotoResponse photoResponse) {
        pageFetched = photoResponse.getPageNumber();
        totalPages = photoResponse.getPages();
        itemList.addAll(photoResponse.getPhotoList());
        if (itemList.size()>10) {
            imageItemAdapter.notifyItemRangeChanged(imageItemAdapter.getItemCount() - 1,
                    photoResponse.getPhotoList().size());
        } else {
            imageItemAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showError(String message) {
        Toast.makeText(SearchActivity.this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void updateProgressbar(boolean show) {
        if (show) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
