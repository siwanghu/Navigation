package com.android.bean;

/**
* PathDB类
* <p>封装路径信息<br>
* @author 李化成
* @version 1.0
* @date 2015-4-30
*/
public class PathDB {
	private String _beg;
	private String _end;
	private String _path;
	
	public PathDB(){
		this._beg="";
		this._end="";
		this._path="";
	}
		
	
	public PathDB(String beg, String end){
		this._beg=beg;
		this._end=end;
		this._path="";
	}
	
	public PathDB(String beg, String end, String path){
		this._beg=beg;
		this._end=end;
		this._path=path;
	}
	
	public String getBeg(){
		return _beg;
	}
	public String getEnd(){
		return _end;
	}
	public String getPath(){
		return _path;
	}
}
