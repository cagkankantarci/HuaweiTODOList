package com.example.forev.huaweitodolist.Models;

public class CategoryNameModel{
	private String mainlistname;
	private String id;

	public void setMainlistname(String mainlistname){
		this.mainlistname = mainlistname;
	}

	public String getMainlistname(){
		return mainlistname;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"CategoryNameModel{" + 
			"mainlistname = '" + mainlistname + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}
