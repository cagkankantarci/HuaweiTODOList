package com.example.forev.huaweitodolist.Fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.forev.huaweitodolist.Models.MainCategoryModel;
import com.example.forev.huaweitodolist.R;
import com.example.forev.huaweitodolist.Utils.FabClassCategory;

import java.util.ArrayList;
import java.util.List;


public class MoviesFragment extends Fragment {

    View view;
    private FloatingActionButton fab,fab_filter,fab_order;
    RecyclerView moviesRecyclerView;
    List<MainCategoryModel> mDataSet;
    List<MainCategoryModel> mDataSet2;
    String id;
    FabClassCategory fabClassCategory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_movies, container, false);
        define();
        fabClassCategory = new FabClassCategory(getContext(),getActivity(),mDataSet,mDataSet2,moviesRecyclerView,new MoviesFragment(),id);
        fabClassCategory.listAllList();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabClassCategory.openAddDialog();
            }
        });
        fab_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabClassCategory.openFilterDialog();
            }
        });
        fab_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabClassCategory.openOrderDialog();
            }
        });
        return view;
    }

    public void define()
    {
        fab = (FloatingActionButton)view.findViewById(R.id.fab);
        fab_filter = (FloatingActionButton)view.findViewById(R.id.fab_filter);
        fab_order = (FloatingActionButton)view.findViewById(R.id.fab_order);
        mDataSet = new ArrayList<>();
        mDataSet2 = new ArrayList<>();
        id = getArguments().getString("id").toString();
        moviesRecyclerView = (RecyclerView)view.findViewById(R.id.moviesRecyclerView);
        moviesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }


}
