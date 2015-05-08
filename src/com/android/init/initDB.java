package com.android.init;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

import com.android.bean.PathDB;
import com.android.bean.PointDB;
import com.android.bean.RelationDB;
import com.android.db.DataBaseManager;
import com.husiwang.myapp.R;

/**
* initDB类
* <p>初始化数据库，程序加载时必须初始化<br>
* @author 李化成
* @author 胡思旺
* @version 2.0
* @date 2015-4-30
*/
public class initDB {
	private DataBaseManager mgr;
	private Context context;
	private static final int PATH_COUNT=49455;
	private static final int POINT_COUNT=315;
	private static final int RELATION_COUNT=250;
	
	/**
	* 构造函数
	* <p>初始化DataBaseManager<br>
	* @date 2015-4-30
	*/
	public initDB(Context context){
		this.context=context;
		mgr=new DataBaseManager(context);
		mgr.closeDB();
		loadData();
	}
	
	/**
	* ReadPathFromRaw方法
	* <p>读取path文件<br>
	* @param int fileInRaw
	* @return String[]
	* @date 2015-4-30
	*/
	public String[] ReadPathFromRaw(int fileInRaw){
		BufferedReader br=null;
		String[] ret=new String[PATH_COUNT];
		int i=0;
		String temp="";
		try{
			InputStream inputStream=context.getResources().openRawResource(fileInRaw);
			br=new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
		}catch(UnsupportedEncodingException e1){
			Log.e("debug",e1.toString());
		}
		try{
			while((temp=br.readLine())!=null){
				ret[i]=temp;
				i++;
			}
		}catch(IOException e){
			Log.e("db-debug", e.toString());
		}
		return ret;
	}
	
	/**
	* ReadPointFromRaw方法
	* <p>读取point文件<br>
	* @param int fileInRaw
	* @return String[]
	* @date 2015-4-30
	*/
	public String[] ReadPointFromRaw(int fileInRaw){
		String[] ret=new String[POINT_COUNT];
		BufferedReader br=null;
		try{
			InputStream inputStream=context.getResources().openRawResource(fileInRaw);
			br=new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
		}catch(UnsupportedEncodingException e1){
			Log.e("db-debug",e1.toString());
		}
		for(int i=0;i<POINT_COUNT;i++){
			try {
				ret[i]=br.readLine().trim();
			} catch (IOException e) {
				Log.e("db-debug", e.toString());
			}
		}
		return ret;
	}
	
	/**
	* ReadRelationFormRaw方法
	* <p>读取relation文件<br>
	* @param int fileInRaw
	* @return String[]
	* @date 2015-4-30
	*/
	public String[] ReadRelationFormRaw(int fileInRaw){
		String[] ret=new String[RELATION_COUNT];
		BufferedReader br=null;
		try{
			InputStream inputStream=context.getResources().openRawResource(fileInRaw);
			br=new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
		}catch(UnsupportedEncodingException e1){
			Log.e("debug",e1.toString());
		}
		for(int i=0;i<RELATION_COUNT;i++){
			try {
				ret[i]=br.readLine().trim();
			} catch (IOException e) {
				Log.e("debug",e.toString());
			}
		}
		return ret;
	}
	
	/**
	* loadData方法
	* <p>加载数据到数据库中<br>
	* @param 无参数
	* @return 无返回值
	* @date 2015-4-30
	*/
	public void loadData(){
    	mgr.openDB();
		ArrayList<PathDB> PathDBs=new ArrayList<PathDB>();
		ArrayList<PointDB> PointDBs=new ArrayList<PointDB>();
		ArrayList<RelationDB> RelationDBs=new ArrayList<RelationDB>();
		String[] infoStrings_path=ReadPathFromRaw(R.raw.path),temp;
		String[] infoStrings_point=ReadPointFromRaw(R.raw.point);
		String[] infoStrings_relation=ReadRelationFormRaw(R.raw.relation);
		for(int i=0;i<infoStrings_path.length;i++){
			temp=infoStrings_path[i].split("-");
			PathDB pathDB=new PathDB(temp[1],temp[temp.length-1], infoStrings_path[i]);
			PathDBs.add(pathDB);
		}
		for(int j=0;j<infoStrings_point.length;j++){
			temp=infoStrings_point[j].split("-");
			PointDB pointDB=new PointDB(temp[0],Integer.parseInt(temp[1]),Integer.parseInt(temp[2]),Integer.parseInt(temp[3]));
			PointDBs.add(pointDB);
		}
		for(int k=0;k<infoStrings_relation.length;k++){
			temp=infoStrings_relation[k].split("-");
			RelationDB relationDB=new RelationDB(temp[0],temp[temp.length-1]);
			RelationDBs.add(relationDB);
		}
		mgr.add(PointDBs, PathDBs, RelationDBs);
		infoStrings_path=null;
		infoStrings_point=null;
		infoStrings_relation=null;
		System.gc();
		mgr.closeDB();
	}
}
