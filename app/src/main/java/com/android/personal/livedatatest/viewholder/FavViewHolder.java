package com.android.personal.livedatatest.viewholder;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.personal.livedatatest.R;

public class FavViewHolder extends RecyclerView.ViewHolder {

    public TextView mTxtUrl;
    public TextView mTxtDate;
    public ImageButton btnDelete;

    public FavViewHolder(View itemView) {
        super(itemView);
        mTxtUrl = itemView.findViewById(R.id.tvUrl);
        mTxtDate = itemView.findViewById(R.id.tvDate);
        btnDelete = itemView.findViewById(R.id.btnDelete);
    }

}
