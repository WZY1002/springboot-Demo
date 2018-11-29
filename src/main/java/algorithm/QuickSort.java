package algorithm;

import algorithm.model.SortBO;

import java.util.List;

/**
 * 快速排序
 * @author wzy
 * @date 2018/11/28
 **/
public class QuickSort {

    /**
     * 快速排序
     * @param arr
     * @param left  左指针
     * @param right 右指针
     */
    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            //获取枢纽值，并将其放在当前待处理序列末尾
            dealPivot(arr, left, right);
            //枢纽值被放在序列末尾
            int pivot = right - 1;
            //左指针
            int i = left;
            //右指针
            int j = right - 1;
            while (true) {
                System.out.println("left--"+left+"   pivot--"+pivot+"   right--"+right);
                AlgorithmUtil.display(arr);
                while (arr[++i] < arr[pivot]) {
                    //当左指针指向元素大于枢纽值，左指针停止移动
                    System.out.print("i"+ i+"---");
                    System.out.println(arr[i]);
                }
                while (j > left && arr[--j] > arr[pivot]) {
                    //当右指针小于左指针，且右指针指向元素小于枢纽值，右指针停止运动
                    System.out.print("j"+j+"---");
                    System.out.println(arr[j]);
                }
                if (i < j) {
                    AlgorithmUtil.swap(arr, i, j);
                } else {
                    break;
                }
            }
            if (i < right) {
                AlgorithmUtil.swap(arr, i, right - 1);
            }
            quickSort(arr, left, i - 1);
            quickSort(arr, i + 1, right);
        }
    }

    /**
     * 处理枢纽值
     *
     * @param arr
     * @param left
     * @param right
     */
    public static void dealPivot(int[] arr, int left, int right) {
        int mid = (left + right) / 2;
        if (arr[left] > arr[mid]) {
            AlgorithmUtil.swap(arr, left, mid);
        }
        if (arr[left] > arr[right]) {
            AlgorithmUtil.swap(arr, left, right);
        }
        if (arr[right] < arr[mid]) {
            AlgorithmUtil.swap(arr, right, mid);
        }
        //将枢纽值移动到right-1下标处
        AlgorithmUtil.swap(arr, right - 1, mid);
    }

    /**
     * 对象类型处理枢纽值
     * @author wzy
     * @date 2018/11/29
     **/
    public  static void dealPivot(List<SortBO> arr,int left,int right){
        int mid=(left+right)/2;
        if(arr.get(left).getNum()>arr.get(mid).getNum()){
            AlgorithmUtil.swap(arr,left,mid);
        }
        if(arr.get(left).getNum()>arr.get(right).getNum()){
            AlgorithmUtil.swap(arr,left,right);
        }
        if(arr.get(mid).getNum()>arr.get(right).getNum()){
            AlgorithmUtil.swap(arr,mid,right);
        }
        AlgorithmUtil.swap(arr,right-1,mid);
    }

    public static void quickSort(List<SortBO> arr,int left,int right){
        if(left<right){
            dealPivot(arr,left,right);
            int i=left;
            int j=right-1;
            int pivot=right-1;
            while (true){
                while(i<right-1 && arr.get(++i).getNum()<arr.get(pivot).getNum()){
                }
                while(j>left && arr.get(--j).getNum()>arr.get(pivot).getNum()){
                }
                if(i<j){
                    AlgorithmUtil.swap(arr,i,j);
                }else {
                    break;
                }
            }
            if (i < right) {
                AlgorithmUtil.swap(arr, i, right - 1);
            }
            quickSort(arr, left, i - 1);
            quickSort(arr, i + 1, right);
        }
    }

    public static void main(String[] args) {
        int [] arr=AlgorithmUtil.getIntData(10);
        AlgorithmUtil.display(arr);
        quickSort(arr, 0, arr.length - 1);
        AlgorithmUtil.display(arr);

        List<SortBO> arr1=AlgorithmUtil.getBOData(10);
        AlgorithmUtil.display(arr1);
        long start1=System.currentTimeMillis();
        quickSort(arr1, 0, arr1.size() - 1);
        System.out.println("快速排序耗时："+ (System.currentTimeMillis()-start1));
        AlgorithmUtil.display(arr1);
    }

}
