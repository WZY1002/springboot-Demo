package com.myproject.myspring.servlet;

import com.myproject.myspring.annocation.*;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.springframework.util.ObjectUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static jdk.nashorn.api.scripting.ScriptUtils.convert;

public class MyDispatcherServlet extends HttpServlet{

    /**
     * 所有的资源信息都存入了Properties
     * @author wzy
     * @return
     * @date 2018/9/12
     **/
    private Properties properties=new Properties();

    //存放全类名的容器
    private List<String> classNames=new ArrayList<>();

    //存放类名和实例的ioc容器
    private Map<String,Object> ioc=new HashMap<>();

    //存放访问路径和方法的容器
//    private Map<String,Object> handlerMapping=new HashMap<>();

    //改良存放访问路径和方法的容器
    private List<Handler> handlerMapping=new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    /**
     * 等待请求，进入运行阶段
     * @author wzy
     * @return
     * @date 2018/9/12
     **/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求路径
        String url=req.getRequestURI();
        String contextPath=req.getContextPath();
        url=url.replaceAll(contextPath,"").replaceAll("/+","/");

        Handler handler=getHandler(req);
        if(handler==null){
            resp.getWriter().write("404 NOT FOUNT");
            return;
        }
        //获取方法的参数列表
        Class<?>[] parameterTypes = handler.method.getParameterTypes();
        //保存所有需要自动赋值的参数值
        Object[] paramValues=new Object[parameterTypes.length];
        //getParameterMap
        Map<String,String[]> params=req.getParameterMap();
        for (Map.Entry<String,String[]> param:params.entrySet()){
            String value=Arrays.toString(param.getValue()).replaceAll("\\[|\\]]","").replaceAll(",\\s",",");
            //如果找到匹配的对象，则开始填充参数值
            if(!handler.paramIndexMapping.containsKey(param.getKey())){
                continue;
            }
            int index=handler.paramIndexMapping.get(param.getKey());
            paramValues[index]=convert(parameterTypes[index],value);
        }
        //设置方法中的request和reponse对象
        int reqIndex=handler.paramIndexMapping.get(HttpServletRequest.class.getName());
        paramValues[reqIndex]=req;
        int respIndex=handler.paramIndexMapping.get(HttpResponse.class.getName());
        paramValues[respIndex]=resp;
        try {
            handler.method.invoke(handler.controller,paramValues);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    private Handler getHandler(HttpServletRequest req) {
        if(handlerMapping.isEmpty()){
            return null;
        }
        String url=req.getRequestURI();
        String contextPath=req.getContextPath();
        url=url.replace(contextPath,"").replaceAll("/+","/");
        for(Handler handler:handlerMapping){
            //匹配正则
            Matcher matcher=handler.pattern.matcher(url);
            //如果没有匹配上继续下一个匹配
            if(!matcher.matches()){continue;}
            return handler;
        }
        return null;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        //加载配置
        //doLoadConfig(config.getInitParameter("contextConfigLocation"));
        //根据配置文件扫描所有相关的类
        doScanner("com.myproject.myspring");
        //初始化所有相关类的实例，并将其加入容器(即map)中
        doInstanace();
        //实现自动依赖注入
        doAutowried();
        //初始化HandlerMapper
        initHandlerMapper();
        System.out.println("---- My Spring ----");
    }

    private void initHandlerMapper() {
        if(ioc.isEmpty()){
            return;
        }
        for (Map.Entry<String,Object> entry:ioc.entrySet()){
            Class<?> aClass = entry.getValue().getClass();
            //判断是否存在Contorller注解
            if(!aClass.isAnnotationPresent(MyContorller.class)){
                continue;
            }
            String baseUrl="";
            if(aClass.isAnnotationPresent(MyRequestMapping.class)){
                //存在Controller注解，并且存在RequestMapping注解
                //保存controller的url配置
                MyRequestMapping requestMapping=aClass.getAnnotation(MyRequestMapping.class);
                baseUrl=requestMapping.value();
            }
            //遍历所有方法,获取Method的url配置
            Method[] methods = aClass.getMethods();
            for (Method method:methods){
                if(method.isAnnotationPresent(MyRequestMapping.class)){
                    //方法上有RequestMapping注解，保存方法路径
                    MyRequestMapping requestMapping=method.getAnnotation(MyRequestMapping.class);
                    String url=("/"+baseUrl+requestMapping.value()).replaceAll("/+","/");
                    Pattern pattern=Pattern.compile(url);
                    handlerMapping.add(new Handler(entry.getValue(),pattern,method));
                    //handlerMapping.put(url,method);
                }
            }
        }

    }

    private void doAutowried() {
        if(ioc.isEmpty()){
            return;
        }
        for (Map.Entry<String,Object> entry:ioc.entrySet()){
            //第一步，首先获取到所有的字段field
            Field[] fields=entry.getValue().getClass().getFields();
            //遍历所有元数据
            for (Field field:fields){
                if (!field.isAnnotationPresent(MyAutowried.class)) {
                    continue;
                }
                //若有@Autowried注解则赋值(自动注入)
                MyAutowried autowried=field.getAnnotation(MyAutowried.class);
                //有自定义名字，则给自定义名字的beanName赋值(注入)
                String beanName=autowried.value().trim();
                if("".equals(beanName)){
                    //是默认值的话，可能是接口，给默认用类名首字母小写的beanName注入
                    beanName=field.getName();
                }
                //要给私有的或者受保护的类赋值，需要强制授权
                field.setAccessible(true);
                try {
                    //为该对象的field属性赋值(注入实例)
                    field.set(entry.getValue(),ioc.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    continue;
                }
            }
        }
    }

    private void doInstanace() {
        if(ObjectUtils.isEmpty(classNames)){
            return;
        }
        //不为空，利用反射将扫描到的所有className初始化
        for (String className:classNames){
            try {
                Class aClass=Class.forName(className);
                //Bean实例化阶段，初始化ioc容器
                //ioc容器规则
                //1.key默认用类名首字母小写
                //根据不同注解进行实例化放入ioc容器中
                if(aClass.isAnnotationPresent(MyContorller.class)){
                    String beanName=lowerFirstCase(aClass.getSimpleName());
                    ioc.put(beanName,aClass.newInstance());
                }else if (aClass.isAnnotationPresent(MyService.class)){
                    //2.如果用户自定义名字，首先用自定义名字
                    MyService servie= (MyService) aClass.getAnnotation(MyService.class);
                    String beanName=servie.value();
                    if("".equals(beanName.trim())){
                        beanName=lowerFirstCase(aClass.getSimpleName());
                    }
                    ioc.put(beanName,aClass.newInstance());
                    //3.如果是接口的话，可以用接口类型做key
                    Class[] interfaces = aClass.getInterfaces();
                    for (Class c:interfaces){
                        ioc.put(c.getSimpleName(),aClass.newInstance());
                    }
                }else {

                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    private void doScanner(String packagename) {
        //递归扫描包
        URL url=this.getClass().getClassLoader().getResource(packagename);
        File dir=new File(url.getFile());
        for (File file:dir.listFiles()) {
            if(file.isDirectory()){
                doScanner(packagename+"."+file.getName());
            }else {
                String className=packagename+"."+file.getName().replaceAll(".class","");
                classNames.add(className);
            }
        }
    }

    private void doLoadConfig(String contextConfigLocation) {

    }

    /**
     * 首字母小写
     * @author wzy
     * @return
     * @date 2018/9/12
     **/
    private String lowerFirstCase(String str){
        char[] chars=str.toCharArray();
        chars[0] = (char) (chars[0] + 32);
        return String.valueOf(chars);
    }

    /**
     * 存放访问路径和方法内部实体
     * @author wzy
     * @return 
     * @date 2018/9/13
     **/
    private class Handler {
        protected Object controller; //保存方法对应的实例
        protected  Method method; //保存映射的方法
        protected Pattern pattern;
        protected Map<String,Integer> paramIndexMapping;//参数顺序

        protected Handler(Object controller, Pattern pattern, Method method) {
            this.controller = controller;
            this.method = method;
            this.pattern = pattern;

            paramIndexMapping = new HashMap<>();
            putParamIndexMapping(method);
        }
        private void putParamIndexMapping(Method method) {
            //获取方法里的参数注解
            Annotation[][] annotations = method.getParameterAnnotations();
            for (int i=0;i<annotations.length;i++){
                for (Annotation a:annotations[i]){
                    if(a instanceof MyRequestParam){
                        String paramName=((MyRequestParam) a).value();
                        if(!("".equals(paramName))){
                            paramIndexMapping.put(paramName,i);
                        }
                    }
                }
            }
            //提取方法中的request参数和response参数
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (int i=0;i<parameterTypes.length;i++) {
                Class<?> c=parameterTypes[i];
                if(c.equals(HttpRequest.class) || c.equals(HttpResponse.class)){
                    paramIndexMapping.put(c.getName(),i);
                }
            }
        }
    }


}
