package com.zhc.util;

import java.util.Properties;

public class AppProps {
	
	  public static final String ID_NAME = "appProps";
	  private Properties properties;
	  
	  public void setProperties(Properties properties)
	  {
	    this.properties = properties;
	  }
	  
	  public Object get(Object key)
	  {
	    return this.properties.get(key);
	  }
	  
	  public Object put(Object key, Object value)
	  {
	    return this.properties.put(key, value);
	  }
	  
	  public Object remove(Object key)
	  {
	    return this.properties.remove(key);
	  }
	  
	  public Properties getProperties()
	  {
	    return this.properties;
	  }

}
