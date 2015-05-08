package com.android.navi;

import java.util.ArrayList;
import java.util.List;

import com.android.bean.PointDB;
import com.android.sources.Entry;
import com.android.sources.Sources;

/**
* Route类
* <p>导航组件中负责生成路径类<br>
* @author 胡思旺
* @version 2.0
* @date 2015-5-3
*/
public class Route {
	private Entry entry;
	private List<PointDB> list_B1;
	private List<PointDB> list_F1;
	private List<PointDB> list_F2;
	private int speed;
	
	/**
	* 构造函数
	* <p>初始化相关变量<br>
	* @date 2015-5-6
	*/
	public Route(){
		speed=2;
		entry=Sources.getInstance().getNewEntry();
	}
	
	/**
	* updateEntry方法
	* <p>更新结果集<br>
	* @param 无参数
	* @return 无返回值
	* @date 2015-5-6
	*/
	public void updateEntry(){
		entry=Sources.getInstance().getNewEntry();
	}
	
	/**
	* updateSpeed方法
	* <p>更新速度<br>
	* @param int speed
	* @return 无返回值
	* @date 2015-5-6
	*/
	public void updateSpeed(int speed){
		this.speed=speed;
		initRoute();
	}
	
	/**
	* getRouteB1方法
	* <p>获取切割后的路径B1<br>
	* @param 无参数
	* @return 无返回值
	* @date 2015-5-6
	*/
	public List<PointDB> getRouteB1(){
		return list_B1;
	}
	
	/**
	* getRouteF1方法
	* <p>获取切割后的路径F1<br>
	* @param 无参数
	* @return 无返回值
	* @date 2015-5-6
	*/
	public List<PointDB> getRouteF1(){
		return list_F1;
	}
	
	/**
	* getRouteF2方法
	* <p>获取切割后的路径F2<br>
	* @param 无参数
	* @return 无返回值
	* @date 2015-5-6
	*/
	public List<PointDB> getRouteF2(){
		return list_F2;
	}
	
	/**
	* initRoute方法
	* <p>初始化路径顶点集<br>
	* @param 无参数
	* @return 无返回值
	* @date 2015-5-6
	*/
	private void initRoute(){
		list_B1=initList(entry.getRoute_B1());
		list_F1=initList(entry.getRoute_F1());
		list_F2=initList(entry.getRoute_F2());
	}
	
	/**
	* initList方法
	* <p>对路径进行切割依照指定的速度切割<br>
	* 采取y=k*x+b的方程进行切割<br>
	* @param List<PointDB> list
	* @return List<PointDB>
	* @date 2015-5-6
	*/
	private List<PointDB> initList(List<PointDB> list) {
		List<PointDB> route = new ArrayList<PointDB>();
		if (list != null) {
			for (int i = 0; i < list.size() - 1; i++) {
				float k = ((float) list.get(i + 1).getY() - (float) list.get(i)
						.getY())
						/ ((float) list.get(i + 1).getX() - (float) list.get(i)
								.getX());
				float b = (((float) list.get(i + 1).getX())
						* ((float) list.get(i).getY()) - ((float) list.get(i)
						.getX()) * ((float) list.get(i + 1).getY()))
						/ ((float) list.get(i + 1).getX() - (float) list.get(i)
								.getX());
				float increments = ((float) list.get(i + 1).getX() - (float) list
						.get(i).getX()) / (float) speed;
				route.add(new PointDB(list.get(i).getX(), list.get(i).getY(), list.get(i).getZ()));
				for (int j = 1; j < speed; j++) {
					route.add(new PointDB((int)(list.get(i).getX()+j*increments),
							(int)(k*(list.get(i).getX() + j * increments)+b),list.get(i).getZ()));
				}
			}
			route.add(new PointDB(list.get(list.size()-1).getX(),list.get(list.size()-1).getY()
					,list.get(list.size()-1).getZ()));
		}
		return route;
	}
}
