package com.android.sources;

import java.util.ArrayList;
import java.util.List;

import com.android.bean.PointDB;

/**
* Entry类
* <p>封装一个查询结果<br>
* @author 胡思旺
* @version 2.0
* @date 2015-4-30
*/
public class Entry {
	private boolean flags;
	private String beg,beg_place;
	private String end,end_place;
	private List<PointDB> route_B1;
	private List<PointDB> route_F1;
	private List<PointDB> route_F2;
	
	/**
	* 构造函数
	* <p>初始化必要的数据<br>
	* @date 2015-4-30
	*/
	public Entry(){
		flags=true;
		beg=null;
		end=null;
		beg_place=null;
		end_place=null;
		route_B1=new ArrayList<PointDB>();
		route_F1=new ArrayList<PointDB>();
		route_F1=new ArrayList<PointDB>();
	}
	
	public boolean isFlags() {
		return flags;
	}

	public Entry setFlags(boolean flags) {
		this.flags = flags;
		return this;
	}

	public List<PointDB> getRoute_B1() {
		return route_B1;
	}

	public Entry setRoute_B1(List<PointDB> route_B1) {
		this.route_B1 = route_B1;
		return this;
	}

	public List<PointDB> getRoute_F1() {
		return route_F1;
	}

	public Entry setRoute_F1(List<PointDB> route_F1) {
		this.route_F1 = route_F1;
		return this;
	}

	public List<PointDB> getRoute_F2() {
		return route_F2;
	}

	public Entry setRoute_F2(List<PointDB> route_F2) {
		this.route_F2 = route_F2;
		return this;
	}

	public String getBeg() {
		return beg;
	}

	public Entry setBeg(String beg) {
		this.beg = beg;
		return this;
	}

	public String getBeg_place() {
		return beg_place;
	}

	public Entry setBeg_place(String beg_place) {
		this.beg_place = beg_place;
		return this;
	}

	public String getEnd() {
		return end;
	}

	public Entry setEnd(String end) {
		this.end = end;
		return this;
	}

	public String getEnd_place() {
		return end_place;
	}

	public Entry setEnd_place(String end_place) {
		this.end_place = end_place;
		return this;
	}
}
