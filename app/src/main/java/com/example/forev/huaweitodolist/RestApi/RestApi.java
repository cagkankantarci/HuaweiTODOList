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
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestApi {
    @FormUrlEncoded
    @POST("/todolistfolder/register.php")
    Call<RegisterModel> registerUser(@Field("email") String email, @Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("/todolistfolder/login.php")
    Call<LoginModel> loginUser(@Field("email") String email, @Field("password") String username);

    @FormUrlEncoded
    @POST("/todolistfolder/all_list.php")
    Call<List<AllListModel>> getAllList(@Field("userid") String userid);

    @FormUrlEncoded
    @POST("/todolistfolder/addlist.php")
    Call<AddListModel> addList(@Field("userid") String userid, @Field("listname") String listname,
                               @Field("listcategory") String listcategory, @Field("listdesc") String listdesc,
                               @Field("listdeadline") String listdeadline,@Field("createdate") String createdate,
                               @Field("groupid") String groupid);

    @FormUrlEncoded
    @POST("/todolistfolder/addcategory.php")
    Call<AddListModel> addCategory(@Field("mainlistname") String mainlistname,@Field("userid") String userid,
                                   @Field("groupid") String groupid);

    @FormUrlEncoded
    @POST("/todolistfolder/filterstatus.php")
    Call<List<AllListModel>> filterList(@Field("userid") String userid, @Field("status") String status);

    @FormUrlEncoded
    @POST("/todolistfolder/filterstatusforcategory.php")
    Call<List<MainCategoryModel>> filterListCat(@Field("userid") String userid, @Field("status") String status,
                                                @Field("listcategory") String listcategory);

    @FormUrlEncoded
    @POST("/todolistfolder/listbyname.php")
    Call<List<AllListModel>> getListbyName(@Field("userid") String userid);

    @FormUrlEncoded
    @POST("/todolistfolder/listbynameforcategory.php")
    Call<List<MainCategoryModel>> getListbyNameForCategory(@Field("listcategory") String listcategory,@Field("userid") String userid);

    @FormUrlEncoded
    @POST("/todolistfolder/listbystatus.php")
    Call<List<AllListModel>> getListbyStatus(@Field("userid") String userid);

    @FormUrlEncoded
    @POST("/todolistfolder/listbystatusforcategory.php")
    Call<List<MainCategoryModel>> getListbyStatusForCategory(@Field("listcategory") String listcategory,@Field("userid") String userid);

    @FormUrlEncoded
    @POST("/todolistfolder/deleteitem.php")
    Call<DeleteModel> deleteItem(@Field("groupid") String groupid);

    @FormUrlEncoded
    @POST("/todolistfolder/deletecategoryitem.php")
    Call<DeleteModel> deleteCategoryItem(@Field("groupid") String groupid,@Field("listcategory") String listcategory);

    @FormUrlEncoded
    @POST("/todolistfolder/updateitem.php")
    Call<UpdateModel> updateItem(@Field("groupid") String groupid,@Field("status") String status);

    @FormUrlEncoded
    @POST("/todolistfolder/updateitem.php")
    Call<UpdateModel> updateItemCat(@Field("id") String id,@Field("status") String status);

    @FormUrlEncoded
    @POST("/todolistfolder/listmaincategory.php")
    Call<List<MainCategoryModel>> listMainCategory(@Field("id") String id,@Field("userid") String userid);

    @GET("/todolistfolder/listcategorynames.php")
    Call<List<CategoryNameModel>> getCategoryName();

    @FormUrlEncoded
    @POST("/todolistfolder/listmycategory.php")
    Call<List<MyCategoryModel>> getMyCategoryName(@Field("userid") String userid);

    @FormUrlEncoded
    @POST("/todolistfolder/deletecategory.php")
    Call<DeleteModel> deleteCategory(@Field("id") String id,@Field("groupid") String groupid,@Field("userid") String userid);
}
