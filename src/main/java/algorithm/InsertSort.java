package algorithm;

import algorithm.model.SortBO;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * 插入排序
 * @author wzy
 * @date 2018/11/23
 **/
public class InsertSort {

    /**
     * 基本类型插入排序
     * @author wzy
     * @date 2018/11/23
     **/
    public static void insertSort(int [] arr){
        for (int i=1;i<arr.length;i++){
            int index=i;
            int temp=arr[i];
            while (index>0 && arr[index-1]>temp){
                arr[index]=arr[index-1];
                --index;
            }
            arr[index]=temp;
        }
    }

    /**
     * 对象插入排序
     * @author wzy
     * @date 2018/11/26
     **/
    public  static void insertSort(List<SortBO> arr){
        for (int i=1;i<arr.size();i++){
            int index=i;
            SortBO temp=arr.get(i);
            while(index>0 && arr.get(index-1).getNum()>temp.getNum()){
                arr.set(i,arr.get(i-1));
                --index;
            }
            arr.set(index,temp);
        }
    }

    public static void main(String[] args) {
        System.out.println("====================基本类型插入排序==================");
        int [] arr=AlgorithmUtil.getIntData(100);
        AlgorithmUtil.display(arr);
        long start=System.currentTimeMillis();
        insertSort(arr);
        System.out.println("基本类型插入排序耗时  "+ (System.currentTimeMillis()-start));
        AlgorithmUtil.display(arr);

        System.out.println("====================对象插入排序==================");
        List<SortBO> boList=AlgorithmUtil.getBOData(100);
        AlgorithmUtil.display(boList);
        long start1=System.currentTimeMillis();
        insertSort(boList);
        System.out.println("对象插入排序耗时  "+ (System.currentTimeMillis()-start1));
        AlgorithmUtil.display(boList);

        System.out.println("==================sort排序====================");
        List<SortBO> boList1=AlgorithmUtil.getBOData(100);
        AlgorithmUtil.display(boList1);
        long start2=System.currentTimeMillis();
        boList1.sort((x,y) -> x.getNum().compareTo(y.getNum()));
        System.out.println("sort排序耗时："+ (System.currentTimeMillis()-start2));
        AlgorithmUtil.display(boList1);
    }
}
