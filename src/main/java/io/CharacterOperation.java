package io;

import java.io.*;

/**
 * 字符 操作
 * @author wzy
 * @since 2019/3/25 14:14
 **/
public class CharacterOperation {

    public static void main(String[] args) throws IOException {
//        stringCoding();
        readFileContent("D:\\demoFile\\demoText.txt");
    }

    /***
     * String的编码方式
     * @return void
     * @author  wzy
     * @since  2019/3/25 14:16
     **/
    public static void stringCoding() throws UnsupportedEncodingException {
        String str="吴智勇";
        //String编码 为字节序列
        byte[] bytes=str.getBytes("UTF-8");
        //字节序列解码 为String
        String str1=new String(bytes,"UTF-8");
        System.out.println(str1);
    }

    /***
     * 逐行读取文件字符
     * @param path 文件路径
     * @return void
     * @author  wzy
     * @since  2019/3/25 14:32
     **/
    public static void readFileContent(String path) throws IOException {
        //装饰者模式使BufferedReader组装Reader,但使用FileReader不能指定编码格式会乱码
//        FileReader fileReader=new FileReader(path);
//        BufferedReader bufferedReader=new BufferedReader(fileReader);

        InputStreamReader isr = new InputStreamReader(new FileInputStream(new File(path)), "GBK");
        BufferedReader bufferedReader=new BufferedReader(isr);
        String line;
        while ((line=bufferedReader.readLine())!=null){
            System.out.println(line);
        }
        bufferedReader.close();
    }


}
