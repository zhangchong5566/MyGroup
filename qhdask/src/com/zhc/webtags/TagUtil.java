package com.zhc.webtags;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.PageContext;

import org.apache.commons.beanutils.PropertyUtils;

public class TagUtil {
	
	  private static TagUtil me = new TagUtil();
	  private static final Map<String, Integer> scopes = new HashMap();
	  
	  static
	  {
	    scopes.put("page", new Integer(1));
	    scopes.put("request", new Integer(2));
	    scopes.put("session", new Integer(3));
	    scopes.put("application", new Integer(4));
	  }
	  
	  public static TagUtil getInstance()
	  {
	    return me;
	  }
	  
	  public int getScope(String scopeName)
	  {
	    Integer scope = (Integer)scopes.get(scopeName.toLowerCase());
	    return scope.intValue();
	  }
	  
	  public Object lookup(PageContext pageContext, String name, String scopeName)
	  {
	    if (scopeName == null) {
	      return pageContext.findAttribute(name);
	    }
	    return pageContext.getAttribute(name, getScope(scopeName));
	  }
	  
	  public Object lookup(PageContext pageContext, String name, String property, String scopeName)
	    throws Exception
	  {
	    Object bean = lookup(pageContext, name, scopeName);
	    return PropertyUtils.getProperty(bean, property);
	  }

}
