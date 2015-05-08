package com.android.bean;

/**
* PointDB类
* <p>封装位置信息<br>
* @author 李化成
* @version 1.0
* @date 2015-4-30
*/
public class PointDB{
    private String _name;
	private int _x;
	private int _y;
	private int _z;
	
	public PointDB(){
		this._name="F1_000";
		this._x=this._y=this._z=0;
	}
	
	public PointDB(int x, int y, int z){
	    this._x=x;
		this._y=y;
		this._z=z;
	}
	
	public PointDB(String name, int x, int y, int z){
	    this._name=name;
		this._x=x;
		this._y=y;
		this._z=z;
	}
	
	public String getName(){
	    return _name;
	}
	
	public int getX(){
	    return _x;
	}
	
	public int getY(){
	    return _y;
	}
	public int getZ(){
	    return _z;
	}
}