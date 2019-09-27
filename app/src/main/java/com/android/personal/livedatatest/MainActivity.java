package com.android.personal.livedatatest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.personal.livedatatest.adapter.FavAdapter;
import com.android.personal.livedatatest.model.Favourites;
import com.android.personal.livedatatest.viewmodel.FavouritesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FavAdapter mFavAdapter;
    private FavouritesViewModel mFavViewModel;
    private List<Favourites> mFav;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFav = new ArrayList<>();

        fab = findViewById(R.id.fab);
        final RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mFavViewModel = ViewModelProviders.of(this).get(FavouritesViewModel.class);
        mFavAdapter = new FavAdapter(mFav, mFavViewModel);
        recyclerView.setAdapter(mFavAdapter);
        final Observer<List<Favourites>> favsObserver = new Observer<List<Favourites>>() {
            @Override
            public void onChanged(@Nullable final List<Favourites> updatedList) {
                //result.dispatchUpdatesTo(mFavAdapter);

                mFav.clear();
                mFav.addAll(updatedList);
                mFavAdapter.notifyDataSetChanged();
            }

        };

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText inUrl = new EditText(MainActivity.this);
                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("New favourite")
                        .setMessage("Add a url link below")
                        .setView(inUrl)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String url = String.valueOf(inUrl.getText());
                                long date = (new Date()).getTime();

                                mFavViewModel.addFav(url, date);
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
            }
        });

        mFavViewModel.getFavs().observe(this, favsObserver);
    }
}
