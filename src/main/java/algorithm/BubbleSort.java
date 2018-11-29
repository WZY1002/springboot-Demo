package algorithm;

import algorithm.model.SortBO;

import java.util.List;

/**
 * 冒泡排序
 * @author wzy
 * @date 2018/11/23
 **/
public class BubbleSort {

    /**
     * 基本类型冒泡
     * @author wzy
     * @date 2018/11/23
     **/
    public static void sortBubble(int [] arr){
        for (int i=arr.length-1;i>0;i--) {
            for (int j=0;j<i;j++){
                if(arr[j]>arr[j+1]){
                    AlgorithmUtil.swap(arr,j,j+1);
                }
            }
        }
    }

    /**
     * 对象冒泡
     * @author wzy
     * @date 2018/11/23
     **/
    public static void sortBubble(List<SortBO> boList){
        for (int i=boList.size()-1;i>0;i--) {
            for (int j=0;j<i;j++){
                if(boList.get(j).getNum()>boList.get(j+1).getNum()){
                    AlgorithmUtil.swap(boList,j,j+1);
                }
            }
        }
    }


    public static void main(String[] args) {
        System.out.println("========冒泡排序基本类型==========");
        int [] arr=AlgorithmUtil.getIntData(10);
        AlgorithmUtil.display(arr);
        sortBubble(arr);
        AlgorithmUtil.display(arr);

        System.out.println("========冒泡排序对象==========");
        List<SortBO> boList=AlgorithmUtil.getBOData(100);
        AlgorithmUtil.display(boList);
        long start=System.currentTimeMillis();
        System.out.println(start);
        sortBubble(boList);
        System.out.println(System.currentTimeMillis());
        System.out.println("对象冒泡耗时："+ (System.currentTimeMillis()-start));
        AlgorithmUtil.display(boList);

        System.out.println("========sort排序==========");
        List<SortBO> boList1=AlgorithmUtil.getBOData(100);
        AlgorithmUtil.display(boList1);
        long start1=System.currentTimeMillis();
        System.out.println(start1);
        boList1.sort((x,y) -> x.getNum().compareTo(y.getNum()));
        System.out.println(System.currentTimeMillis());
        System.out.println("sort排序耗时："+ (System.currentTimeMillis()-start1));
        AlgorithmUtil.display(boList1);
    }



}
