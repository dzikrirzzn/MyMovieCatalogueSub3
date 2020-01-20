package com.example.mymoviecatalogue3.listtvshow.pojo;

import androidx.annotation.NonNull;

import java.util.List;

import com.androidnetworking.error.ANError;
import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class ResponseTvShows {
    private ANError anError;

    public ResponseTvShows(ANError anError) {
        this.anError = anError;
    }

    public ResponseTvShows() {

    }

    public ANError getAnError() {
        return anError;
    }

    @SerializedName("page")
    private int page;

    @SerializedName("total_pages")
    private int totalpages;

    @SerializedName("results")
    private List<ResultsItem> results;

    @SerializedName("total_results")
    private int totalresults;


    public List<ResultsItem> getResult() {
        return results;
    }

    @NonNull
    @Override
    public String toString() {
        return
                "ResponseTvShows{" +
                        "page = '" + page + '\'' +
                        ",total_pages = '" + totalpages + '\'' +
                        ",results = '" + results + '\'' +
                        ",total_results '" + totalresults + '\'' +
                        "}";
    }

}