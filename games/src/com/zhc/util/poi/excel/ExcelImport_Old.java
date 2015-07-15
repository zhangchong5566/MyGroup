package com.zhc.util.poi.excel;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelImport_Old
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
  
  public ExcelImport_Old()
  {
    this.msExcel = new HSSFWorkbook();
  }
  
  public ExcelImport_Old(boolean isExcel2007)
  {
    this.isExcel2007 = isExcel2007;
    if (!isExcel2007) {
      this.msExcel = new HSSFWorkbook();
    } else {
      this.msExcel = new XSSFWorkbook();
    }
  }
  
  public ExcelImport_Old(InputStream in, boolean isExcel2007)
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
  
  public ExcelImport_Old(InputStream in)
  {
    this(in, true);
  }
  
  public List<List> readData(int sheetIndex, int beginRow, String ignorechars)
  {
    Sheet sheet = this.msExcel.getSheetAt(sheetIndex);
    List<List> rowDatas = new ArrayList();
    
    

    int rows = sheet.getLastRowNum() + 1;
    
    label363:
    for (int j = beginRow; j < rows; j++)
    {
      Row row = sheet.getRow(j);
      if (row != null)
      {
        List rowData = new ArrayList();
        int cells = row.getLastCellNum();
        for (int k = 0; k < cells; k++)
        {
          Cell cell = row.getCell(k);
          if (cell != null) {
            switch (cell.getCellType())
            {
            case 0: 
              if (DateUtil.isCellDateFormatted(cell)) {
                rowData.add(DateUtil.getJavaDate(cell.getNumericCellValue()));
              } else {
                rowData.add(Double.valueOf(cell.getNumericCellValue()));
              }
              break;
            case 1: 
              if (StringUtils.isNotBlank(ignorechars) && ignorechars.equals(cell.getStringCellValue().trim())) {
                break label363;
              }
              rowData.add(cell.getRichStringCellValue().toString());
              
              break;
            case 2: 
              rowData.add(Double.valueOf(cell.getNumericCellValue()));
              
              break;
            case 4: 
              rowData.add(Boolean.valueOf(cell.getBooleanCellValue()));
              
              break;
            case 3: 
              rowData.add("");
              break;
            case 5: 
              rowData.add("");
              break;
            default: 
              rowData.add(cell.getRichStringCellValue().toString()); 
              break;
            }
          } else {
            rowData.add(null);
          }
        }
        rowDatas.add(rowData);
      }
    }
   
    return rowDatas;
  }
  
  public Workbook getMsExcel()
  {
    return this.msExcel;
  }
  
  public static void main(String[] args)
    throws Exception
  {
	ExcelImport_Old test = new ExcelImport_Old(new FileInputStream("e:\\xxx.xlsx"), true);
    List<List> beans = test.readData(0, 0, "*注意*");
    for (List bean : beans)
    {
      System.out.println(bean.size());
      for (Object o : bean)
      {
        System.out.print("  ");
        System.out.print(o);
      }
      System.out.println();
    }
  }
}
