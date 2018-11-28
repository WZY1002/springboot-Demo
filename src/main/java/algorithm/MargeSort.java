package algorithm;

import algorithm.model.SortBO;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 归并排序，建立在归并的两个子序列都是有序的
 * @author wzy
 * @date 2018/11/26
 **/
public class MargeSort {

    public static void sort(int []arr){
        //在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        int []temp = new int[arr.length];
        sort(arr,0,arr.length-1,temp);
    }

    /**
     * 基本类型二分递归
     * @author wzy
     * @date 2018/11/26
     **/
    private static void sort(int[] arr, int left, int right, int[] temp) {
        if(left==right){
            return;
        }else {
            int mid = (left+right)/2;
            //左边归并排序，使得左子序列有序
            sort(arr,left,mid,temp);
            //右边归并排序，使得右子序列有序
            sort(arr,mid+1,right,temp);
            //将两个有序子数组合并操作
            merge(arr,left,mid,right,temp);
        }
    }

    /**
     * 基本类型子序列排序归并
     * @author wzy
     * @date 2018/11/26
     **/
    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        //左序列指针
        int i = left;
        //右序列指针
        int j = mid+1;
        //临时数组指针
        int t = 0;
        while (i<=mid && j<=right){
            if(arr[i]<=arr[j]){
                temp[t++] = arr[i++];
            }else {
                temp[t++] = arr[j++];
            }
        }
        while(i<=mid){
            //将左边剩余元素填充进temp中
            temp[t++] = arr[i++];
        }
        while(j<=right){
            //将右序列剩余元素填充进temp中
            temp[t++] = arr[j++];
        }
        t = 0;
        //将temp中的元素全部拷贝到原数组中
        while(left <= right){
            arr[left++] = temp[t++];
        }
    }

    public static  void sort(SortBO [] boList){
        SortBO [] tempBOList=new SortBO[boList.length];
        sort(boList,0,boList.length-1,tempBOList);
    }

    /**
     * 对象二分递归
     * @author wzy
     * @date 2018/11/26
     **/
    private static void sort(SortBO [] boList, int left, int right, SortBO [] tempBOList) {
        if(left==right){
            return;
        }else {
            int mid=(left+right)/2;
            sort(boList,left,mid,tempBOList);
            sort(boList,mid+1,right,tempBOList);
            merge(boList,left,mid,right,tempBOList);
        }
    }

    /**
     * 对象子序列归并排序
     * @author wzy
     * @date 2018/11/26
     **/
    private static void merge(SortBO [] boList, int left, int mid, int right, SortBO [] tempBOList) {
        int i=left;
        int j=mid+1;
        int t=0;
        while (i<=mid && j<=right){
            if(boList[i].getNum()<=boList[j].getNum()){
                tempBOList[t++]=boList[i++];
            }else {
                tempBOList[t++]=boList[j++];
            }
        }
        while (i<=mid){
            tempBOList[t++]=boList[i++];
        }
        while (j<=right){
            tempBOList[t++]=boList[j++];
        }
        t=0;
        while (left<=right){
            boList[left++]=tempBOList[t++];
        }
    }

    public static void main(String[] args) {
        int []arr=AlgorithmUtil.getIntData(100);
        AlgorithmUtil.display(arr);
        sort(arr);
        AlgorithmUtil.display(arr);

        List<SortBO> boList=AlgorithmUtil.getBOData(100);
        SortBO[] sortBOS=new SortBO[boList.size()];
        boList.toArray(sortBOS);
        AlgorithmUtil.display(boList);
        sort(sortBOS);
        AlgorithmUtil.display(Arrays.asList(sortBOS));
    }

}
