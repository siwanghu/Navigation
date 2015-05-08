package com.android.sources;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;

/**
* Sources类
* <p>全局资源管理(单例)<br>
* @author 胡思旺
* @version 2.0
* @date 2015-4-30
*/
public class Sources {
	private static Sources sources=new Sources();
	private List<Entry> enteys;
	
	/**
	* 构造函数
	* <p>阻止用户生成对象<br>
	* @date 2015-4-30
	*/
	private Sources(){ 
		enteys=new ArrayList<Entry>();
	}
	
	/**
	* getInstance方法
	* <p>获取实例<br>
	* @param 无参数
	* @return Sources
	* @date 2015-4-30
	*/
	public static Sources getInstance(){
		return sources;
	}
	
	/**
	* addEntry方法
	* <p>添加一个查询结果<br>
	* @param Entry entry
	* @return 无返回值
	* @date 2015-4-30
	*/
	public void addEntry(Entry entry){
		for(int i=0;i<enteys.size();i++){
			enteys.get(i).setFlags(false);
		}
		enteys.add(entry);
	}
	
	/**
	* findEntry方法
	* <p>从缓存中查询数据<br>
	* @param String beg,String end
	* @return boolean
	* @date 2015-4-30
	*/
	public boolean findEntry(String beg,String end){
		boolean flags = false;
		for(int i=0;i<enteys.size();i++){
			if(enteys.get(i).getBeg().equals(beg)&&enteys.get(i).getEnd().equals(end)){
				enteys.get(i).setFlags(true);
				flags=true;
			}
		}
		if(flags){
			enteys.get(enteys.size()-1).setFlags(false);
		}
		return flags;
	}
	
	/**
	* getNewEntry方法
	* <p>从缓存中获取最新的查询结果<br>
	* @param String beg,String end
	* @return Entry
	* @date 2015-4-30
	*/
	public Entry getNewEntry(){
		for(int i=0;i<enteys.size();i++){
			if(enteys.get(i).isFlags()){
				return enteys.get(i);
			}
		}
		return null;
	}
}
