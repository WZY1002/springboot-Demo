package io;

import org.springframework.util.ObjectUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * File，Files文件操作
 * @author wzy
 * @since 2019/3/24 9:50
 **/
public class FileOperation {

    public static void main(String[] args) throws IOException {
//        File file=new File("D:\\demoFile");
//        listAllFile(file);
        listAllFiles("D:\\demoFile");
    }

    /**
     * File遍历一个文件或目录
     * @author wzy
     * @since 2019/3/24 9:52
     **/
    public static void listAllFile(File dir) {
        if(ObjectUtils.isEmpty(dir) && !dir.exists()){
            return;
        }
        if(dir.isFile()){
            System.out.println(dir.getName());
            System.out.println(dir.toURI());
            System.out.println(dir.toPath());
        }
        if(dir.isDirectory()){
           for (File file:dir.listFiles()){
                listAllFile(file);
           }
        }
    }

    /***
     * Files遍历一个文件或目录
     * @param path 文件路径
     * @return void
     * @author  wzy
     * @since  2019/3/25 15:24
     **/
    public static void listAllFiles(String path) throws IOException {
        if(!Files.exists(Paths.get(path))){
            return;
        }
        if(Files.isDirectory(Paths.get(path))){
            Path path1 = Paths.get(path);
            SimpleFileVisitor<Path> fileVisitor =new SimpleFileVisitor<Path>(){
                //访问目录是触发
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    System.out.println("正在访问：" + dir +"文件夹");
                    return FileVisitResult.CONTINUE;
                }
                //访问文件时触发
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    System.out.println("正在访问：" + file +"文件");
                    return FileVisitResult.CONTINUE;
                }
            } ;
            Files.walkFileTree(path1,fileVisitor);
        }else {
            System.out.println("第一个就是文件" + Paths.get(path).getFileName() +"文件");
        }
    }
}
