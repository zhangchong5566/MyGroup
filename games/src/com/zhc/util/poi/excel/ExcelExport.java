package com.zhc.util.poi.excel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelExport
{
  Workbook msExcel = null;
  private boolean isExcel2007 = false;
  
  public boolean isExcel2007()
  {
    return this.isExcel2007;
  }
  
  public void setExcel2007(boolean isExcel2007)
  {
    this.isExcel2007 = isExcel2007;
  }
  
  public ExcelExport()
  {
    this.msExcel = new HSSFWorkbook();
  }
  
  public ExcelExport(boolean isExcel2007)
  {
    this.isExcel2007 = isExcel2007;
    if (!isExcel2007) {
      this.msExcel = new HSSFWorkbook();
    } else {
      this.msExcel = new XSSFWorkbook();
    }
  }
  
  public ExcelExport(InputStream in, boolean isExcel2007)
  {
    try
    {
      this.isExcel2007 = isExcel2007;
      if (!isExcel2007) {
        this.msExcel = new HSSFWorkbook(in);
      } else {
        this.msExcel = new XSSFWorkbook(in);
      }
    }
    catch (Exception ex)
    {
      System.out.println("excel读取出错!");
      ex.printStackTrace();
    }
  }
  
  public ExcelExport(InputStream in)
  {
    this(in, true);
  }
  
  public void writeData(int sheetIndex, int beginRow, List<List> rowDatas)
  {
    Sheet sheet = this.msExcel.getSheetAt(sheetIndex);
    DataFormat df = this.msExcel.createDataFormat();
    CellStyle dateStyle = this.msExcel.createCellStyle();
    dateStyle.setDataFormat(df.getFormat("yyyy-MM-dd"));
    for (int i = 0; i < rowDatas.size(); i++)
    {
      List rowData = (List)rowDatas.get(i);
      Row row = sheet.createRow(i + beginRow);
      for (int j = 0; j < rowData.size(); j++)
      {
        Object obj = rowData.get(j);
        Cell cell = row.createCell(j);
        if (obj == null)
        {
          cell.setCellValue("");
        }
        else if ((obj instanceof Integer))
        {
          cell.setCellValue(obj.toString());
        }
        else if ((obj instanceof Long))
        {
          cell.setCellValue(obj.toString());
        }
        else if ((obj instanceof Date))
        {
          cell.setCellStyle(dateStyle);
          cell.setCellValue((Date)obj);
        }
        else if ((obj instanceof Double))
        {
          cell.setCellValue(((Double)obj).doubleValue());
        }
        else
        {
          cell.setCellValue(obj.toString());
        }
      }
    }
  }
  
  public void writeBeans(int sheetIndex, int beginRow, List beans, Class c, ExcelHeader[] excelHeaders)
    throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
  {
    List<List> rowDatas = new ArrayList();
    Map<String, Method> m_methods = new HashMap();
    if (!c.equals(Map.class)) {
      m_methods = getGetterMethods(c, true);
    }
    
    
    Object value = null;
    List rowData = null;
    for (Object bean : beans)
    {
      rowData = new ArrayList();
      for (ExcelHeader excelHeader : excelHeaders)
      {
        if (c.equals(Map.class))
        {
          value = ((Map)bean).get(excelHeader.getPropertyName());
        }
        else
        {
          Method method = (Method)m_methods.get(excelHeader.getPropertyName());
          if (method == null) {
            throw new NoSuchMethodException("[" + excelHeader.getPropertyName() + "]");
          }
          value = method.invoke(bean, null);
        }
        rowData.add(excelHeader.getProcessor().getExcelValue(value));
      }
      rowDatas.add(rowData);
    }
    m_methods.clear();
    writeData(sheetIndex, beginRow, rowDatas);
  }
  
  private Map<String, Method> getGetterMethods(Class klass, boolean includeSuperClass)
  {
    Map<String, Method> m_methods = new HashMap();
    Object[] methodInfo = (Object[])null;
    Method[] methods = includeSuperClass ? klass.getMethods() : klass.getDeclaredMethods();
    for (int i = 0; i < methods.length; i++)
    {
      Method method = methods[i];
      String name = method.getName();
      String key = null;
      if (name.startsWith("get"))
      {
        key = name.substring(3, 4).toLowerCase() + name.substring(4);
        
        m_methods.put(key, method);
      }
    }
    return m_methods;
  }
  
  public void addImages(byte[] datas, int sheetIndex, int[] position)
  {
    Sheet sheet = this.msExcel.getSheetAt(sheetIndex);
    Drawing patriarch = sheet.createDrawingPatriarch();
    if (this.isExcel2007)
    {
      ClientAnchor anchor = new XSSFClientAnchor(0, 0, 255, 255, (short)position[0], position[1], (short)position[2], position[3]);
      patriarch.createPicture(anchor, this.msExcel.addPicture(datas, 5));
    }
    else
    {
      ClientAnchor anchor = new HSSFClientAnchor(0, 0, 255, 255, (short)position[0], position[1], (short)position[2], position[3]);
      patriarch.createPicture(anchor, this.msExcel.addPicture(datas, 5));
    }
  }
  
  public Sheet creatSheet()
  {
    return this.msExcel.createSheet();
  }
  
  public void writeFile(OutputStream out)
    throws IOException
  {
    this.msExcel.write(out);
  }
  
  public Workbook getMsExcel()
  {
    return this.msExcel;
  }
  
  public static void main(String[] args)
    throws Exception
  {
    ExcelExport test2 = new ExcelExport(true);
    ExcelImport test = new ExcelImport(
      new FileInputStream("e:\\xxx.xlsx"));
    
    List<List> datas = test.readData(0, 0, "*注意*");
    test2.creatSheet();
    test2.writeData(0, 2, datas);
    test2.writeFile(new FileOutputStream("e:\\test2.xlsx"));
  }
}
