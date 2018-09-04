package RPC.myrpc.model;

import java.io.Serializable;
import java.util.Arrays;

public class PRCTanslator  implements Serializable{

    private static final long serialVersionUID = -1486631166196309756L;

    /**
     * 调用，要生成代理类的Class类型
     */
    private Class serviceClass;
    /**
     * 参数类型
     */
    private Class[] paramsTypes;
    /**
     * 参数
     */
    private Object[] paramsValue;

    /**
     * 要调用的方法名
     */
    private String methodName;

    public Class getServiceClass() {
        return serviceClass;
    }

    public void setServiceClass(Class serviceClass) {
        this.serviceClass = serviceClass;
    }

    public Class[] getParamsTypes() {
        return paramsTypes;
    }

    public void setParamsTypes(Class[] paramsTypes) {
        this.paramsTypes = paramsTypes;
    }

    public Object[] getParamsValue() {
        return paramsValue;
    }

    public void setParamsValue(Object[] paramsValue) {
        this.paramsValue = paramsValue;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public String toString() {
        return "PRCTanslator{" +
                "serviceClass=" + serviceClass +
                ", paramsTypes=" + Arrays.toString(paramsTypes) +
                ", paramsValue=" + Arrays.toString(paramsValue) +
                ", methodName='" + methodName + '\'' +
                '}';
    }
}
