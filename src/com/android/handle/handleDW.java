package com.android.handle;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.android.sources.Entry;
import com.android.sources.Sources;
import com.android.tools.Floor;

/**
* handleDW类
* <p>画图工具类<br>
* @author 胡思旺
* @version 2.0
* @date 2015-5-1
*/
public class handleDW {
	private Paint paint;
	private Entry entry;
	
	/**
	* 构造函数
	* <p>初始化Paint<br>
	* @date 2015-5-1
	*/
	public handleDW(){
		paint=new Paint();
		paint.setColor(Color.RED);
		paint.setStrokeWidth(5);
	}
	
	/**
	* drawPicture方法
	* <p>画图函数<br>
	* @param Canvas canvas,Floor floor,float optsX,float optsY
	* @return boolean
	* @date 2015-5-1
	*/
	public boolean drawPicture(Canvas canvas,Floor floor,float optsX,float optsY){
		entry=Sources.getInstance().getNewEntry();
		if(entry==null)
			return false;
		else{
			switch (floor) {
			case B1:
				return drawPicture_B1(canvas,optsX,optsY);
			case F1:
				return drawPicture_F1(canvas,optsX,optsY);
			case F2:
				return drawPicture_F2(canvas,optsX,optsY);
			}
			return false;
		}
	}
	
	/**
	* drawPicture_B1方法
	* <p>画图函数(地下一楼)<br>
	* @param Canvas canvas,float optsX,float optsY
	* @return boolean
	* @date 2015-5-1
	*/
	private boolean drawPicture_B1(Canvas canvas,float optsX,float optsY){
		for (int i = 0; i < entry.getRoute_B1().size() - 1; i++) {
			canvas.drawLine((float) entry.getRoute_B1().get(i).getX() / optsX,
					(float) entry.getRoute_B1().get(i).getY() / optsY,
					(float) entry.getRoute_B1().get(i + 1).getX() / optsX,
					(float) entry.getRoute_B1().get(i + 1).getY() / optsY, paint);
		}
		return true;
	}
	
	/**
	* drawPicture_F1方法
	* <p>画图函数(一楼)<br>
	* @param Canvas canvas,float optsX,float optsY
	* @return boolean
	* @date 2015-5-1
	*/
	private boolean drawPicture_F1(Canvas canvas,float optsX,float optsY){
		for (int i = 0; i < entry.getRoute_F1().size() - 1; i++) {
			canvas.drawLine((float) entry.getRoute_F1().get(i).getX() / optsX,
					(float) entry.getRoute_F1().get(i).getY() / optsY,
					(float) entry.getRoute_F1().get(i + 1).getX() / optsX,
					(float) entry.getRoute_F1().get(i + 1).getY() / optsY, paint);
		}
		return true;
	}
	
	/**
	* drawPicture_F2方法
	* <p>画图函数(二楼)<br>
	* @param Canvas canvas,float optsX,float optsY
	* @return boolean
	* @date 2015-5-1
	*/
	private boolean drawPicture_F2(Canvas canvas,float optsX,float optsY){
		for (int i = 0; i < entry.getRoute_F2().size() - 1; i++) {
			canvas.drawLine((float) entry.getRoute_B1().get(i).getX() / optsX,
					(float) entry.getRoute_F2().get(i).getY() / optsY,
					(float) entry.getRoute_F2().get(i + 1).getX() / optsX,
					(float) entry.getRoute_F2().get(i + 1).getY() / optsY, paint);
		}
		return true;
	}
	
	/**
	* setPaint方法
	* <p>设置画笔<br>
	* @param Paint paint
	* @return 无返回值
	* @date 2015-5-1
	*/
	public void setPaint(Paint paint){
		this.paint=paint;
	}
	
	/**
	* setPaintColor方法
	* <p>设置画笔颜色<br>
	* @param int color
	* @return 无返回值
	* @date 2015-5-1
	*/
	public void setPaintColor(int color){
		paint.setColor(color);
	}
	
	/**
	* setPaintWidth方法
	* <p>设置画笔宽度<br>
	* @param int width
	* @return 无返回值
	* @date 2015-5-1
	*/
	public void setPaintWidth(int width){
		paint.setStrokeWidth(width);
	}
}
