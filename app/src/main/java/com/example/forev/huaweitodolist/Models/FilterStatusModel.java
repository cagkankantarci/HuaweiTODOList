package com.example.forev.huaweitodolist.Models;

public class FilterStatusModel{
	private String listdeadline;
	private String listdesc;
	private String listname;
	private String id;
	private String userid;
	private String listcategory;
	private String status;

	public void setListdeadline(String listdeadline){
		this.listdeadline = listdeadline;
	}

	public String getListdeadline(){
		return listdeadline;
	}

	public void setListdesc(String listdesc){
		this.listdesc = listdesc;
	}

	public String getListdesc(){
		return listdesc;
	}

	public void setListname(String listname){
		this.listname = listname;
	}

	public String getListname(){
		return listname;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setUserid(String userid){
		this.userid = userid;
	}

	public String getUserid(){
		return userid;
	}

	public void setListcategory(String listcategory){
		this.listcategory = listcategory;
	}

	public String getListcategory(){
		return listcategory;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"FilterStatusModel{" + 
			"listdeadline = '" + listdeadline + '\'' + 
			",listdesc = '" + listdesc + '\'' + 
			",listname = '" + listname + '\'' + 
			",id = '" + id + '\'' + 
			",userid = '" + userid + '\'' + 
			",listcategory = '" + listcategory + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
