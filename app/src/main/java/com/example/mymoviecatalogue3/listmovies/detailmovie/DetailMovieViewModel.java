package com.example.mymoviecatalogue3.listmovies.detailmovie;

import androidx.lifecycle.ViewModel;

import com.example.mymoviecatalogue3.listmovies.pojo.ResultsItem;

public class DetailMovieViewModel extends ViewModel {
    private ResultsItem resultsItem;

    public ResultsItem getResultsItem() {
        return resultsItem;
    }

    public void setResultsItem(ResultsItem resultsItem){
        this.resultsItem = resultsItem;
    }
}
