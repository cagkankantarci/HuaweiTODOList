package com.example.forev.huaweitodolist.Models;

public class MyCategoryModel{
	private String mainlistname;
	private String groupid;
	private String id;
	private String categoryid;

	public void setMainlistname(String mainlistname){
		this.mainlistname = mainlistname;
	}

	public String getMainlistname(){
		return mainlistname;
	}

	public void setGroupid(String groupid){
		this.groupid = groupid;
	}

	public String getGroupid(){
		return groupid;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setCategoryid(String categoryid){
		this.categoryid = categoryid;
	}

	public String getCategoryid(){
		return categoryid;
	}

	@Override
 	public String toString(){
		return 
			"MyCategoryModel{" + 
			"mainlistname = '" + mainlistname + '\'' + 
			",groupid = '" + groupid + '\'' + 
			",id = '" + id + '\'' + 
			",categoryid = '" + categoryid + '\'' + 
			"}";
		}
}
