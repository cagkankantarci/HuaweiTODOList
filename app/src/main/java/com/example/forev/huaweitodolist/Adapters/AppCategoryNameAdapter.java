package com.example.forev.huaweitodolist.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.forev.huaweitodolist.Models.CategoryNameModel;
import com.example.forev.huaweitodolist.R;

import java.util.List;

public class AppCategoryNameAdapter extends RecyclerView.Adapter<AppCategoryNameAdapter.MyViewHolder> {

    Context context;
    List<CategoryNameModel> catList;

    public AppCategoryNameAdapter(Context context, List<CategoryNameModel> catList) {
        this.context = context;
        this.catList = catList;
    }

    @NonNull
    @Override
    public AppCategoryNameAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cat_name,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppCategoryNameAdapter.MyViewHolder myViewHolder, final int i) {

        myViewHolder.catName.setText((i+1)+"."+catList.get(i).getMainlistname().toString());
    }

    @Override
    public int getItemCount() {
        return catList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView catName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            catName = itemView.findViewById(R.id.catName);
        }
    }
}
