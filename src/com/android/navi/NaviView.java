package com.android.navi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.husiwang.myapp.R;

/**
* NaviView类
* <p>导航视图类<br>
* @author 胡思旺
* @version 2.0
* @date 2015-5-2
*/
public class NaviView extends SurfaceView implements SurfaceHolder.Callback,Runnable{
	private SurfaceHolder holder;
	private Bitmap bitmap;
	private Canvas canvas;
	private Paint paint;
	private BitmapFactory.Options opts;
	private int locationX=0;
	private int locationY=0;
	
	/**
	* 构造函数
	* <p>初始化相关变量<br>
	* @date 2015-5-2
	*/
	public NaviView(Context context) {
		super(context);
		holder=getHolder();
		holder.addCallback(this);
		setFocusable(true);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		bitmap=BitmapFactory.decodeStream(
				getResources().openRawResource(
						R.drawable.picturehight), null, opts).copy(
				Bitmap.Config.ARGB_8888, true);
		paint=new Paint();
		new Thread(this).start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		
	}

	@Override
	public void run() {
		while(true){
			if(locationX<2000-1.5*getWidth())
				locationX+=10;
			else {
				locationX=0;
			}
			if(locationY<2000-1.5*getWidth())
				locationY+=10;
			else {
				locationY=0;
			}
			draw();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void draw(){
		canvas=holder.lockCanvas();
		paint.setColor(Color.WHITE);    
        paint.setStyle(Style.FILL);   
        canvas.drawRect(new Rect(0, 0, getWidth(), getHeight()), paint); 
		canvas.drawBitmap(Bitmap.createBitmap(bitmap, locationX, locationY, getWidth(), getWidth()), 0, getWidth()/3, null);
		holder.unlockCanvasAndPost(canvas);
	}
	
	private Bitmap pictureShot(int x,int y){
		
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
