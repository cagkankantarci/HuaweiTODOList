package com.example.forev.huaweitodolist.Models;

public class AllListModel{
	private String listdeadline;
	private String listdesc;
	private String groupid;
	private String listname;
	private String createdate;
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

	public void setGroupid(String groupid){
		this.groupid = groupid;
	}

	public String getGroupid(){
		return groupid;
	}

	public void setListname(String listname){
		this.listname = listname;
	}

	public String getListname(){
		return listname;
	}

	public void setCreatedate(String createdate){
		this.createdate = createdate;
	}

	public String getCreatedate(){
		return createdate;
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
			"AllListModel{" + 
			"listdeadline = '" + listdeadline + '\'' + 
			",listdesc = '" + listdesc + '\'' + 
			",groupid = '" + groupid + '\'' + 
			",listname = '" + listname + '\'' + 
			",createdate = '" + createdate + '\'' + 
			",id = '" + id + '\'' + 
			",userid = '" + userid + '\'' + 
			",listcategory = '" + listcategory + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
