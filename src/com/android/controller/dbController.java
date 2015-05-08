package com.android.controller;

import java.util.ArrayList;

import android.content.Context;

import com.android.handle.handleDB;
import com.android.sources.Entry;
import com.android.sources.Sources;
import com.android.tools.Convert;

/**
* dbController类
* <p>数据控制器<br>
* @author 胡思旺
* @version 2.0
* @date 2015-4-30
*/
public class dbController {
	private handleDB handle;
	
	/**
	* 构造函数
	* <p>初始化handleDB<br>
	* @date 2015-4-30
	*/
	public dbController(Context context){
		handle=new handleDB(context);
	}
	
	/**
	* updataSource方法
	* <p>更新source<br>
	* @param String beg,String end
	* @return boolean
	* @date 2015-4-30
	*/
	public boolean updataSource(String beg,String end){
		if(!Sources.getInstance().findEntry(beg, end)){
			return initEntey(handle.getRoute(beg, end),beg,end);
		}
		return true;
	}
	
	/**
	* initEntey方法
	* <p>初始化entry<br>
	* @param String[] route,String beg,String end
	* @return boolean
	* @date 2015-4-30
	*/
	private boolean initEntey(String[] route,String beg,String end){
		if(route==null)
			return false;
		else{
			ArrayList<String> route_B1=new ArrayList<String>();
		    ArrayList<String> route_F1=new ArrayList<String>();
		    ArrayList<String> route_F2=new ArrayList<String>();
		    for(int i=1;i<route.length;i++){
				if(Convert.parseInt(route[i])>=0&&Convert.parseInt(route[i])<=9){
					route_B1.add(route[i]);
				}
				if(Convert.parseInt(route[i])>=10&&Convert.parseInt(route[i])<=167){
					route_F1.add(route[i]);
				}
				if(Convert.parseInt(route[i])>=168&&Convert.parseInt(route[i])<=314){
					route_F2.add(route[i]);
				}
			}
			Entry entry=new Entry();
			entry.setFlags(true)
				 .setBeg(beg)
				 .setEnd(end)
				 .setBeg_place(handle.getPlace(beg))
				 .setEnd_place(handle.getPlace(beg))
				 .setRoute_B1(handle.getPointDBs(route_B1))
				 .setRoute_F1(handle.getPointDBs(route_F1))
				 .setRoute_F2(handle.getPointDBs(route_F2));
			Sources.getInstance().addEntry(entry);
			return true;
		}
	}
}
