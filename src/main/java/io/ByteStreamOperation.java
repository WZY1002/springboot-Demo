package io;

import java.io.*;

/**
 * 字节流操作
 * @author wzy
 * @since 2019/3/24 18:14
 **/
public class ByteStreamOperation {
    public static void main(String[] args) throws IOException {
        long start=System.currentTimeMillis();
        copyFile("D:\\demoFile\\一念永恒.txt","D:\\demoFile\\demoFile2\\demoText2.txt");
        System.out.println("总耗时：" +(System.currentTimeMillis()-start));
    }

    /***
     * 字节流拷贝文件
     * @param src
     * @param dist
     * @return void
     * @author  wzy
     * @since  2019/3/25 18:58
     **/
    public static void copyFile(String src, String dist) throws IOException {
        FileInputStream inputStream=new FileInputStream(src);
        FileOutputStream outputStream=new FileOutputStream(dist);
        try {
            byte[] buff=new byte[20*1024];
            int cont;
            //read 读取buff数据从0~buff.length 个字节
            while ((cont=inputStream.read(buff,0,buff.length))!= -1){
                outputStream.write(buff,0,cont);
            }
        }finally {
            inputStream.close();
            outputStream.close();
        }
    }
}
