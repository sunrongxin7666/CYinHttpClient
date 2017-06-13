package com.CYinHttpClient.lib;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by sunrongxin on 2017/6/12.
 */

public class QueryHttp {
	public static void main(String args[]) {

		OkHttpClient client = new OkHttpClient();
		//构建URL请求；Get
		HttpUrl httpUrl = HttpUrl.parse("https://api.heweather.com/x3/weather")
				                  .newBuilder()
				                  .addQueryParameter("city", "beijing")
				                  .addQueryParameter("key", "d17ce22ec5404ed883e1cfcaca0ecaa7")
				                  .build();
		String url = httpUrl.toString();
		System.out.println(httpUrl.toString());
		Request request = new Request.Builder().url(url).build();
		try {
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				System.out.println(response.body().string());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
