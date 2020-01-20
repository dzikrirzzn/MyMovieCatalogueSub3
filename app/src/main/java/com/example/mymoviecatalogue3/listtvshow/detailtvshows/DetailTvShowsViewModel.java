package com.example.mymoviecatalogue3.listtvshow.detailtvshows;

import androidx.lifecycle.ViewModel;

import com.example.mymoviecatalogue3.listtvshow.pojo.ResultsItem;

public class DetailTvShowsViewModel extends ViewModel {
    private ResultsItem resultsItem;

    public ResultsItem getResultsItem() {
        return resultsItem;
    }

    public void setResultsItem(ResultsItem resultsItem) {
        this.resultsItem = resultsItem;

    }
}
