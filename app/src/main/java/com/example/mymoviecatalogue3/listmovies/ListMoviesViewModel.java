package com.example.mymoviecatalogue3.listmovies;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.mymoviecatalogue3.MovieCatalogue;
import com.example.mymoviecatalogue3.listmovies.pojo.ResponseMovies;

public class ListMoviesViewModel extends ViewModel {

    private MutableLiveData<ResponseMovies> responseMovies = new MutableLiveData<>();

    public MutableLiveData getMovies(){
        if (responseMovies==null){
            doRequestListMovie();
        }
        return responseMovies;
    }

    public void doRequestListMovie(){
        AndroidNetworking.get("https://api.themoviedb.org/3/discover/movie")
                .addQueryParameter("api_key", MovieCatalogue.MOVIE_DB_API_KEY)
                .addQueryParameter("language","en-US")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(ResponseMovies.class, new ParsedRequestListener<ResponseMovies>() {
                    @Override
                    public void onResponse(ResponseMovies response) {
                        responseMovies.postValue(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        responseMovies.setValue(new ResponseMovies(anError));

                    }
                });
    }
}
