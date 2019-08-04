package com.example.forev.huaweitodolist.RestApi;

import com.example.forev.huaweitodolist.Models.AddListModel;
import com.example.forev.huaweitodolist.Models.AllListModel;
import com.example.forev.huaweitodolist.Models.CategoryNameModel;
import com.example.forev.huaweitodolist.Models.DeleteModel;
import com.example.forev.huaweitodolist.Models.LoginModel;
import com.example.forev.huaweitodolist.Models.MainCategoryModel;
import com.example.forev.huaweitodolist.Models.MyCategoryModel;
import com.example.forev.huaweitodolist.Models.RegisterModel;
import com.example.forev.huaweitodolist.Models.UpdateModel;

import java.util.List;

import retrofit2.Call;

public class ManagerAll extends BaseManager {

    private  static ManagerAll ourInstance = new ManagerAll();

    public  static synchronized ManagerAll getInstance()
    {
        return  ourInstance;
    }

    public Call<RegisterModel> registerUser(String email , String username, String password)
    {
        Call<RegisterModel> x = getRestApi().registerUser(email,username,password);
        return  x ;
    }

    public Call<LoginModel> loginUser(String email , String password)
    {
        Call<LoginModel> x = getRestApi().loginUser(email,password);
        return  x ;
    }

    public Call<List<AllListModel>> getAllList(String userid)
    {
        Call<List<AllListModel>> x = getRestApi().getAllList(userid);
        return  x ;
    }

    public Call<AddListModel> addList(String userid, String listname, String listcategory, String listdesc,String listdeadline,String createdate,String groupid)
    {
        Call<AddListModel> x = getRestApi().addList(userid,listname,listcategory,listdesc,listdeadline,createdate,groupid);
        return  x ;
    }

    public Call<AddListModel> addCategory(String mainlistname,String userid,String groupid)
    {
        Call<AddListModel> x = getRestApi().addCategory(mainlistname,userid,groupid);
        return  x ;
    }

    public Call<List<AllListModel>> filterList(String userid, String status)
    {
        Call<List<AllListModel>> x = getRestApi().filterList(userid,status);
        return  x ;
    }

    public Call<List<MainCategoryModel>> filterListCat(String userid, String status, String listcategory)
    {
        Call<List<MainCategoryModel>> x = getRestApi().filterListCat(userid,status,listcategory);
        return  x ;
    }

    public Call<List<AllListModel>> getListbyName(String userid)
    {
        Call<List<AllListModel>> x = getRestApi().getListbyName(userid);
        return  x ;
    }

    public Call<List<MainCategoryModel>> getListbyNameForCategory(String listcategory,String userid)
    {
        Call<List<MainCategoryModel>> x = getRestApi().getListbyNameForCategory(listcategory,userid);
        return  x ;
    }

    public Call<List<AllListModel>> getListbyStatus(String userid)
    {
        Call<List<AllListModel>> x = getRestApi().getListbyStatus(userid);
        return  x ;
    }

    public Call<List<MainCategoryModel>> getListbyStatusForCategory(String listcategory,String userid)
    {
        Call<List<MainCategoryModel>> x = getRestApi().getListbyStatusForCategory(listcategory,userid);
        return  x ;
    }

    public Call<DeleteModel> deleteItem(String groupid)
    {
        Call<DeleteModel> x = getRestApi().deleteItem(groupid);
        return  x ;
    }

    public Call<DeleteModel> deleteCategoryItem(String groupid,String listcategory)
    {
        Call<DeleteModel> x = getRestApi().deleteCategoryItem(groupid,listcategory);
        return  x ;
    }

    public Call<UpdateModel> updateItem(String groupid,String status)
    {
        Call<UpdateModel> x = getRestApi().updateItem(groupid,status);
        return  x ;
    }

    public Call<UpdateModel> updateItemCat(String id,String status)
    {
        Call<UpdateModel> x = getRestApi().updateItemCat(id,status);
        return  x ;
    }

    public Call<List<MainCategoryModel>> listMainCategory(String id,String userid)
    {
        Call<List<MainCategoryModel>> x = getRestApi().listMainCategory(id,userid);
        return  x ;
    }

    public Call<List<CategoryNameModel>> getCategoryName()
    {
        Call<List<CategoryNameModel>> x = getRestApi().getCategoryName();
        return  x ;
    }

    public Call<List<MyCategoryModel>> getMyCategoryName(String userid)
    {
        Call<List<MyCategoryModel>> x = getRestApi().getMyCategoryName(userid);
        return  x ;
    }

    public Call<DeleteModel> deleteCategory(String id,String groupid,String userid)
    {
        Call<DeleteModel> x = getRestApi().deleteCategory(id,groupid,userid);
        return  x ;
    }
}
