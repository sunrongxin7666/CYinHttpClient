package com.android.srx.github.httpframework.http;

import java.util.Map;


interface NameValueMap extends Map<String,String> {

	String get(String key);

	void set(String key, String value);

	void setAll(Map<String,String> map);
}
