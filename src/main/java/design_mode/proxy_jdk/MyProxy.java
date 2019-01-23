package design_mode.proxy_jdk;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * JDK Proxy产生代理类对象的方法
 * @author wzy
 * @return
 * @date 2018/8/10
 **/
public class MyProxy {
	//用来产生新的代理类
	public static Object newProxyInstance(Class infce, InvocationHandler h) throws Exception { //JDK6 Complier API, CGLib, ASM
		String methodStr = "";
		String rt = "\r\n";
		
		Method[] methods = infce.getMethods();
		/*
		for(Method m : methods) {
			methodStr += "@Override" + rt + 
						 "public void " + m.getName() + "() {" + rt +
						 	"   long start = System.currentTimeMillis();" + rt +
							"   System.out.println(\"starttime:\" + start);" + rt +
							"   t." + m.getName() + "();" + rt +
							"   long end = System.currentTimeMillis();" + rt +
							"   System.out.println(\"time:\" + (end-start));" + rt +
						 "}";
		}
		*/

		//打印被代理类的所有方法
		for(Method m : methods) {
			methodStr += "@Override" + rt + 
						 "public void " + m.getName() + "() {" + rt +
						 "    try {" + rt +
						 "    Method md = " + infce.getName() + ".class.getMethod(\"" + m.getName() + "\");" + rt +
						 "    h.invoke(this, md);" + rt +
						 "    }catch(Exception e) {e.printStackTrace();}" + rt +

						 "}";
		}

		//创建继承被代理类接口的类$Proxy1，并将被代理类的所有方法实现进去
		String src = 
			"package com.bjsxt.proxy;" +  rt +
			"import java.lang.reflect.Method;" + rt +
			"public class $Proxy1 implements " + infce.getName() + "{" + rt +
			"    public $Proxy1(InvocationHandler h) {" + rt +
			"        this.h = h;" + rt +
			"    }" + rt +
			
			
			"    com.bjsxt.proxy.InvocationHandler h;" + rt +
							
			methodStr +
			"}";
		//生成$Proxy1.java文件
		String fileName = 
			"d:/src/com/bjsxt/proxy/$Proxy1.java";
		File f = new File(fileName);
		FileWriter fw = new FileWriter(f);
		fw.write(src);
		fw.flush();
		fw.close();

		//JavaCompiler是java编译器，调用getSystemJavaCompiler拿到系统当前默认的编译器
		//拿到编译器compiler
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		//拿到fileMgr管理者，来管理文件
		StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);
		//拿到java文件的对象
		Iterable units = fileMgr.getJavaFileObjects(fileName);
		//获取一次编译任务
		CompilationTask t = compiler.getTask(null, fileMgr, null, null, null, units);
		//调用编译任务，完成编译
		t.call();
		fileMgr.close();
		
		//load into memory and create an instance
		//load到内存，生成新对象
		//指定路径提过load
		URL[] urls = new URL[] {new URL("file:/" + "d:/src/")};
		//ClassLoader指将硬盘上的java文件放到内存里的类
		URLClassLoader ul = new URLClassLoader(urls);
		//这个类$Proxy1的二进制码对象class找出来
		Class c = ul.loadClass("com.bjsxt.proxy.$Proxy1");
		System.out.println(c);

		//拿到一个构造方法，构造方法的参数类型是InvocationHandler类型,ctr即是形参为InvocationHandler类型的构造方法
		Constructor ctr = c.getConstructor(InvocationHandler.class);
		//产生一个新的对象，因为构造方法里需要参数，所有需要参数类型为InvocationHandler的h
		Object m = ctr.newInstance(h);
		//m.move();

		return m;
	}
}
