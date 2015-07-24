package com.zhc.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

public class ConfigFileReader
{
  private Hashtable paramTable;
  private String conf_filename;
  
  public ConfigFileReader(String conf_filename)
    throws FileNotFoundException, IOException
  {
    this.conf_filename = conf_filename;
    loadFromFile(conf_filename);
  }
  
  public String getConfFilename()
  {
    return this.conf_filename;
  }
  
  public String getStrValue(String name)
  {
    Object obj = this.paramTable.get(name);
    if (obj == null) {
      return null;
    }
    if ((obj instanceof String)) {
      return (String)obj;
    }
    return (String)((ArrayList)obj).get(0);
  }
  
  public int getIntValue(String name, int default_value)
  {
    String szValue = getStrValue(name);
    if (szValue == null) {
      return default_value;
    }
    return Integer.parseInt(szValue);
  }
  
  public boolean getBoolValue(String name, boolean default_value)
  {
    String szValue = getStrValue(name);
    if (szValue == null) {
      return default_value;
    }
    return (szValue.equalsIgnoreCase("yes")) || (szValue.equalsIgnoreCase("on")) || (szValue.equalsIgnoreCase("true")) || (szValue.equals("1"));
  }
  
  public String[] getValues(String name)
  {
    Object obj = this.paramTable.get(name);
    if (obj == null) {
      return null;
    }
    if ((obj instanceof String))
    {
      String[] values = new String[1];
      values[0] = ((String)obj);
      return values;
    }
    Object[] objs = ((ArrayList)obj).toArray();
    String[] values = new String[objs.length];
    System.arraycopy(objs, 0, values, 0, objs.length);
    return values;
  }
  
  private void loadFromFile(String conf_filename)
    throws FileNotFoundException, IOException
  {
    FileReader fReader = new FileReader(conf_filename);
    BufferedReader buffReader = new BufferedReader(fReader);
    this.paramTable = new Hashtable();
    try
    {
      String line;
      while ((line = buffReader.readLine()) != null)
      {
        line = line.trim();
        if ((line.length() != 0) && (line.charAt(0) != '#'))
        {
          String[] parts = line.split("=", 2);
          if (parts.length == 2)
          {
            String name = parts[0].trim();
            String value = parts[1].trim();
            
            Object obj = this.paramTable.get(name);
            if (obj == null)
            {
              this.paramTable.put(name, value);
            }
            else if ((obj instanceof String))
            {
              ArrayList valueList = new ArrayList();
              valueList.add(obj);
              valueList.add(value);
              this.paramTable.put(name, valueList);
            }
            else
            {
              ArrayList valueList = (ArrayList)obj;
              valueList.add(value);
            }
          }
        }
      }
    }
    finally
    {
      fReader.close();
    }
  }
}
