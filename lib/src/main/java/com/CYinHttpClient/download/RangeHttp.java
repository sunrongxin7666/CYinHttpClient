package com.CYinHttpClient.download;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by sunrongxin on 2017/6/15.
 */

public class RangeHttp {
	public static void main(String args[]) {


		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url("http://www.qq.com")
				                  //identity总是可被接受的encoding类型(除非显示的标记这个类型q=0) ,
				                  //如果Accept-Encoding的值是空, 那么只有identity是会被接受的类型
				                  //如果Accept-Encoding中的所有类型服务器都没发返回, 那么应该返回406错误给客户端
				                  //如果request中没有Accept-Encoding 那么服务器会假设所有的Encoding都是可以被接受的。
				                  //如果Accept-Encoding中有identity 那么应该优先返回identity (除非有q值的定义,或者你认为另外一种类型是更有意义的)
				                  //注意:
				                  //如果服务器不支持identity 并且浏览器没有发送Accept-Encoding,那么服务器应该倾向于使用HTTP1.0中的 "gzip" and "compress" , 服务器可能按照客户端类型 发送更适合的encoding类型
				                  .addHeader("Accept-Encoding","identity")
								  //从0开始一直到最后
				                  .addHeader("Range", "bytes=0-").build();
		try {
			Response response = client.newCall(request).execute();
			//一般只有静态文件才会有该字段
			System.out.println("content-length : "+response.body().contentLength());
			if (response.isSuccessful()) {
				Headers headers = response.headers();
				for (int i = 0; i < headers.size(); i++) {
					//Headers 其实是个一维数组；
					System.out.println(headers.name(i) + " : " + headers.value(i));
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
