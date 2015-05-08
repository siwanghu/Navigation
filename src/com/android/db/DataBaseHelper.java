package com.android.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
* DataBaseHelper类
* <p>用于表的创建<br>
* @author 李化成
* @author 胡思旺
* @version 2.0
* @date 2015-4-30
*/
public class DataBaseHelper extends SQLiteOpenHelper {

	private static final String DB_NAME = "mydb.db";
	private static final int version = 1;

	public DataBaseHelper(Context context) {
		super(context, DB_NAME, null, version);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql_pointDB = "CREATE TABLE IF NOT EXISTS point"
				+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT, name CHAR(5), xPos INTEGER, yPos INTEGER, zPos INTEGER)";
		db.execSQL(sql_pointDB);

		String sql_path = "CREATE TABLE IF NOT EXISTS path"
				+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT, beg CHAR(5),	end CHAR(5), path VARCHAR)";
		db.execSQL(sql_path);

		String sql_relation = "CREATE TABLE IF NOT EXISTS relation"
				+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT, name CHAR(5), place VARCHAR)";
		db.execSQL(sql_relation);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// db.execSQL("ALTER TABLE pointDB ADD COLUMN ")
	}

}