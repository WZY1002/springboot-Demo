package RPC.okhttp;

import okhttp3.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * okhttp的使用
 * @author wzy
 * @date 2019/1/21
 **/
public class OkHttpUtil {

    private static OkHttpClient client = new OkHttpClient();

    /**
     * 普通post请求
     * @param url 请求地址
     * @param param1  请求参数
     * @author wzy
     * @date 2019/1/21
     **/
    public void  post(String url,String param1) throws IOException {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("param",param1);
        FormBody build = builder.build();
        Request request = new Request.Builder()
                .addHeader("Connection", "close")
                .url(url)
                .post(build)
                .build();
        Response response = client.newCall(request).execute();
        String httpResult = response.body().string();
    }

    /**
     * 文件post请求
     * @param url 请求地址
     * @param file 文件参数
     * @param param1 参数1
     * @param name 文件参数名
     * @author wzy
     * @date 2019/1/23
     **/
    public void postFile(String url, MultipartFile file,String param1,String name) throws IOException {
        MediaType mutilPartFormData = MediaType.parse("multipart/form-data; charset=utf-8");
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart(name, file.getOriginalFilename(),RequestBody.create(mutilPartFormData, file.getBytes()))
                .addFormDataPart("param1",param1);
        MultipartBody build = builder.build();
        Request request = new Request.Builder().addHeader("Connection", "close")
                .url(url)
                .post(build)
                .build();
        Response response = client.newCall(request).execute();
        String httpResult = response.body().string();
    }

    /**
     * 普通get请求
     * @author wzy
     * @date 2019/1/23
     **/
    public void  get(String url,String param1) throws IOException {
        url=url+"?"+(String.format("%s=%s", "param1", URLEncoder.encode(param1, "utf-8")));
        Request request = new Request.Builder()
                .addHeader("Connection", "close")
                .url(url)
                .get()
                .build();
        Response response = client.newCall(request).execute();
        String httpResult = response.body().string();
    }

}
