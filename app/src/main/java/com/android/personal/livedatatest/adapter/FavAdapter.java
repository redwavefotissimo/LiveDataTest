package com.android.personal.livedatatest.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.android.personal.livedatatest.R;
import com.android.personal.livedatatest.model.Favourites;
import com.android.personal.livedatatest.viewholder.FavViewHolder;
import com.android.personal.livedatatest.viewmodel.FavouritesViewModel;

import java.util.Date;
import java.util.List;

public class FavAdapter extends RecyclerView.Adapter<FavViewHolder> {

    private List<Favourites> mFav;
    private FavouritesViewModel mFavViewModel;

    public FavAdapter(List<Favourites> mFav, FavouritesViewModel mFavViewModel){
        this.mFav = mFav;
        this.mFavViewModel = mFavViewModel;
    }

    @Override
    public FavViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_row, parent, false);
        return new FavViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FavViewHolder holder, final int position) {
        Favourites favourites = mFav.get(position);
        holder.mTxtUrl.setText(favourites.mUrl);
        holder.mTxtDate.setText((new Date(favourites.mDate).toString()));
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Favourites favourites = mFav.get(position);
                mFavViewModel.removeFav(favourites.mId);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFav.size();
    }


}
