package com.android.tools;

/**
* Convert类
* <p>数据转换类<br>
* @author 胡思旺
* @version 2.0
* @date 2015-4-30
*/
public class Convert {
	/**
	* parseInt方法
	* <p>将string类型数据转换为int类型<br>
	* @param String str
	* @return int
	* @date 2015-4-30
	*/
	public static int parseInt(String str) {
		int num=0,temp=1;
		for(int i=str.length()-1;i>=0;i--){
			num+=temp*parseChar(str.charAt(i));
			temp*=10;
		}
		return num;
	}
	
	/**
	* parseChar方法
	* <p>将char类型数据转换为int类型<br>
	* @param char Char
	* @return int
	* @date 2015-4-30
	*/
	public static int parseChar(char Char){
		switch(Char){
		case '0':
			return 0;
		case '1':
			return 1;
		case '2':
			return 2;
		case '3':
			return 3;
		case '4':
			return 4;
		case '5':
			return 5;
		case '6':
			return 6;
		case '7':
			return 7;
		case '8':
			return 8;
		case '9':
			return 9;
		}
		return 0;
	}
}
