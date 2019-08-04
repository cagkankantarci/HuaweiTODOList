package com.example.forev.huaweitodolist.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.forev.huaweitodolist.Adapters.AppCategoryNameAdapter;
import com.example.forev.huaweitodolist.Adapters.CategoryAdapter;
import com.example.forev.huaweitodolist.Adapters.MyCategoryNameAdapter;
import com.example.forev.huaweitodolist.Adapters.NonCategoryAdapter;
import com.example.forev.huaweitodolist.Fragments.HomeFragment;
import com.example.forev.huaweitodolist.Fragments.MoviesFragment;
import com.example.forev.huaweitodolist.Fragments.OthersFragment;
import com.example.forev.huaweitodolist.Fragments.ShoppingFragment;
import com.example.forev.huaweitodolist.Fragments.TodayFragment;
import com.example.forev.huaweitodolist.Fragments.TravelFragment;
import com.example.forev.huaweitodolist.Fragments.WorkFragment;
import com.example.forev.huaweitodolist.Models.AllListModel;
import com.example.forev.huaweitodolist.Models.CategoryNameModel;
import com.example.forev.huaweitodolist.Models.DeleteModel;
import com.example.forev.huaweitodolist.Models.MyCategoryModel;
import com.example.forev.huaweitodolist.R;
import com.example.forev.huaweitodolist.RestApi.ManagerAll;
import com.example.forev.huaweitodolist.Utils.ChangeFragments;
import com.example.forev.huaweitodolist.Utils.GetSharedPreferences;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private SharedPreferences getSharedPreferences;
    private GetSharedPreferences preferences;
    private ChangeFragments changeFragments;
    private static final String FILE_NAME = "export.txt";
    AppCategoryNameAdapter appCategoryNameAdapter;
    List<CategoryNameModel> categoryNameModels;
    List<MyCategoryModel> myCategoryModels;
    List<AllListModel> allListModels;
    MyCategoryNameAdapter myCategoryNameAdapter;
    public static Button DELETEBUTTON;
    public static NonCategoryAdapter ADAPTER;
    public static CategoryAdapter CATADAPTER;
    public static int WHICHFAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getFragment();
        define();
        controlUser();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headView = navigationView.getHeaderView(0);

        final TextView firstname = headView.findViewById(R.id.nav_username);
        final TextView email = headView.findViewById(R.id.nav_email);

        firstname.setText(getSharedPreferences.getString("username",null));
        email.setText(getSharedPreferences.getString("email",null));
    }

    private void getFragment() {
        changeFragments = new ChangeFragments(MainActivity.this);
        changeFragments.change(new HomeFragment());
    }

    public void define()
    {
        preferences = new GetSharedPreferences(MainActivity.this);
        getSharedPreferences = preferences.getSession();
        categoryNameModels = new ArrayList<>();
        myCategoryModels = new ArrayList<>();
        allListModels = new ArrayList<>();
    }

    public void controlUser()
    {
        if(getSharedPreferences.getString("id",null) == null && getSharedPreferences.getString("email",null) == null &&
                getSharedPreferences.getString("username",null) == null)
        {
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search_menu,menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView2 = (SearchView)searchItem.getActionView();

        searchView2.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                switch(WHICHFAB)
                {
                    case 1: ADAPTER.getFilter().filter(newText); break;
                    case 2: CATADAPTER.getFilter().filter(newText); break;
                    default: break;
                }
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_home) {
            getFragment();
        } else if (id == R.id.nav_today) {
            changeFragments = new ChangeFragments(MainActivity.this);
            changeFragments.change(new TodayFragment());
        } else if (id == R.id.nav_work) {
            changeFragments = new ChangeFragments(MainActivity.this);
            changeFragments.changeWithParameters(new WorkFragment(),"1");
        } else if (id == R.id.nav_shopping) {
            changeFragments = new ChangeFragments(MainActivity.this);
            changeFragments.changeWithParameters(new ShoppingFragment(),"2");
        } else if (id == R.id.nav_movies) {
            changeFragments = new ChangeFragments(MainActivity.this);
            changeFragments.changeWithParameters(new MoviesFragment(),"3");
        } else if (id == R.id.nav_travel) {
            changeFragments = new ChangeFragments(MainActivity.this);
            changeFragments.changeWithParameters(new TravelFragment(),"4");
        }else if (id == R.id.nav_others) {
            changeFragments = new ChangeFragments(MainActivity.this);
            changeFragments.changeWithParameters(new OthersFragment(),"6");
        }else if (id == R.id.nav_settings) {
            openSettingsDialog();
        }else if (id == R.id.nav_exit) {
            GetSharedPreferences getSharedPreferences = new GetSharedPreferences(MainActivity.this);
            getSharedPreferences.deleteToSession();
            Intent intent = new Intent(MainActivity.this,MainActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void openSettingsDialog()
    {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.alertdialog_settings,null);

        RecyclerView appCatNameRecyclerView= (RecyclerView)view.findViewById(R.id.appCatNameRecyclerView);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        appCatNameRecyclerView.setLayoutManager(layoutManager);
        appCatNameFunction(appCatNameRecyclerView);

        RecyclerView myCatNameRecyclerView= (RecyclerView)view.findViewById(R.id.myCatNameRecyclerView);
        LinearLayoutManager layoutManager2= new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        myCatNameRecyclerView.setLayoutManager(layoutManager2);
        myCatNameFunction(myCatNameRecyclerView);

        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Details & Settings")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        alert.setView(view);
        final AlertDialog dialog = alert.create();
        dialog.show();

        DELETEBUTTON = (Button)view.findViewById(R.id.deleteButton);

        DELETEBUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0; i< myCategoryNameAdapter.deneme.size();i++)
                {
                    deleteCategory(myCategoryNameAdapter.deneme.get(i).getId().toString(),
                            myCategoryNameAdapter.deneme.get(i).getGroupid().toString(),
                            getSharedPreferences.getString("id",null));
                }
                dialog.cancel();
            }
        });

        Button exportButton = view.findViewById(R.id.exportButton);
        exportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allList();
            }
        });

    }

    public void allList()
    {
        Call<List<AllListModel>> req = ManagerAll.getInstance().getAllList(getSharedPreferences.getString("id",null).toString());
        req.enqueue(new Callback<List<AllListModel>>() {
            @Override
            public void onResponse(Call<List<AllListModel>> call, Response<List<AllListModel>> response) {
                allListModels = response.body();
                String allText="";

                FileOutputStream fos=null;
                try {
                    fos=openFileOutput(FILE_NAME,MODE_PRIVATE);for(int i=0;i<allListModels.size();i++)
                    {
                        allText="Listname: "+allListModels.get(i).getListname()+" Description: "+allListModels.get(i).getListdesc()+
                                " Deadline: "+allListModels.get(i).getListdeadline()+" Create Date:"+allListModels.get(i).getCreatedate();
                        fos.write(allText.concat("\n").getBytes());
                    }
                    Toast.makeText(getApplicationContext(),"Saved to "+ getFilesDir()+"/"+FILE_NAME,Toast.LENGTH_LONG).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if(fos != null)
                    {
                        try{
                            fos.close();
                        } catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<AllListModel>> call, Throwable t) {

            }
        });
    }
    public void appCatNameFunction(final RecyclerView appCatNameRecyclerView)
    {
        Call<List<CategoryNameModel>> req = ManagerAll.getInstance().getCategoryName();
        req.enqueue(new Callback<List<CategoryNameModel>>() {
            @Override
            public void onResponse(Call<List<CategoryNameModel>> call, Response<List<CategoryNameModel>> response) {
                categoryNameModels  = response.body();
                appCategoryNameAdapter = new AppCategoryNameAdapter(getApplicationContext(),categoryNameModels);
                appCatNameRecyclerView.setAdapter(appCategoryNameAdapter);
            }

            @Override
            public void onFailure(Call<List<CategoryNameModel>> call, Throwable t) {

            }
        });
    }

    public void myCatNameFunction(final RecyclerView myCatNameRecyclerView)
    {
        Call<List<MyCategoryModel>> req = ManagerAll.getInstance().getMyCategoryName(getSharedPreferences.getString("id",null));
        req.enqueue(new Callback<List<MyCategoryModel>>() {
            @Override
            public void onResponse(Call<List<MyCategoryModel>> call, Response<List<MyCategoryModel>> response) {
                myCategoryModels = response.body();
                myCategoryNameAdapter = new MyCategoryNameAdapter(getApplicationContext(),myCategoryModels);
                myCatNameRecyclerView.setAdapter(myCategoryNameAdapter);
            }

            @Override
            public void onFailure(Call<List<MyCategoryModel>> call, Throwable t) {

            }
        });
    }

    public void deleteCategory(String id,String groupid,String userid)
    {
        Call<DeleteModel> req = ManagerAll.getInstance().deleteCategory(id,groupid,userid);
        req.enqueue(new Callback<DeleteModel>() {
            @Override
            public void onResponse(Call<DeleteModel> call, Response<DeleteModel> response) {

            }

            @Override
            public void onFailure(Call<DeleteModel> call, Throwable t) {

            }
        });
    }
}
