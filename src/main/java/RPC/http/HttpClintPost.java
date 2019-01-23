package RPC.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpClintPost{
    CloseableHttpClient client= HttpClients.createDefault();


    public void run() {
        HttpPost httpPost=new HttpPost("http://fanyi.youdao.com/openapi.do");
        try {
            //定义参数
            List<BasicNameValuePair> params=new ArrayList<>();
            params.add(new BasicNameValuePair("keyfrom","JKXY-test"));
            params.add(new BasicNameValuePair("key","343166845"));
            params.add(new BasicNameValuePair("type","data"));
            params.add(new BasicNameValuePair("doctype","xml"));
            params.add(new BasicNameValuePair("version","1.1"));
            params.add(new BasicNameValuePair("q","welcome"));
            //传入参数
            httpPost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));
            //接受返回的请求
            HttpResponse response=client.execute(httpPost);
            //读取返回的entity数据
            HttpEntity entity=response.getEntity();
            String result= EntityUtils.toString(entity,"UTF-8");

            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
