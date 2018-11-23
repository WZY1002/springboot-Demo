package algorithm;

import algorithm.model.SortBO;

import java.util.List;

/**
 * 选择排序
 * @author wzy
 * @date 2018/11/23
 **/
public class SelectSort {

    /**
     * 基本类型选择排序
     * @author wzy
     * @date 2018/11/23
     **/
    public static void sortSelect(int [] arr){
        for(int i=0;i<arr.length-1;i++){
            int min=i;
            for(int j=i+1;j<arr.length;j++){
                if(arr[j]<arr[min]){
                    min=j;
                }
            }
            if(min!=i){
                AlgorithmUtil.swap(arr,i,min);
            }
        }
    }

    public static void sortSelect(List<SortBO> arr){
        for(int i=0;i<arr.size()-1;i++){
            int min=i;
            for(int j=i+1;j<arr.size();j++){
                if(arr.get(j).getNum()<arr.get(min).getNum()){
                    min=j;
                }
            }
            if(min!=i){
                AlgorithmUtil.swap(arr,i,min);
            }
        }
    }

    public static void main(String[] args) {
//        System.out.println("========基本类型选择排序=========");
//        int [] arr=AlgorithmUtil.getIntData(100);
//        AlgorithmUtil.display(arr);
//        long start=System.currentTimeMillis();
//        System.out.println(start);
//        sortSelect(arr);
//        System.out.println(System.currentTimeMillis());
//        System.out.println("基本类型选择排序耗时  "+ (System.currentTimeMillis()-start));
//        AlgorithmUtil.display(arr);

        System.out.println("====================对象选择排序==================");
        List<SortBO> boList=AlgorithmUtil.getBOData(100);
        AlgorithmUtil.display(boList);
        long start=System.currentTimeMillis();
        sortSelect(boList);
        System.out.println("对象选择排序耗时  "+ (System.currentTimeMillis()-start));
        AlgorithmUtil.display(boList);

        System.out.println("==================sort排序====================");
        List<SortBO> boList1=AlgorithmUtil.getBOData(100);
        AlgorithmUtil.display(boList1);
        long start1=System.currentTimeMillis();
        boList1.sort((x,y) -> x.getNum().compareTo(y.getNum()));
        System.out.println("sort排序耗时："+ (System.currentTimeMillis()-start1));
        AlgorithmUtil.display(boList1);
    }

}
