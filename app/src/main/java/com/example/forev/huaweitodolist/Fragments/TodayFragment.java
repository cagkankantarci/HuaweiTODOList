package com.example.forev.huaweitodolist.Fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.forev.huaweitodolist.Models.AllListModel;
import com.example.forev.huaweitodolist.R;
import com.example.forev.huaweitodolist.Utils.FabClass;

import java.util.ArrayList;
import java.util.List;


public class TodayFragment extends Fragment {

    View view;
    private FloatingActionButton fab,fab_filter,fab_order;
    private RecyclerView todayRecyclerView;
    List<AllListModel> mDataSet;
    List<AllListModel> mDataSet2;
    String id;
    FabClass fabClass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_today, container, false);
        defineLayout();
        fabClass = new FabClass(getContext(),getActivity(),mDataSet,mDataSet2,todayRecyclerView,"1",new TodayFragment());
        fabClass.listAllList();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabClass.openAddDialog();
            }
        });
        fab_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabClass.openFilterDialog();
            }
        });
        fab_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabClass.openOrderDialog();
            }
        });
        return view;
    }

    public void defineLayout()
    {
        fab = (FloatingActionButton)view.findViewById(R.id.fab);
        fab_filter = (FloatingActionButton)view.findViewById(R.id.fab_filter);
        fab_order = (FloatingActionButton)view.findViewById(R.id.fab_order);
        mDataSet = new ArrayList<>();
        todayRecyclerView = (RecyclerView)view.findViewById(R.id.todayRecyclerView);
        todayRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mDataSet2 = new ArrayList<>();
    }

}
