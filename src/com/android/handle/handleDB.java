package com.android.handle;

import java.util.List;

import android.content.Context;

import com.android.bean.PointDB;
import com.android.db.DataBaseManager;
import com.android.tools.Convert;

/**
* handleDB类
* <p>读取数据库，获取数据<br>
* @author 胡思旺
* @version 2.0
* @date 2015-4-30
*/
public class handleDB {
	private DataBaseManager mgr;
	
	/**
	* 构造函数
	* <p>初始化DataBaseManager<br>
	* @date 2015-4-30
	*/
	public handleDB(Context context){
		mgr=new DataBaseManager(context);
		mgr.closeDB();
	}
	
	/**
	* getRoute方法
	* <p>获取路径<br>
	* @param String beg,String end
	* @return String[]
	* @date 2015-4-30
	*/
	public String[] getRoute(String beg,String end){
		if(Convert.parseInt(beg)>Convert.parseInt(end)){
			String temp=beg;
			beg=end;
			end=temp;
		}
		mgr.openDB();
		if(mgr.query_path(beg, end)==null){
			mgr.closeDB();
			return null;
		}
		String[] route=mgr.query_path(beg,end).trim().split("-");
		mgr.closeDB();
		for(int i=0;i<route.length;i++){
			route[i]=route[i].trim();
		}
		return route;
	}
	
	/**
	* getPointDB方法
	* <p>根据id获取位置信息<br>
	* @param String id
	* @return PointDB
	* @date 2015-4-30
	*/
	public PointDB getPointDB(String id){
		mgr.openDB();
		PointDB pointDB=mgr.query_point(id);
		mgr.closeDB();
		return pointDB;
	}
	
	/**
	* getPointDBs方法
	* <p>根据id获取位置信息<br>
	* @param List<String> ids
	* @return List<PointDB>
	* @date 2015-4-30
	*/
	public List<PointDB> getPointDBs(List<String> ids){
		mgr.openDB();
		List<PointDB> pointDBs=mgr.query_point(ids);
		mgr.closeDB();
		return pointDBs;
	}
	
	/**
	* getId方法
	* <p>根据位置名称获取位置id<br>
	* @param String place
	* @return String
	* @date 2015-4-30
	*/
	public String getId(String place){
		mgr.openDB();
		String id=mgr.query_relation_place(place);
		mgr.closeDB();
		return id;
	}
	
	/**
	* getIds方法
	* <p>根据位置名称获取位置id<br>
	* @param List<String> places
	* @return List<String>
	* @date 2015-4-30
	*/
	public List<String> getIds(List<String> places){
		mgr.openDB();
		List<String> ids=mgr.query_relation_place(places);
		mgr.closeDB();
		return ids;
	}
	
	/**
	* getIds方法
	* <p>根据位置id获取位置名称<br>
	* @param List<String> places
	* @return List<String>
	* @date 2015-4-30
	*/
	public String getPlace(String id){
		mgr.openDB();
		String place=mgr.query_relation_name(id);
		mgr.closeDB();
		return place;
	}
}
