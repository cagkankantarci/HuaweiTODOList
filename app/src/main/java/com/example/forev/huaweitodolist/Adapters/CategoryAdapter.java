package com.example.forev.huaweitodolist.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.example.forev.huaweitodolist.Models.DeleteModel;
import com.example.forev.huaweitodolist.Models.MainCategoryModel;
import com.example.forev.huaweitodolist.Models.UpdateModel;
import com.example.forev.huaweitodolist.R;
import com.example.forev.huaweitodolist.RestApi.ManagerAll;
import com.example.forev.huaweitodolist.Utils.ChangeFragments;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryAdapter extends RecyclerSwipeAdapter<CategoryAdapter.SimpleViewHolder> implements Filterable {

    private Context mContext;
    List<MainCategoryModel> mainCategoryModel;
    private ChangeFragments changeFragments;
    List<MainCategoryModel> mainCategoryModelFull;
    Fragment fragment;
    String listcategory;

    public CategoryAdapter(Context context, List<MainCategoryModel> objects, Fragment fragment, String listcategory) {
        this.mContext = context;
        this.mainCategoryModel = objects;
        mainCategoryModelFull = new ArrayList<>(mainCategoryModel);
        this.fragment = fragment;
        this.listcategory = listcategory;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.swipe_layout, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SimpleViewHolder viewHolder, final int position) {
        final MainCategoryModel item = mainCategoryModel.get(position);

        if(item.getStatus().toString().equals("0"))
        {
            viewHolder.btnComplete.setImageResource(R.drawable.nonmarkm);
            viewHolder.Name.setText(item.getListname().toString());
            viewHolder.Name.setPaintFlags(viewHolder.Name.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            viewHolder.Name.setTextColor(Color.parseColor("#9e9e9e"));
            viewHolder.listDescription.setText(item.getListdesc().toString());
            viewHolder.listDescription.setPaintFlags(viewHolder.listDescription.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            viewHolder.listDescription.setTextColor(Color.parseColor("#9e9e9e"));
        }
        else
        {
            viewHolder.Name.setText(item.getListname().toString());
            viewHolder.listDescription.setText(item.getListdesc().toString());
            viewHolder.btnComplete.setImageResource(R.drawable.mark);
            viewHolder.Name.setPaintFlags(viewHolder.Name.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
            viewHolder.Name.setTextColor(Color.parseColor("#c33335"));
            viewHolder.listDescription.setPaintFlags(viewHolder.listDescription.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
            viewHolder.listDescription.setTextColor(Color.parseColor("#000000"));
        }

        viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);

        //dari kiri
        viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Left, viewHolder.swipeLayout.findViewById(R.id.bottom_wrapper1));

        //dari kanan
        viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, viewHolder.swipeLayout.findViewById(R.id.bottom_wraper));

        viewHolder.swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onStartOpen(SwipeLayout layout) {

            }

            @Override
            public void onOpen(SwipeLayout layout) {

            }

            @Override
            public void onStartClose(SwipeLayout layout) {

            }

            @Override
            public void onClose(SwipeLayout layout) {

            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {

            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {

            }
        });

        viewHolder.btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInfoDialog(item);
            }
        });

        viewHolder.btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String st;

                Log.i("cagkan","1");
                if(item.getStatus().toString().equals("1"))
                {
                    viewHolder.btnComplete.setImageResource(R.drawable.nonmarkm);
                    viewHolder.Name.setText(item.getListname().toString());
                    viewHolder.Name.setPaintFlags(viewHolder.Name.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    viewHolder.Name.setTextColor(Color.parseColor("#9e9e9e"));
                    viewHolder.listDescription.setText(item.getListdesc().toString());
                    viewHolder.listDescription.setPaintFlags(viewHolder.listDescription.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    viewHolder.listDescription.setTextColor(Color.parseColor("#9e9e9e"));
                    st="0";
                    Log.i("cagkan",item.getStatus().toString());
                }
                else{
                    viewHolder.Name.setText(item.getListname().toString());
                    viewHolder.listDescription.setText(item.getListdesc().toString());
                    viewHolder.btnComplete.setImageResource(R.drawable.mark);
                    viewHolder.Name.setPaintFlags(viewHolder.Name.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                    viewHolder.Name.setTextColor(Color.parseColor("#c33335"));
                    viewHolder.listDescription.setPaintFlags(viewHolder.listDescription.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                    viewHolder.listDescription.setTextColor(Color.parseColor("#000000"));
                    st="1";
                    Log.i("cagkan",item.getStatus().toString());
                }
                Log.i("cagkan","4");
                Log.i("cagkan",item.getAlllistid()+"1");
                Log.i("cagkan",item.getId()+"2");
                Log.i("cagkan",item.getStatus()+"3");
                    updateItem(item.getAlllistid(),st);
            }
        });

        viewHolder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem(item.getGroupid().toString(),item.getListcategory().toString());
                mItemManger.removeShownLayouts(viewHolder.swipeLayout);
                mainCategoryModel.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, mainCategoryModel.size());
                mItemManger.closeAllItems();
            }
        });

        mItemManger.bindView(viewHolder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return mainCategoryModel.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    public Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<MainCategoryModel> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0)
            {
                filteredList.addAll(mainCategoryModelFull);
            }
            else{
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(MainCategoryModel item : mainCategoryModelFull)
                {
                    if(item.getListname().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mainCategoryModel.clear();
            mainCategoryModel.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };

    public static class SimpleViewHolder extends RecyclerView.ViewHolder{
        public SwipeLayout swipeLayout;
        public TextView Name;
        public TextView listDescription;
        ImageButton Delete;
        ImageButton btnLocation;
        ImageButton btnComplete;

        public SimpleViewHolder(View itemView) {
            super(itemView);

            swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipe);
            Name = (TextView) itemView.findViewById(R.id.Name);
            listDescription = (TextView) itemView.findViewById(R.id.listDescription);
            Delete = (ImageButton) itemView.findViewById(R.id.btnDelete);
            btnLocation = (ImageButton) itemView.findViewById(R.id.btnLocation);
            btnComplete = (ImageButton) itemView.findViewById(R.id.btnComplete);
        }
    }

    private void getFragment(){
        changeFragments = new ChangeFragments(mContext);
        changeFragments.changeWithParameters(fragment,listcategory);
    }

    public void deleteItem(String id,String listcategory)
    {
        final Call<DeleteModel> req = ManagerAll.getInstance().deleteCategoryItem(id,listcategory);
        req.enqueue(new Callback<DeleteModel>() {
            @Override
            public void onResponse(Call<DeleteModel> call, Response<DeleteModel> response) {
                Log.i("onResponse",response.body().isTf()+"");
            }

            @Override
            public void onFailure(Call<DeleteModel> call, Throwable t) {

            }
        });
    }

    public void openInfoDialog(MainCategoryModel item)
    {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.alert_dialog_info,null);

        TextView info_title = (TextView)view.findViewById(R.id.info_title);
        TextView info_category = (TextView)view.findViewById(R.id.info_category);
        TextView info_description = (TextView)view.findViewById(R.id.info_description);
        TextView info_deadline = (TextView)view.findViewById(R.id.info_deadline);
        TextView info_createdate = (TextView)view.findViewById(R.id.info_createdate);
        TextView info_status = (TextView)view.findViewById(R.id.info_status);

        info_title.setText(item.getListname().toString());
        info_category.setText(item.getMainlistname().toString());
        info_description.setText(item.getListdesc().toString());
        info_deadline.setText(item.getListdeadline().toString());
        info_createdate.setText(item.getCreatedate().toString());
        info_status.setText(item.getStatus().toString().equals("1") ? "NOT DONE YET" : "DONE");

        AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
        alert.setTitle("INFO")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        alert.setView(view);
        final AlertDialog dialog = alert.create();
        dialog.show();
    }

    public void updateItem(String id,String status)
    {
        Call<UpdateModel> req = ManagerAll.getInstance().updateItemCat(id,status);
        req.enqueue(new Callback<UpdateModel>() {
            @Override
            public void onResponse(Call<UpdateModel> call, Response<UpdateModel> response) {
                Log.i("status","girdi 4");
                getFragment();
            }

            @Override
            public void onFailure(Call<UpdateModel> call, Throwable t) {

            }
        });
    }

}
