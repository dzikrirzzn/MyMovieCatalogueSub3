package com.example.mymoviecatalogue3.listtvshow;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymoviecatalogue3.R;
import com.example.mymoviecatalogue3.listtvshow.detailtvshows.DetailTvShows;
import com.example.mymoviecatalogue3.listtvshow.pojo.ResponseTvShows;
import com.example.mymoviecatalogue3.listtvshow.pojo.ResultsItem;

public class ListTvShowFragment extends Fragment {

    private RecyclerView recyclerView;
    private AlertDialog alertDialog;
    private ProgressBar progressBar;

    private Observer<ResponseTvShows> getTvShows = new Observer<ResponseTvShows>() {
        @Override
        public void onChanged(ResponseTvShows responseTvShows) {
            if (responseTvShows!=null){
                if (responseTvShows.getAnError()==null){
                    ListTvShowsAdapter mAdapter = new ListTvShowsAdapter(responseTvShows.getResult());
                    mAdapter.SetOnItemClickListener(new ListTvShowsAdapter.OnItemClickListener(){
                        @Override
                        public void onItemClick(View view, ResultsItem model){
                            Intent goToDetailMovie = new Intent(view.getContext(), DetailTvShows.class);
                            goToDetailMovie.putExtra(DetailTvShows.SELECTED_TV_SHOWS,model);
                            startActivity(goToDetailMovie);
                        }
                    });
                    recyclerView.setAdapter(mAdapter);
                }else{
                    alertDialog.setMessage(responseTvShows.getAnError().getMessage());
                    alertDialog.show();
                }
            }
            progressBar.setVisibility(View.GONE);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_tv_show_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view);
        progressBar = view.findViewById(R.id.progressBar);

        alertDialog = new AlertDialog.Builder(view.getContext()).setTitle(getString(R.string.failure)).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create();

        LinearLayoutManager layoutManager= new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        ListTvShowViewModel mViewModel = ViewModelProviders.of(this).get(ListTvShowViewModel.class);
        mViewModel.doRequestListTvShows();
        mViewModel.getTvShowList().observe(this, getTvShows);
    }
}
