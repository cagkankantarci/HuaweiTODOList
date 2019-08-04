package com.example.forev.huaweitodolist.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.forev.huaweitodolist.Activities.MainActivity;
import com.example.forev.huaweitodolist.Models.MyCategoryModel;
import com.example.forev.huaweitodolist.R;

import java.util.ArrayList;
import java.util.List;

public class MyCategoryNameAdapter extends RecyclerView.Adapter<MyCategoryNameAdapter.MyViewHolder> {

    Context context;
    List<MyCategoryModel> catList;
    CheckBox myCheckBoxes;
    public List<MyCategoryModel> deneme=new ArrayList<>();

    public MyCategoryNameAdapter(Context context, List<MyCategoryModel> catList) {
        this.context = context;
        this.catList = catList;
    }

    @NonNull
    @Override
    public MyCategoryNameAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mycat_name,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCategoryNameAdapter.MyViewHolder myViewHolder, final int i) {

        myViewHolder.catName.setText((i+1)+"."+catList.get(i).getMainlistname().toString());


        myViewHolder.setItemClickListener(new MyViewHolder.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                myCheckBoxes = (CheckBox) view;
                MyCategoryModel currentCB = catList.get(position);

                if(myCheckBoxes.isChecked())
                {
                    deneme.add(currentCB);
                    new MainActivity().DELETEBUTTON.setVisibility(View.VISIBLE);
                }
                else if(!myCheckBoxes.isChecked())
                {
                    deneme.remove(currentCB);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return catList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView catName;
        CheckBox myCheckBox;

        ItemClickListener itemClickListener;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            catName = (TextView)itemView.findViewById(R.id.catName);
            myCheckBox = (CheckBox)itemView.findViewById(R.id.checkbox);

            myCheckBox.setOnClickListener(this);
        }

        public void setItemClickListener(MyCategoryNameAdapter.MyViewHolder.ItemClickListener ic)
        {
            this.itemClickListener = ic;
        }

        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(v,getLayoutPosition());
        }

        interface ItemClickListener{
            void onItemClick(View view,int position);
        }
    }
}
