package com.android.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.android.handle.handleDW;
import com.android.tools.Floor;
import com.husiwang.myapp.R;

/**
* dbController类
* <p>画图控制器<br>
* @author 胡思旺
* @version 2.0
* @date 2015-5-1
*/
public class dwController {
	private handleDW handle;
	private Bitmap bitmap;
	private Context context;
	private BitmapFactory.Options opts;
	
	/**
	* 构造函数
	* <p>初始化handleDW,Context,BitmapFactory.Options<br>
	* @date 2015-5-1
	*/
	public dwController(Context context){
		bitmap=null;
		this.context=context;
		handle=new handleDW();
		opts = new BitmapFactory.Options();
		opts.inSampleSize=2;
		opts.inJustDecodeBounds = false;
	}
	
	/**
	* drawPicture方法
	* <p>画图函数<br>
	* @param Floor floor
	* @return boolean
	* @date 2015-5-1
	*/
	public boolean drawPicture(Floor floor){
		recycleBitmap();
		switch(floor){
		case B1:
			return drawPicture_B1();
		case F1:
			return drawPicture_F1();
		case F2:
			return drawPicture_F2();
		}
		return false;
	}
	
	/**
	* getBitmap方法
	* <p>获取bitmap<br>
	* @param 无参数
	* @return 无返回值
	* @date 2015-5-1
	*/
	public Bitmap getBitmap(){
		return bitmap;
	}
	
	/**
	* setBitmapScaling方法
	* <p>设置图像放缩大小<br>
	* @param int scaling
	* @return 无返回值
	* @date 2015-5-1
	*/
	public void setBitmapScaling(int scaling){
		opts.inSampleSize = scaling;
	}
	
	/**
	* setPaintColor方法
	* <p>设置画笔颜色<br>
	* @param int color
	* @return 无返回值
	* @date 2015-5-1
	*/
	public void setPaintColor(int color){
		handle.setPaintColor(color);
	}
	
	/**
	* setPaintWidth方法
	* <p>设置画笔宽度<br>
	* @param int width
	* @return 无返回值
	* @date 2015-5-1
	*/
	public void setPaintWidth(int width){
		handle.setPaintWidth(width);
	}
	
	/**
	* recycleBitmap方法
	* <p>回收bitmap的内存<br>
	* @param 无参数
	* @return 无返回值
	* @date 2015-5-1
	*/
	private void recycleBitmap(){
		if(bitmap!=null&&bitmap.isRecycled()){
			bitmap.recycle();
			bitmap=null;
			System.gc();
		}
	}
	
	/**
	* drawPicture_B1方法
	* <p>画图函数(地下一楼)<br>
	* @param 无参数
	* @return boolean
	* @date 2015-5-1
	*/
	private boolean drawPicture_B1(){
		if(bitmap==null){
			bitmap=BitmapFactory.decodeStream(
					context.getResources().openRawResource(
							R.drawable.picturelow), null, opts).copy(
					Bitmap.Config.ARGB_8888, true);
			handle.drawPicture(new Canvas(bitmap), Floor.B1, 1500f / (float) bitmap.getWidth(), 
					598f / (float) bitmap.getHeight());
			return true;
		}
		return false;
	}
	
	/**
	* drawPicture_F1方法
	* <p>画图函数(一楼)<br>
	* @param 无参数
	* @return boolean
	* @date 2015-5-1
	*/
	private boolean drawPicture_F1(){
		if(bitmap==null){
			bitmap=BitmapFactory.decodeStream(
					context.getResources().openRawResource(
							R.drawable.picturemid), null, opts).copy(
					Bitmap.Config.ARGB_8888, true);
			handle.drawPicture(new Canvas(bitmap), Floor.F1, 2000f / (float) bitmap.getWidth(), 
					2000f / (float) bitmap.getHeight());
			return true;
		}
		return false;
	}
	
	/**
	* drawPicture_F2方法
	* <p>画图函数(二楼)<br>
	* @param 无参数
	* @return boolean
	* @date 2015-5-1
	*/
	private Boolean drawPicture_F2(){
		if(bitmap==null){
			bitmap=BitmapFactory.decodeStream(
					context.getResources().openRawResource(
							R.drawable.picturehight), null, opts).copy(
					Bitmap.Config.ARGB_8888, true);
			handle.drawPicture(new Canvas(bitmap), Floor.F2, 2000f / (float) bitmap.getWidth(), 
					2000f / (float) bitmap.getHeight());
			return true;
		}
		return false;
	}
}
