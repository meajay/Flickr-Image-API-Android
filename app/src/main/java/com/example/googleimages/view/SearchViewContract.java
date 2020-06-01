package com.example.googleimages.view;

import com.example.googleimages.model.PhotoDetail;
import com.example.googleimages.model.PhotoResponse;

import java.util.List;

public interface SearchViewContract {
    void showQueryData(PhotoResponse photoResponse);
    void showError(String message);
    void updateProgressbar(boolean show);
}
