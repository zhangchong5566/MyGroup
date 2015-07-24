package com.zhc.util;

import java.lang.reflect.Method;
import java.math.BigDecimal;

import org.springframework.web.context.WebApplicationContext;


public class BeanUtil {
	private static BeanUtil me = null;
	WebApplicationContext tempContext = null;

	public static BeanUtil getInstance() {
		if (me == null) {
			me = new BeanUtil();
		}
		return me;
	}

	public void setAppContext(WebApplicationContext context) {
		this.tempContext = context;
	}

	public Object getBean(String name) {
		return this.tempContext.getBean(name);
	}

	public WebApplicationContext getAppContext() {
		return this.tempContext;
	}

	/**
	 * @parameter Object obj1,Object obj2
	 * @return Object
	 */
	public static Object CopyBeanToBean(Object obj1, Object obj2)
			throws Exception {
		Method[] method1 = obj1.getClass().getMethods();
		Method[] method2 = obj2.getClass().getMethods();
		String methodName1;
		String methodFix1;
		String methodName2;
		String methodFix2;
		for (int i = 0; i < method1.length; i++) {
			methodName1 = method1[i].getName();
			methodFix1 = methodName1.substring(3, methodName1.length());
			if (methodName1.startsWith("get")) {
				for (int j = 0; j < method2.length; j++) {
					methodName2 = method2[j].getName();
					methodFix2 = methodName2.substring(3, methodName2.length());
					if (methodName2.startsWith("set")) {
						if (methodFix2.equals(methodFix1)) {
							Object[] objs1 = new Object[0];
							Object[] objs2 = new Object[1];
							objs2[0] = method1[i].invoke(obj1, objs1);// 激活obj1的相应的get的方法，objs1数组存放调用该方法的参数,此例中没有参数，该数组的长度为0
							method2[j].invoke(obj2, objs2);// 激活obj2的相应的set的方法，objs2数组存放调用该方法的参数
							continue;

						}
					}
				}
			}
		}
		return obj2;
	}

}
