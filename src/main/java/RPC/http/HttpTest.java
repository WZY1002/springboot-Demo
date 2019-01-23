package RPC.http;


public class HttpTest {

    public static void main(String[] args) {
        HttpPost httpPost=new HttpPost();
        httpPost.run();

        HttpClintPost httpClintPost=new HttpClintPost();
        httpClintPost.run();

        HttpsPost httpsPost =new HttpsPost();
        httpsPost.httpsRequest("https://kyfw.12306.cn/","GET",null);
    }

}
