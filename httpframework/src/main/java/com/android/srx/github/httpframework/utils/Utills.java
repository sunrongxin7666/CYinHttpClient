package com.android.srx.github.httpframework.utils;

/**
 * Created by sunrongxin on 2017/7/5.
 */

public class Utills {


	public static boolean isExist(String className, ClassLoader loader) {
		try {
			Class.forName(className);
			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
}

