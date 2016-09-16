package com.wentongwang.mysports.model.bussiness;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * 带请求头的StringRequest
 * 请求头统一写在getHeaders()中
 * 设置请求头（HTTP headers）        如果你想失败后重新请求（因超时），您可以指定使用上面的代码，增加重试次数。注意最后一个参数，它允许你指定一个退避乘数可以用来实现“指数退避”来从RESTful服务器请求数据。
 * 有时候需要给HTTP请求添加额外的头信息，一个常用的例子是添加 “Authorization”到HTTP 请求的头信息
 * Volley请求类提供了一个 getHeaers（）的方法，重载这个方法可以自定义HTTP 的头信息
 * 
 * @author fan
 *
 */
public class VollyStringRequest extends StringRequest {

	public VollyStringRequest(int method, String url, Listener<String> listener, ErrorListener errorListener) {
		super(method, url, listener, errorListener);

		// 设置连接超时和重连策略
		// Volley中没有指定的方法来设置请求超时时间，
		// 可以设置RetryPolicy 来变通实现。DefaultRetryPolicy类有个initialTimeout参数，可以设置超时时间。
		// 要确保最大重试次数为1，以保证超时后不重新请求
		this.setRetryPolicy(new DefaultRetryPolicy(500000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
	}

	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
		} catch (Exception e) {
			if (e != null)
				e.printStackTrace();
		}
		return map;
	}
	 @Override
	    protected Response<String> parseNetworkResponse(NetworkResponse response) {
	        String str = null;
	        try {
	        	str = new String(response.data, "utf-8");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return Response.success(str, HttpHeaderParser.parseCacheHeaders(response));
	    }

}
