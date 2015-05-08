package com.android.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.android.navi.NaviView;

public class MainActivity extends Activity{
	private NaviView view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		view=new NaviView(this);
		setContentView(view);
	}
}
