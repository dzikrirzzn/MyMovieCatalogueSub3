package com.example.mymoviecatalogue3.listtvshow.detailtvshows;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.mymoviecatalogue3.R;
import com.example.mymoviecatalogue3.databinding.ActivityTvShowsBinding;
import com.example.mymoviecatalogue3.listtvshow.pojo.ResultsItem;

import java.util.Objects;

public class DetailTvShows extends AppCompatActivity {
    public static final  String SELECTED_TV_SHOWS = "selected_tv_shows";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DetailTvShowsViewModel viewModel = ViewModelProviders.of(this).get(DetailTvShowsViewModel.class);
        ActivityTvShowsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_tv_shows);
        ResultsItem movieModel = getIntent().getParcelableExtra(SELECTED_TV_SHOWS);
        viewModel.setResultsItem(movieModel);

        binding.setTitle(viewModel.getResultsItem().getName());
        binding.setOriginalLanguage(viewModel.getResultsItem().getOriginalLanguage());
        binding.setReleaseDate(viewModel.getResultsItem().getFirstAirDate());
        binding.setOverview(viewModel.getResultsItem().getOverview());
        binding.setVote(String.valueOf(viewModel.getResultsItem().getVoteCount()));

        Glide.with(this).load("https://image.tmdb.org/t/p/w185"+movieModel.getPosterPath()).into(binding.imgPoster);
        setTitle(viewModel.getResultsItem().getName());

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
