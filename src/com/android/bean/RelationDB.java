package com.android.bean;

/**
* RelationDB类
* <p>封装关系信息<br>
* @author 李化成
* @version 1.0
* @date 2015-4-30
*/
public class RelationDB {
	private String _name;
	private String _place;
	
	public RelationDB(){
		this._name="";
		this._place="";
	}
	
	public RelationDB(String name, String place){
		this._name=name;
		this._place=place;
	}
	
	public String getName(){
		return _name;
	}
	
	public String getPlace(){
		return _place;
	}
}
