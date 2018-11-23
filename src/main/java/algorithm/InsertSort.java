package algorithm;

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

    public static void main(String[] args) {
        System.out.println("====================基本类型插入排序==================");
        int [] arr=AlgorithmUtil.getIntData(100);
        AlgorithmUtil.display(arr);
        long start=System.currentTimeMillis();
        insertSort(arr);
        System.out.println("基本类型插入排序耗时  "+ (System.currentTimeMillis()-start));
        AlgorithmUtil.display(arr);
    }
}
