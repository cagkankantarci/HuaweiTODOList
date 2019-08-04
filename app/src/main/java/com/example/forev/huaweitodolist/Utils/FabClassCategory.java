package com.example.forev.huaweitodolist.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.daimajia.swipe.util.Attributes;
import com.example.forev.huaweitodolist.Activities.MainActivity;
import com.example.forev.huaweitodolist.Adapters.CategoryAdapter;
import com.example.forev.huaweitodolist.Adapters.CheckBoxAdapter;
import com.example.forev.huaweitodolist.Models.AddListModel;
import com.example.forev.huaweitodolist.Models.CategoryNameModel;
import com.example.forev.huaweitodolist.Models.MainCategoryModel;
import com.example.forev.huaweitodolist.R;
import com.example.forev.huaweitodolist.RestApi.ManagerAll;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FabClassCategory {

    private SharedPreferences getSharedPreferences;
    private GetSharedPreferences preferences;
    private Context mContext;
    String date ="", formatDate="",today;
    int whichFilter,whichOrder;
    List<MainCategoryModel> mDataSet;
    List<MainCategoryModel> mDataSet2;
    RecyclerView mRecyclerView;
    Activity activity;
    String userid;
    int temp=0;
    Fragment fragment;
    String listcategory;
    private List<CategoryNameModel> categoryNameModels;
    CheckBoxAdapter checkBoxAdapter;
    RecyclerView categoryRecyclerView;
    public static EditText othersText;
    public static TextInputLayout TextInputDeneme;
    List<CategoryNameModel> list = new ArrayList<>();

    public FabClassCategory(Context context, Activity activity, List<MainCategoryModel> mDataSet, List<MainCategoryModel> mDataSet2
            , RecyclerView mRecyclerView, Fragment fragment, String listcategory)
    {
        this.mContext = context;
        this.mDataSet = mDataSet;
        this.mDataSet2 = mDataSet2;
        this.mRecyclerView = mRecyclerView;
        this.activity = activity;
        this.fragment = fragment;
        this.listcategory = listcategory;
        preferences = new GetSharedPreferences(activity);
        getSharedPreferences = preferences.getSession();
        userid = getSharedPreferences.getString("id",null);
    }

    public void nameCheck()
    {
        Call<List<CategoryNameModel>> req = ManagerAll.getInstance().getCategoryName();
        req.enqueue(new Callback<List<CategoryNameModel>>() {
            @Override
            public void onResponse(Call<List<CategoryNameModel>> call, Response<List<CategoryNameModel>> response) {
                categoryNameModels = response.body();
                checkBoxAdapter = new CheckBoxAdapter(mContext,categoryNameModels,new FabClassCategory(mContext,activity,mDataSet,mDataSet2,mRecyclerView,fragment,listcategory));
                categoryRecyclerView.setAdapter(checkBoxAdapter);
            }

            @Override
            public void onFailure(Call<List<CategoryNameModel>> call, Throwable t) {

            }
        });
    }

    public void openAddDialog()
    {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.alertdialog_add,null);

        final EditText title = (EditText)view.findViewById(R.id.todolistTitle);
        final EditText desc = (EditText)view.findViewById(R.id.todolistDesc);
        othersText = (EditText)view.findViewById(R.id.othersText);
        TextInputDeneme = view.findViewById(R.id.TextInputDeneme);
        categoryRecyclerView = (RecyclerView)view.findViewById(R.id.categoryRecyclerView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(mContext,3);
        categoryRecyclerView.setLayoutManager(layoutManager);
        categoryNameModels = new ArrayList<>();
        nameCheck();
        CalendarView calendarView = (CalendarView)view.findViewById(R.id.todolistCalendar);
        today = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                DateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
                DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");

                date = dayOfMonth +"/"+(month+1)+"/"+year;

                try {
                    Date dat = inputFormat.parse(date);
                    formatDate = outputFormat.format(dat);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });


        AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
        alert.setTitle("You have a new plan?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(!formatDate.equals("") && !title.getText().toString().equals("")
                                && !desc.getText().toString().equals("") && checkBoxAdapter.deneme.size() > 0)
                        {
                            int groupId = (10000+(int)(Math.random()* 30000));

                            for(int searchGroup=0; searchGroup<mDataSet.size();searchGroup++)
                            {
                                if(mDataSet.get(searchGroup).getGroupid().toString().equals(groupId))
                                {
                                    groupId = (10000+(int)(Math.random()* 30000));
                                    searchGroup=0;
                                }
                            }

                            for(int k=0; k<checkBoxAdapter.deneme.size(); k++)
                            {
                                if(checkBoxAdapter.deneme.get(k).getId().equals("6"))
                                {
                                    if(!othersText.getText().toString().equals(""))
                                    {
                                        saveCategoryName(othersText.getText().toString(),String.valueOf(groupId));
                                    }
                                    else{
                                        Toast.makeText(mContext,"Enter the complete information. Failed to save list.",Toast.LENGTH_LONG).show();
                                        break;
                                    }
                                }
                                saveList(getSharedPreferences.getString("id",null),title.getText().toString(),
                                        checkBoxAdapter.deneme.get(k).getId().toString(),desc.getText().toString(),
                                        formatDate,today,String.valueOf(groupId));
                            }
                        }
                        else if(formatDate.equals("") && !title.getText().toString().equals("")
                                && !desc.getText().toString().equals(""))
                        {
                            Toast.makeText(mContext,"You did not mark the date. Please mark the date.",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(mContext,"Enter the complete information. Failed to save list.",Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        alert.setView(view);
        final AlertDialog dialog = alert.create();
        dialog.show();
    }

    public void saveList(String userid, String listname, String listcategory, String listdesc,String listdeadline,String today,String groupId)
    {
        Call<AddListModel> req = ManagerAll.getInstance().addList(userid,listname,listcategory,listdesc,listdeadline,today,groupId);
        req.enqueue(new Callback<AddListModel>() {
            @Override
            public void onResponse(Call<AddListModel> call, Response<AddListModel> response) {
                Toast.makeText(mContext,response.body().getText().toString(),Toast.LENGTH_LONG).show();
                listAllList();
            }

            @Override
            public void onFailure(Call<AddListModel> call, Throwable t) {
                Toast.makeText(mContext,Warning.internetProblemText,Toast.LENGTH_LONG).show();
            }
        });
    }

    public void saveCategoryName(String mainlistname,String groupidx)
    {
        Call<AddListModel> req = ManagerAll.getInstance().addCategory(mainlistname,userid,groupidx);
        req.enqueue(new Callback<AddListModel>() {
            @Override
            public void onResponse(Call<AddListModel> call, Response<AddListModel> response) {

            }

            @Override
            public void onFailure(Call<AddListModel> call, Throwable t) {

            }
        });
    }

    public void openFilterDialog()
    {
        final String[] filters = mContext.getResources().getStringArray(R.array.filter);

        AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
        alert.setTitle("Filter by what?");
        alert.setSingleChoiceItems(R.array.filter, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                whichFilter =  which;
            }
        });

        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch(whichFilter)
                {
                    case 0: filterAccordingToStatus(String.valueOf(whichFilter)); break;
                    case 1: filterAccordingToStatus(String.valueOf(whichFilter)); break;
                    case 2: filterAccordingToDate(); break;
                    case 3: listAllList(); break;
                    default: break;
                }
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        final AlertDialog dialog = alert.create();
        dialog.show();
    }

    public void openOrderDialog()
    {
        final String[] orders = mContext.getResources().getStringArray(R.array.order);

        AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
        alert.setTitle("Sort by?");
        alert.setSingleChoiceItems(R.array.order, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                whichOrder =  which;
            }
        });

        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch(whichOrder)
                {
                    case 0: listAccordingToCreateDate(); break;
                    case 1: listAccordingToDeadLine(); break;
                    case 2: listAccordingToName(); break;
                    case 3: listAccordingToStatus(); break;
                    default: break;
                }
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        final AlertDialog dialog = alert.create();
        dialog.show();
    }

    public void listAllList()
    {
        Call<List<MainCategoryModel>> req = ManagerAll.getInstance().listMainCategory(listcategory,userid);
        req.enqueue(new Callback<List<MainCategoryModel>>() {
            @Override
            public void onResponse(Call<List<MainCategoryModel>> call, Response<List<MainCategoryModel>> response) {
                if(response.body().size()>0) {
                    mDataSet = response.body();
                    mDataFunc();
                }
                else
                {
                    Toast.makeText(mContext,"There is no record.",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<List<MainCategoryModel>> call, Throwable t) {
            }
        });
    }

    public void filterAccordingToStatus(String status)
    {
        Call<List<MainCategoryModel>> req = ManagerAll.getInstance().filterListCat(userid,status,listcategory);
        req.enqueue(new Callback<List<MainCategoryModel>>() {
            @Override
            public void onResponse(Call<List<MainCategoryModel>> call, Response<List<MainCategoryModel>> response) {
                if(response.body().size()>0) {
                    mDataSet = response.body();
                    mDataFunc();
                }
                else
                {
                    mRecyclerView.setVisibility(View.GONE);
                    Toast.makeText(mContext,"There is no record.",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<MainCategoryModel>> call, Throwable t) {

            }
        });
    }

    public void filterAccordingToDate()
    {
        Call<List<MainCategoryModel>> req = ManagerAll.getInstance().listMainCategory(listcategory,userid);
        req.enqueue(new Callback<List<MainCategoryModel>>() {
            @Override
            public void onResponse(Call<List<MainCategoryModel>> call, Response<List<MainCategoryModel>> response) {
                if(response.body().size()>0) {
                    mDataSet = response.body();
                    mDataSet2 = new ArrayList<>();

                    Date today = new Date();
                    String year,month,day;
                    String all;

                    for(int i =0;i<mDataSet.size();i++)
                    {
                        all = mDataSet.get(i).getListdeadline().toString();

                        year = all.substring(6,10);
                        month = all.substring(3,5);
                        day = all.substring(0,2);

                        Calendar myNextCalendar = Calendar.getInstance();
                        myNextCalendar.set(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day));
                        Date nyd = myNextCalendar.getTime();

                        FabClassCategory fabClass = new FabClassCategory(mContext,activity,mDataSet,mDataSet2,mRecyclerView,fragment,listcategory);
                        long days = fabClass.daysBetween(today,nyd);

                        if(days >= 0)
                        {
                            mDataSet2.add(mDataSet.get(i));
                        }
                    }
                    mDataFunc2();
                }
                else{
                    mRecyclerView.setVisibility(View.GONE);
                    Toast.makeText(mContext,"There is no record.",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<List<MainCategoryModel>> call, Throwable t) {
            }
        });
    }

    public void listAccordingToCreateDate()
    {
        Call<List<MainCategoryModel>> req = ManagerAll.getInstance().listMainCategory(listcategory,userid);
        req.enqueue(new Callback<List<MainCategoryModel>>() {
            @Override
            public void onResponse(Call<List<MainCategoryModel>> call, Response<List<MainCategoryModel>> response) {
                if(response.body().size()>0) {
                    mDataSet = response.body();
                    mDataSet2 = new ArrayList<>();

                    Date today = new Date();
                    String year,month,day;
                    String all;

                    for(int i =0;i<mDataSet.size();i++)
                    {
                        all = mDataSet.get(i).getCreatedate().toString();

                        year = all.substring(6,10);
                        month = all.substring(3,5);
                        day = all.substring(0,2);

                        Calendar myNextCalendar = Calendar.getInstance();
                        myNextCalendar.set(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day));
                        Date nyd = myNextCalendar.getTime();

                        FabClassCategory fabClass = new FabClassCategory(mContext,activity,mDataSet,mDataSet2,mRecyclerView,fragment,listcategory);
                        long days = fabClass.daysBetween(today,nyd);

                        if(days > temp)
                        {
                            mDataSet2.add(mDataSet.get(i));
                            temp=(int)days;
                        }
                        else{
                            int count=0;
                            for(int x=0; x<mDataSet2.size();x++)
                            {
                                all = mDataSet2.get(mDataSet2.size()-x-1).getCreatedate().toString();

                                year = all.substring(6,10);
                                month = all.substring(3,5);
                                day = all.substring(0,2);

                                myNextCalendar = Calendar.getInstance();
                                myNextCalendar.set(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day));
                                nyd = myNextCalendar.getTime();

                                fabClass = new FabClassCategory(mContext,activity,mDataSet,mDataSet2,mRecyclerView,fragment,listcategory);
                                long oldDays = fabClass.daysBetween(today,nyd);

                                if(oldDays > days)
                                {
                                    count++;
                                }
                            }
                            mDataSet2.add(mDataSet2.size()-count,mDataSet.get(i));
                        }
                    }
                    mDataFunc2();
                }
            }
            @Override
            public void onFailure(Call<List<MainCategoryModel>> call, Throwable t) {
            }
        });
    }

    public void listAccordingToDeadLine()
    {
        Call<List<MainCategoryModel>> req = ManagerAll.getInstance().listMainCategory(listcategory,userid);
        req.enqueue(new Callback<List<MainCategoryModel>>() {
            @Override
            public void onResponse(Call<List<MainCategoryModel>> call, Response<List<MainCategoryModel>> response) {
                if(response.body().size()>0) {
                    mDataSet = response.body();
                    mDataSet2 = new ArrayList<>();

                    Date today = new Date();
                    String year,month,day;
                    String all;

                    for(int i =0;i<mDataSet.size();i++)
                    {
                        all = mDataSet.get(i).getListdeadline().toString();

                        year = all.substring(6,10);
                        month = all.substring(3,5);
                        day = all.substring(0,2);

                        Calendar myNextCalendar = Calendar.getInstance();
                        myNextCalendar.set(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day));
                        Date nyd = myNextCalendar.getTime();

                        FabClassCategory fabClass = new FabClassCategory(mContext,activity,mDataSet,mDataSet2,mRecyclerView,fragment,listcategory);
                        long days = fabClass.daysBetween(today,nyd);

                        if(days > temp)
                        {
                            mDataSet2.add(mDataSet.get(i));
                            temp=(int)days;
                        }
                        else{
                            int count=0;
                            for(int x=0; x<mDataSet2.size();x++)
                            {
                                all = mDataSet2.get(mDataSet2.size()-x-1).getListdeadline().toString();

                                year = all.substring(6,10);
                                month = all.substring(3,5);
                                day = all.substring(0,2);

                                myNextCalendar = Calendar.getInstance();
                                myNextCalendar.set(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day));
                                nyd = myNextCalendar.getTime();

                                fabClass = new FabClassCategory(mContext,activity,mDataSet,mDataSet2,mRecyclerView,fragment,listcategory);
                                long oldDays = fabClass.daysBetween(today,nyd);

                                if(oldDays > days)
                                {
                                    count++;
                                }
                            }
                            mDataSet2.add(mDataSet2.size()-count,mDataSet.get(i));
                        }
                    }
                    mDataFunc2();
                }
            }
            @Override
            public void onFailure(Call<List<MainCategoryModel>> call, Throwable t) {
            }
        });
    }

    public void listAccordingToName()
    {
        Call<List<MainCategoryModel>> req = ManagerAll.getInstance().getListbyNameForCategory(listcategory,userid);
        req.enqueue(new Callback<List<MainCategoryModel>>() {
            @Override
            public void onResponse(Call<List<MainCategoryModel>> call, Response<List<MainCategoryModel>> response) {
                if(response.body().size()>0) {
                    mDataSet = response.body();
                    mDataFunc();
                }
            }
            @Override
            public void onFailure(Call<List<MainCategoryModel>> call, Throwable t) {
            }
        });
    }

    public void listAccordingToStatus()
    {
        Call<List<MainCategoryModel>> req = ManagerAll.getInstance().getListbyStatusForCategory(listcategory,userid);
        req.enqueue(new Callback<List<MainCategoryModel>>() {
            @Override
            public void onResponse(Call<List<MainCategoryModel>> call, Response<List<MainCategoryModel>> response) {
                if(response.body().size()>0) {
                    mDataSet = response.body();
                    mDataFunc();
                }
            }
            @Override
            public void onFailure(Call<List<MainCategoryModel>> call, Throwable t) {
            }
        });
    }

    public long daysBetween(Date one, Date two)
    {
        long difference = (one.getTime()-two.getTime())/86400000;
        if(difference<=0)
        {
            return difference;
        }
        return Math.abs(difference);
    }

    public void mDataFunc()
    {
        if(mDataSet.isEmpty()){
            mRecyclerView.setVisibility(View.GONE);
            Toast.makeText(mContext,"There is no record.",Toast.LENGTH_LONG).show();
        }else{
            mRecyclerView.setVisibility(View.VISIBLE);
        }

        CategoryAdapter mAdapter = new CategoryAdapter(mContext, mDataSet,fragment,listcategory);
        ((CategoryAdapter) mAdapter).setMode(Attributes.Mode.Single);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.e("RecyclerView", "onScrollStateChanged");
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        MainActivity.CATADAPTER = mAdapter;
        MainActivity.WHICHFAB = 2;
    }

    public void mDataFunc2()
    {
        if(mDataSet2.isEmpty()){
            mRecyclerView.setVisibility(View.GONE);
            Toast.makeText(mContext,"There is no record.",Toast.LENGTH_LONG).show();
        }else{
            mRecyclerView.setVisibility(View.VISIBLE);
        }

        CategoryAdapter mAdapter = new CategoryAdapter(mContext, mDataSet2,fragment,listcategory);
        ((CategoryAdapter) mAdapter).setMode(Attributes.Mode.Single);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.e("RecyclerView", "onScrollStateChanged");
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        MainActivity.CATADAPTER = mAdapter;
        MainActivity.WHICHFAB = 2;
    }
}
