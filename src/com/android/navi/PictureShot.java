package com.android.navi;

import com.android.tools.Floor;

import android.graphics.Bitmap;


public class PictureShot {
	private Route route;
	private Bitmap bitmap;
	
	public PictureShot(){
		route=new Route();
	}
	
	public Bitmap pictureShot(Floor floor){
		switch(floor){
		case B1:
			return pictureShot_B1();
		case F1:
			return pictureShot_F1();
		case F2:
			return pictureShot_F2();
		}
		return null;
	}
	
	private Bitmap pictureShot_B1(){
		return null;
	}
	
	private Bitmap pictureShot_F1(){
		return null;
	}
	
	private Bitmap pictureShot_F2(){
		return null;
	}
	
	public Route getRoute(){
		return route;
	}

}
