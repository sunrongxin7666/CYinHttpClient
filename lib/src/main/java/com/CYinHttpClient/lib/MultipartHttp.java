package com.CYinHttpClient.lib;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by sunrongxin on 2017/6/13.
 */

public class MultipartHttp {
	public static void main(String args[]) {

		RequestBody imageBody = RequestBody.create(
				MediaType.parse("image/jpeg"),
				new File("/Users/sunrongxin/psu.jpg"));
		MultipartBody body = new MultipartBody.Builder()
				                     .setType(MultipartBody.FORM)
				                     .addFormDataPart("name", "nate")
				                     .addFormDataPart("filename", "girl.jpg", imageBody).build();
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url("http://localhost:8080/web/UploadServlet").post(body).build();
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
