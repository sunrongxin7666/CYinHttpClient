package com.CYinHttpClient.lib;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by sunrongxin on 2017/6/11.
 * a test method for okhttp
 */

public class HelloOkHttp {

	public static void main(String args[]) {
		OkHttpClient client = new OkHttpClient();


		RequestBody body=RequestBody.create(null,new byte[0]);
		// Builder
		Request request = new Request.Builder().url("http://www.imooc.com").method("GET",null).build();
		try {
			// 同步执行，等待返回情况；
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				String resp = response.body().string();
				System.out.println(resp);
			}
		} catch (IOException | NullPointerException e) {
			e.printStackTrace();
		}
	}
}
