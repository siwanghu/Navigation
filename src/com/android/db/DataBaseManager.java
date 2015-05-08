package com.android.db;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.bean.PathDB;
import com.android.bean.PointDB;
import com.android.bean.RelationDB;

/**
* DataBaseManager类
* <p>对表的基本操作<br>
* @author 李化成
* @author 胡思旺
* @version 2.0
* @date 2015-4-30
*/
public class DataBaseManager {
	private DataBaseHelper helper;
	private SQLiteDatabase db;

	/**
	* 构造函数
	* <p>初始化DataBaseHelper和SQLiteDatabase<br>
	* @date 2015-4-30
	*/
	public DataBaseManager(Context context) {
		helper = new DataBaseHelper(context);
		db = helper.getWritableDatabase();
	}
	
	/**
	* add方法
	* <p>向表中添加元素<br>
	* @param ArrayList<PointDB> pointDBs, ArrayList<PathDB> pathDBs,
			ArrayList<RelationDB> relationDBs
	* @return 没有返回值
	* @date 2015-4-30
	*/
	public void add(ArrayList<PointDB> pointDBs, ArrayList<PathDB> pathDBs,
			ArrayList<RelationDB> relationDBs) {
		db.beginTransaction();
		try {
			for (PointDB pointDB : pointDBs) {
				db.execSQL("INSERT INTO point VALUES(null,?,?,?,?)",
						new Object[] { pointDB.getName(), pointDB.getX(),
								pointDB.getY(), pointDB.getZ() });
			}
			for (PathDB pathDB : pathDBs) {
				db.execSQL("INSERT INTO path VALUES(null,?,?,?)", new Object[] {
						pathDB.getBeg(), pathDB.getEnd(), pathDB.getPath() });
			}
			for (RelationDB relationDB : relationDBs) {
				db.execSQL(
						"INSERT INTO relation VALUES(null,?,?)",
						new Object[] { relationDB.getName(),
								relationDB.getPlace() });
			}
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}
	}
	
	/**
	* addPath方法
	* <p>向path表中添加元素<br>
	* @param ArrayList<PathDB> pathDBs
	* @return 没有返回值
	* @date 2015-4-30
	*/
	public void addPath(ArrayList<PathDB> pathDBs) {
		db.beginTransaction();
		try {
			for (PathDB pathDB : pathDBs) {
				db.execSQL("INSERT INTO path VALUES(null,?,?,?)", new Object[] {
						pathDB.getBeg(), pathDB.getEnd(), pathDB.getPath() });
			}
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}
	}

	/**
	* addPoint方法
	* <p>向point表中添加元素<br>
	* @param ArrayList<PointDB> pointDBs
	* @return 没有返回值
	* @date 2015-4-30
	*/
	public void addPoint(ArrayList<PointDB> pointDBs) {
		db.beginTransaction();
		try {
			for (PointDB pointDB : pointDBs) {
				db.execSQL("INSERT INTO point VALUES(null,?,?,?,?)",
						new Object[] { pointDB.getName(), pointDB.getX(),
								pointDB.getY(), pointDB.getZ() });
			}
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}
	}
	
	/**
	* addRelation方法
	* <p>向relation表中添加元素<br>
	* @param ArrayList<RelationDB> relationDBs
	* @return 没有返回值
	* @date 2015-4-30
	*/
	public void addRelation(ArrayList<RelationDB> relationDBs) {
		db.beginTransaction();
		try {
			for (RelationDB relationDB : relationDBs) {
				db.execSQL(
						"INSERT INTO relation VALUES(null,?,?)",
						new Object[] { relationDB.getName(),
								relationDB.getPlace() });
			}
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}
	}
	
	/**
	* query_point方法
	* <p>根据位置id查询位置信息<br>
	* @param String id
	* @return PointDB
	* @date 2015-4-30
	*/
	public PointDB query_point(String id){
		Cursor c=queryTheCursor_point(id);
		PointDB pointDB=null;
		if(c.moveToFirst()){
			pointDB = new PointDB(
					c.getString(c.getColumnIndex("name")), c.getInt(c
							.getColumnIndex("xPos")), c.getInt(c
							.getColumnIndex("yPos")), c.getInt(c
							.getColumnIndex("zPos")));
		}
		return pointDB;
	}

	/**
	* query_point方法
	* <p>根据位置id查询位置信息<br>
	* @param List<String> list
	* @return List<PointDB>
	* @date 2015-4-30
	*/
	public List<PointDB> query_point(List<String> list) {
		ArrayList<PointDB> pointDBs = new ArrayList<PointDB>();
		Cursor c;
		for (int i = 0; i < list.size(); i++) {
			c = queryTheCursor_point(list.get(i));
			if(c.moveToFirst()){
				PointDB pointDB = new PointDB(
						c.getString(c.getColumnIndex("name")), c.getInt(c
								.getColumnIndex("xPos")), c.getInt(c
								.getColumnIndex("yPos")), c.getInt(c
								.getColumnIndex("zPos")));
				pointDBs.add(pointDB);
			}
		}
		return pointDBs;
	}

	/**
	* query_path方法
	* <p>根据起点和终点查询路径<br>
	* @param String beg, String end
	* @return String　route
	* @date 2015-4-30
	*/
	public String query_path(String beg, String end) {
		Cursor c = queryTheCursor_path(beg, end);
		if (c.moveToFirst())
			return c.getString(c.getColumnIndex("path"));
		return null;
	}

	/**
	* query_relation方法
	* <p>根据位置名称查询位置id<br>
	* @param String place
	* @return String　id
	* @date 2015-4-30
	*/
	public String query_relation_place(String place) {
		Cursor c = queryTheCursor_relation_place(place);
		if(c.moveToFirst())
			return c.getString(c.getColumnIndex("name"));
		return null;
	}
	
	/**
	* query_relation方法
	* <p>根据位置名称查询位置id<br>
	* @param List<String> places
	* @return List<String>
	* @date 2015-4-30
	*/
	public List<String> query_relation_place(List<String> places){
		Cursor c;
		List<String> ids = new ArrayList<String>();
		for(int i=0;i<places.size();i++){
			c=queryTheCursor_relation_place(places.get(i));
			c.moveToFirst();
			ids.add(c.getString(c.getColumnIndex("name")));
		}
		return ids;
	}
	
	/**
	* query_relation方法
	* <p>根据位置id查询位置名称<br>
	* @param String place
	* @return String　id
	* @date 2015-4-30
	*/
	public String query_relation_name(String name){
		Cursor c=queryTheCursor_relation_name(name);
		if(c.moveToFirst())
			return c.getString(c.getColumnIndex("place"));
		return null;
	}

	/**
	* closeDB方法
	* <p>关闭数据库<br>
	* @param 无参数
	* @return 无返回值
	* @date 2015-4-30
	*/
	public void closeDB() {
		db.close();
	}

	/**
	* openDB方法
	* <p>打开数据库<br>
	* @param 无参数
	* @return 无返回值
	* @date 2015-4-30
	*/
	public void openDB() {
		db = helper.getWritableDatabase();
	}
	
	/**
	* queryTheCursor_point方法
	* <p>移动游标到指定位置<br>
	* @param String name
	* @return Cursor
	* @date 2015-4-30
	*/
	public Cursor queryTheCursor_point(String name) {
		Cursor c = db.rawQuery("SELECT * FROM point WHERE name=" + name, null);
		return c;
	}

	/**
	* queryTheCursor_path方法
	* <p>移动游标到指定位置<br>
	* @param String beg, String end
	* @return Cursor
	* @date 2015-4-30
	*/
	public Cursor queryTheCursor_path(String beg, String end) {
		Cursor c = db.rawQuery("SELECT * FROM path WHERE beg=" + beg + " "
				+ "AND end=" + end, null);
		return c;
	}

	/**
	* queryTheCursor_relation方法
	* <p>移动游标到指定位置<br>
	* @param String place
	* @return Cursor
	* @date 2015-4-30
	*/
	public Cursor queryTheCursor_relation_place(String place) {
		Cursor c = db.rawQuery("SELECT * FROM relation WHERE place=" + place,
				null);
		return c;
	}
	
	/**
	* queryTheCursor_relation方法
	* <p>移动游标到指定位置<br>
	* @param String place
	* @return Cursor
	* @date 2015-4-30
	*/
	public Cursor queryTheCursor_relation_name(String name) {
		Cursor c = db.rawQuery("SELECT * FROM relation WHERE name=" + name,
				null);
		return c;
	}

}