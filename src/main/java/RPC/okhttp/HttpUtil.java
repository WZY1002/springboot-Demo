//package com.djcps.avm.commons.utils;
//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//import java.util.Collection;
//import java.util.Map;
//import java.util.Set;
//
//
//import com.google.gson.Gson;
//import commom.GsonUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONException;
//import com.alibaba.fastjson.JSONObject;
//import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
//import com.rpt.model.base.ResultModel;
//import com.rpt.util.DjException;
//
//import okhttp3.FormBody;
//import okhttp3.MediaType;
//import okhttp3.MultipartBody;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//import okhttp3.ResponseBody;
//
///**
// * http请求工具类
// *
// * @author zqz
// *
// */
//public class HttpUtil {
//	private static OkHttpClient client = new OkHttpClient();
//	private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);
//
//	private Gson gson = GsonUtils.gson;
//
//	/**
//	 * 向指定URL发送POST方法的请求
//	 * @param url
//	 *            发送请求的URL
//	 * @param param
//	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
//	 * @return 所代表远程资源的响应结果
//	 */
//	public static ResultModel post(String url, Map<String, ?> map) {
//		ResultModel jso = new ResultModel(false, "");
//		FormBody.Builder builder = new FormBody.Builder();
//		Set<String> keys = map.keySet();
//		for (String key : keys) {
//			if (map.get(key) instanceof Object[]) {
//				for(Object value:(Object[])map.get(key)){
//					builder.add(key, value.toString());
//				}
//			} else if(map.get(key) instanceof Collection<?>){
//				Collection<?> entry=(Collection<?>)map.get(key);
//				for(Object value:entry){
//					builder.add(key, value.toString());
//				}
//			}
//			else {
//				builder.add(key, map.get(key).toString());
//			}
//		}
//		FormBody build = builder.build();
//		Request request = new Request.Builder().addHeader("Connection", "close").url(url).post(build).build();
//		StringBuilder logBuilder = new StringBuilder();
//		try {
//			logBuilder.append("==> Http_Preparing: POST - ").append(url).append("\r\n");
//			logBuilder.append("==> Http_Parameters: json/application - Request parameters")
//					.append(JSON.toJSONString(map)).append("\r\n");
//			Long start = System.currentTimeMillis();
//			Response response = client.newCall(request).execute();
//			Long executeTime = System.currentTimeMillis() - start;
//			String strString = response.body().string();
//			logBuilder.append(
//					String.format("<== Http_Result: %s - %s - 耗时%sms", response.code(), strString, executeTime));
//			if (response.isSuccessful()) {
//
//				jso = (ResultModel) JSON.parseObject(strString, ResultModel.class);
//				if (jso.getData() != null && jso.getData() instanceof JSONObject
//						&& ((JSONObject) jso.getData()).size() == 1) {
//					jso.setData(((JSONObject) jso.getData()).values().toArray()[0]);
//				}
//				LOGGER.info(logBuilder.toString());
//			} else {
//				LOGGER.error(logBuilder.toString());
//				jso.setMsg("请求失败！");
//			}
//		} catch (IOException e1) {
//			logBuilder.append("\r\n").append(e1.getMessage());
//			LOGGER.error(logBuilder.toString(), e1);
//			jso.setMsg("未知错误！");
//		} catch (JSONException e) {
//			logBuilder.append("\r\n").append(e.getMessage());
//			LOGGER.error(logBuilder.toString(), e);
//			jso.setMsg("解析失败！");
//		}
//
//		return jso;
//	}
//
//	/**
//	 * 向指定URL发送GET方法的请求
//	 *
//	 * @param url
//	 *            发送请求的URL
//	 * @param param
//	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
//	 * @return
//	 */
//	public static ResultModel get(String url, Map<String, ?> map) {
//		LOGGER.info(url);
//		StringBuilder logBuilder = new StringBuilder();
//		ResultModel jso = new ResultModel(false, "");
//		StringBuilder tempParams = new StringBuilder();
//		int pos = 0;
//		// 补全请求地址
//		String questionMark = "?";
//		if (url.indexOf(questionMark) < 0) {
//			url += questionMark;
//		} else {
//			pos = 1;
//		}
//
//		try {
//			Set<String> keys = map.keySet();
//			for (String key : keys) {
//				if (map.get(key)  instanceof Object[]) {
//					for(Object valueobject:(Object[])map.get(key)){
//						tempParams.append(pos > 0?"&":"");
//						tempParams.append(String.format("%s=%s", key, URLEncoder.encode(valueobject.toString(), "utf-8")));
//						pos++;
//					}
//				}else if(map.get(key) instanceof Collection<?>){
//					Collection<?> entry=(Collection<?>)map.get(key);
//					for(Object valueobject:entry){
//						tempParams.append(pos > 0?"&":"");
//						tempParams.append(String.format("%s=%s", key, URLEncoder.encode(valueobject.toString(), "utf-8")));
//						pos++;
//					}
//				}else {
//					tempParams.append(pos > 0?"&":"");
//					tempParams.append(String.format("%s=%s", key, URLEncoder.encode(map.get(key).toString(), "utf-8")));
//					pos++;
//				}
//			}
//		} catch (UnsupportedEncodingException e) {
//			LOGGER.error("参数转换失败！" + url);
//			jso.setMsg("参数转换失败！");
//		}
//		url += tempParams.toString();
//
//		Request request = new Request.Builder().addHeader("Connection", "close").url(url).get().build();
//
//		try {
//			logBuilder.append("==> Http_Preparing: GET - ").append(url).append("\r\n");
//			Long start = System.currentTimeMillis();
//			Response response = client.newCall(request).execute();
//			Long executeTime = System.currentTimeMillis() - start;
//			String strString = response.body().string();
//			logBuilder.append(
//					String.format("<== Http_Result: %s - %s - 耗时%sms", response.code(), strString, executeTime));
//			if (response.isSuccessful()) {
//				jso = (ResultModel) JSON.parseObject(strString, ResultModel.class);
//				if (jso.getData() != null && jso.getData() instanceof JSONObject
//						&& ((JSONObject) jso.getData()).size() == 1) {
//					jso.setData(((JSONObject) jso.getData()).values().toArray()[0]);
//				}
//				LOGGER.info(logBuilder.toString());
//			} else {
//				LOGGER.error(logBuilder.toString());
//				jso.setMsg("请求失败！");
//			}
//		} catch (IOException e1) {
//			logBuilder.append("\r\n").append(e1.getMessage());
//			LOGGER.error(logBuilder.toString(), e1);
//			jso.setMsg("未知错误！");
//		} catch (JSONException e) {
//			logBuilder.append("\r\n").append(e.getMessage());
//			LOGGER.error(logBuilder.toString(), e);
//			jso.setMsg("解析失败！");
//		}
//
//		return jso;
//
//	}
//
//	public static ResponseBody getFiles(String url) throws IOException {
//		Request request = new Request.Builder().url(url).get().build();
//		StringBuilder logBuilder = new StringBuilder();
//
//		logBuilder.append("==> Http_Preparing: GET - ").append(url).append("\r\n");
//		Long start = System.currentTimeMillis();
//		Response response = client.newCall(request).execute();
//		Long executeTime = System.currentTimeMillis() - start;
//		logBuilder.append(
//				String.format("<== Http_Result: %s - %s - 耗时%sms", response.code(), response.body(), executeTime));
//		if (!response.isSuccessful()) {
//			LOGGER.debug(logBuilder.toString());
//			throw new DjException("请求失败");
//		} else {
//			LOGGER.error(logBuilder.toString());
//		}
//		return response.body();
//	}
//
//
//public HttpResult postFile(String url, MultipartFile file,String name)  {
//		MediaType mutilPartFormData = MediaType.parse("multipart/form-data; charset=utf-8");
//		MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
//		StringBuilder logBuilder = new StringBuilder();
//		try {
////		Set<String> keys = map.keySet();
////		for (String key : keys) {
////			if (map.get(key) instanceof MultipartFile) {
////				MultipartFile file=(MultipartFile)map.get(key);
//				builder.addFormDataPart(name, file.getOriginalFilename(),RequestBody.create(mutilPartFormData, file.getBytes()));
////			} else {
////				builder.addFormDataPart(key, (String) map.get(key));
////			}
////		}
//		MultipartBody build = builder.build();
//		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(MultipartFile.class, "name","originalFilename","size");
//		Request request = new Request.Builder().addHeader("Connection", "close").url(url).post(build).build();
//			logBuilder.append("==> Http_Preparing: POST - ").append(url).append("\r\n");
//			logBuilder.append("==> Http_Parameters: ").append(mutilPartFormData.type()).append("- Request parameters")
//					.append(JSON.toJSONString(map,filter)).append("\r\n");
//			Long start = System.currentTimeMillis();
//			Response response = client.newCall(request).execute();
//			try {
//				response = client.newCall(request).execute();
//				if (response.isSuccessful()) {
//					String bodyStr= response.body().string();
//					HttpResult baseResult = gson.fromJson(bodyStr, HttpResult.class);
//					return baseResult;
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}catch (Exception e){
//			e.printStackTrace();
//		}
////			Long executeTime = System.currentTimeMillis() - start;
////			String strString = response.body().string();
////			logBuilder.append(
////					String.format("<== Http_Result: %s - %s - 耗时%sms", response.code(), strString, executeTime));
////			if (response.isSuccessful()) {
////				jso = (ResultModel) JSON.parseObject(strString, ResultModel.class);
////				if (jso.getData() != null && jso.getData() instanceof JSONObject
////						&& ((JSONObject) jso.getData()).size() == 1) {
////					jso.setData(((JSONObject) jso.getData()).values().toArray()[0]);
////				}
////				LOGGER.info(logBuilder.toString());
////			} else {
////				LOGGER.error(logBuilder.toString());
////				jso.setMsg("请求失败！");
////			}
////		} catch (IOException e1) {
////			logBuilder.append("\r\n").append(e1.getMessage());
////			LOGGER.error(logBuilder.toString(), e1);
////			jso.setMsg("未知错误！");
////		} catch (JSONException e) {
////			logBuilder.append("\r\n").append(e.getMessage());
////			LOGGER.error(logBuilder.toString(), e);
////			jso.setMsg("解析失败！");
////		}
//		return jso;
//	}
//
//}
