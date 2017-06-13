package com.CYinHttpClient.lib;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by sunrongxin on 2017/6/13.
 */

public class PostHttp {
	public static void main(String args[]) {

		new Thread() {

			@Override
			public void run() {
				OkHttpClient client = new OkHttpClient();
				//构建post时使用的Body
				FormBody body = new FormBody.Builder().add("username", "nate")
						                .add("userage", "99").build();
				Request request = new Request.Builder().url("http://localhost:8080/web/HelloServlet").post(body).build();
				try {
					Response response = client.newCall(request).execute();
					if (response.isSuccessful()) {
						System.out.println(response.body().string());
					}
				} catch (IOException e) {
					e.printStackTrace();
				}


			}
		}.start();
	}
}
