package com.example.mymoviecatalogue3.listtvshow;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mymoviecatalogue3.R;
import com.example.mymoviecatalogue3.listmovies.ListMoviesAdapter;
import com.example.mymoviecatalogue3.listtvshow.pojo.ResultsItem;

import java.util.List;

public class ListTvShowsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ResultsItem> itemList;

    private OnItemClickListener mitemClickListener;

    ListTvShowsAdapter(List<ResultsItem> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie_list, viewGroup, false);

        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            final ResultsItem model = getItem(position);
            ViewHolder genericViewHolder = (ViewHolder) holder;

            genericViewHolder.itemTxtTitle.setText(model.getName());

            if (model.getOverview().length() > 50) {
                genericViewHolder.itemTxtOverview.setText(model.getOverview().substring(0, 49) + "...");

            } else {
                genericViewHolder.itemTxtOverview.setText(model.getOverview());
            }
            Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w185" + model.getPosterPath()).into(genericViewHolder.imgPoster);
        }

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
    void SetOnItemClickListener(final OnItemClickListener mItemClickListener){
        this.mitemClickListener = mItemClickListener;
    }
    private ResultsItem getItem(int position){
        return itemList.get(position);
    }
    public interface OnItemClickListener{
        void onItemClick(View view, ResultsItem model);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgPoster;
        private TextView itemTxtTitle;
        private TextView itemTxtOverview;

        ViewHolder(final View itemView) {
            super(itemView);

            this.imgPoster = itemView.findViewById(R.id.img_poster);
            this.itemTxtOverview = itemView.findViewById(R.id.item_txt_overview);
            this.itemTxtTitle = itemView.findViewById(R.id.item_txt_title);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    mitemClickListener.onItemClick(itemView, itemList.get(getAdapterPosition()));

                }
            });
        }
    }
}
