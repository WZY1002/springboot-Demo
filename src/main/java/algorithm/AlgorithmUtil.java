package algorithm;

import algorithm.model.SortBO;

import java.util.ArrayList;
import java.util.List;

/**
 * 算法工具类
 * @author wzy
 * @date 2018/11/23
 **/
public class AlgorithmUtil {
    public static int temp,index = 0;
    public static SortBO BOtemp=null;

    /**
     * 临时值交换
     *
     * @param datas
     *            数组
     * @param i
     * @param j
     */
    public static void swap(int[] datas, int i, int j) {
        temp = datas[i];
        datas[i] = datas[j];
        datas[j] = temp;
    }
    public static void swap(List<SortBO> boList, int i, int j) {
//        BOtemp=new SortBO();
        BOtemp = boList.get(i);
        boList.set(i,boList.get(j));
        boList.set(j,BOtemp);
    }

    public static int [] getIntData(int size){
        int [] arr=new int[size];
        for (int i=0;i<size;i++){
            int x=(int)(Math.random()*100);
            arr[i]=x;
        }
        return arr;
    }

    public static List<SortBO> getBOData(int size){
        List<SortBO> boList=new ArrayList<>();
        for (int i=0;i<size;i++){
            int x=(int)(Math.random()*100);
            SortBO sortBO =new SortBO();
            sortBO.setNum(x);
            sortBO.setName(i+"号");
            boList.add(sortBO);
        }
        return boList;
    }

    /**
     * 扩充数组长度
     *
     * @param datas
     * @param value
     * @return
     */
    public static int[] expandArray(int[] datas, int value) {
        if (datas.length <= index) {
            int[] arrays = new int[datas.length * 2];
            System.arraycopy(datas, 0, arrays, 0, datas.length);
            datas = arrays;
        }
        datas[index] = value;
        index++;
        return datas;
    }

    public static void display(int[] datas){
        for (int i:datas){
            System.out.print(i+" ");
        }
        System.out.println("");
    }
    public static void display(List<SortBO> boList){
        for (SortBO i:boList){
            System.out.print(i.getName()+" : "+i.getNum()+"    ");
        }
        System.out.println("");
    }
}
