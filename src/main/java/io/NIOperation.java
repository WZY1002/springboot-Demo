package io;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * NIO 非阻塞传输IO 操作
 * @author wzy
 * @since 2019/3/25 16:53
 **/
public class NIOperation {

    public static void main(String[] args) throws IOException {
        long start=System.currentTimeMillis();
        fastCopy("D:\\demoFile\\一念永恒.txt","D:\\demoFile\\demoFile2\\demoText3.txt");
        System.out.println("总耗时：" +(System.currentTimeMillis()-start));
    }

    /***
     * 使用NIO 快速拷贝文件
     * @param src 原路径
     * @param dlist 目标路径
     * @return void
     * @author  wzy
     * @since  2019/3/25 17:05
     **/
    public static void fastCopy(String src,String dlist) throws IOException {
        //获得源文件的输入字节流
        FileInputStream fin=new FileInputStream(src);
        //获取目标文件的输出字节流
        FileOutputStream fout=new FileOutputStream(dlist);
        //获取输入字节流的文件通道
        FileChannel fcin=fin.getChannel();
        //获取输出字节流的文件通道
        FileChannel fcout=fout.getChannel();
        //为缓冲区分配1024字节
        ByteBuffer byteBuffer=ByteBuffer.allocateDirect(1024);
        //从输入通道中读取数据到缓冲区中,读入给定缓冲区的字节序列。
        while (true){
            int flag=fcin.read(byteBuffer);
            //等于-1 表示EOF(无法在读取更多)
            if(flag== -1){
                break;
            }
            //切换读写,保证写缓冲区中已存在的大小，而不会下标越界
            byteBuffer.flip();
            //从给定缓冲区将字节序列写入此通道。
            fcout.write(byteBuffer);
            //清空缓冲池
            byteBuffer.clear();
        }

    }

}
