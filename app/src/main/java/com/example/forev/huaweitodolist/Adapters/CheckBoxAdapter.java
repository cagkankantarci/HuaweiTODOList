package com.example.forev.huaweitodolist.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.forev.huaweitodolist.Models.CategoryNameModel;
import com.example.forev.huaweitodolist.R;
import com.example.forev.huaweitodolist.Utils.FabClass;
import com.example.forev.huaweitodolist.Utils.FabClassCategory;

import java.util.ArrayList;
import java.util.List;

public class CheckBoxAdapter extends RecyclerView.Adapter<CheckBoxAdapter.MyViewHolder> {

    Context context;
    public List<CategoryNameModel> categoryNameModels;
    CheckBox myCheckBoxes;
    FabClass fabClass;
    FabClassCategory fabClassCategory;
    public List<CategoryNameModel> deneme=new ArrayList<>();
    public int okay=0;

    public CheckBoxAdapter(Context context, List<CategoryNameModel> categoryNameModels, FabClass fabClass) {
        this.context = context;
        this.categoryNameModels = categoryNameModels;
        this.fabClass = fabClass;
    }

    public CheckBoxAdapter(Context context, List<CategoryNameModel> categoryNameModels, FabClassCategory fabClassCategory) {
        this.context = context;
        this.categoryNameModels = categoryNameModels;
        this.fabClassCategory = fabClassCategory;
    }

    @NonNull
    @Override
    public CheckBoxAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_checkbox,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckBoxAdapter.MyViewHolder myViewHolder, final int position) {

        myViewHolder.categoryTxt.setText(categoryNameModels.get(position).getMainlistname().toString());

        myViewHolder.setItemClickListener(new MyViewHolder.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                myCheckBoxes = (CheckBox) view;
                CategoryNameModel currentCB = categoryNameModels.get(position);

                if(myCheckBoxes.isChecked())
                {
                    deneme.add(currentCB);
                    if(currentCB.getId().equals("6") && fabClass!=null)
                    {
                        fabClass.TextInputDeneme.setVisibility(View.VISIBLE);
                        fabClass.othersText.setVisibility(View.VISIBLE);
                    }
                    else if(currentCB.getId().equals("6") && fabClassCategory!=null)
                    {
                        fabClassCategory.TextInputDeneme.setVisibility(View.VISIBLE);
                        fabClassCategory.othersText.setVisibility(View.VISIBLE);
                    }
                }
                else if(!myCheckBoxes.isChecked())
                {
                    if(deneme.contains(currentCB)&& fabClass!=null)
                    {
                        deneme.remove(currentCB);
                        fabClass.TextInputDeneme.setVisibility(View.INVISIBLE);
                        fabClass.othersText.setVisibility(View.INVISIBLE);
                    }
                    else if(deneme.contains(currentCB)&& fabClassCategory!=null)
                    {
                        deneme.remove(currentCB);
                        fabClassCategory.TextInputDeneme.setVisibility(View.INVISIBLE);
                        fabClassCategory.othersText.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryNameModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView categoryTxt;
        CheckBox myCheckBox;

        ItemClickListener itemClickListener;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryTxt = (TextView)itemView.findViewById(R.id.categoryTextView);
            myCheckBox = (CheckBox)itemView.findViewById(R.id.checkbox);

            myCheckBox.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener ic)
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
