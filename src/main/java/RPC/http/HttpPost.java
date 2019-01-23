package RPC.http;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpPost {
    public void run() {
        try {
            URL url=new URL("https://kyfw.12306.cn/");
            //打开url连接
            HttpURLConnection connection=(HttpURLConnection) url.openConnection();
            connection.setRequestProperty("encoding","UTF-8");
            //从网络获取数据权限
            connection.setDoInput(true);
            //从网络发送数据权限
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");

            //对输出流进行包装
            OutputStream os=connection.getOutputStream();
            OutputStreamWriter osw=new OutputStreamWriter(os);
            BufferedWriter bw=new BufferedWriter(osw);

//            bw.write("keyfrom=JKXY-test&key=343166845&type=data&doctype=xml&version=1.1&q=welcome 吴智勇");
//            //强制输入
//            bw.flush();

            //对输入流进行包装
            InputStream is=connection.getInputStream();
            InputStreamReader isr=new InputStreamReader(is);
            BufferedReader br=new BufferedReader(isr);

            String line;
            StringBuffer buffer=new StringBuffer();
            while ((line=br.readLine())!=null){
                buffer.append(line);
            }
            bw.close();
            osw.close();
            os.close();
            br.close();
            isr.close();
            is.close();

            System.out.println(buffer.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
