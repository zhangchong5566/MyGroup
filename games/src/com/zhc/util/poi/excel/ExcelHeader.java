package com.zhc.util.poi.excel;

public class ExcelHeader
{
  private String name;
  private String propertyName;
  private FieldValueTransferProcessor processor;
  public static FieldValueTransferProcessor defaultProcessor = new FieldValueTransferProcessor()
  {
    public String getExcelValue(Object fieldValue)
    {
      if (fieldValue == null) {
        return "";
      }
      return fieldValue.toString();
    }
  };
  
  public ExcelHeader(String name, String propertyName, FieldValueTransferProcessor processor)
  {
    this.name = name;
    this.propertyName = propertyName;
    this.processor = processor;
  }
  
  public ExcelHeader(String name, String propertyName)
  {
    this(name, propertyName, defaultProcessor);
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public String getPropertyName()
  {
    return this.propertyName;
  }
  
  public FieldValueTransferProcessor getProcessor()
  {
    return this.processor;
  }
}
