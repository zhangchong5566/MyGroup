package com.zhc.util.poi.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelImport {
	Workbook msExcel = null;

	public ExcelImport() {
	}

	public ExcelImport(InputStream in) {
		try {
			this.msExcel = WorkbookFactory.create(in);
		} catch (Exception ex) {
			System.out.println("excel读取出错!");
			ex.printStackTrace();
		}
	}

	public List<List> readData(int sheetIndex, int beginRow, String ignorechars) {
		Sheet sheet = this.msExcel.getSheetAt(sheetIndex);
		List<List> rowDatas = new ArrayList();

		int rows = sheet.getLastRowNum() + 1;

		label363: for (int j = beginRow; j < rows; j++) {
			Row row = sheet.getRow(j);
			if (row != null) {
				List rowData = new ArrayList();
				int cells = row.getLastCellNum();
				for (int k = 0; k < cells; k++) {
					Cell cell = row.getCell(k);
					if (cell != null) {
						switch (cell.getCellType()) {
						case 0:
							if (DateUtil.isCellDateFormatted(cell)) {
								rowData.add(DateUtil.getJavaDate(cell
										.getNumericCellValue()));
							} else {
								rowData.add(Double.valueOf(cell
										.getNumericCellValue()));
							}
							break;
						case 1:
							if (StringUtils.isNotBlank(ignorechars)
									&& ignorechars.equals(cell
											.getStringCellValue().trim())) {
								break label363;
							}
							rowData.add(cell.getRichStringCellValue()
									.toString());

							break;
						case 2:
							rowData.add(Double.valueOf(cell
									.getNumericCellValue()));

							break;
						case 4:
							rowData.add(Boolean.valueOf(cell
									.getBooleanCellValue()));

							break;
						case 3:
							rowData.add("");
							break;
						case 5:
							rowData.add("");
							break;
						default:
							rowData.add(cell.getRichStringCellValue()
									.toString());
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

	public Workbook getMsExcel() {
		return this.msExcel;
	}

	public void setMsExcel(InputStream in) {
		try {
			this.msExcel = WorkbookFactory.create(in);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		ExcelImport test = new ExcelImport(new FileInputStream(
				"C:\\Users\\Administrator\\Desktop\\games.xlsx"));
		List<List> beans = test.readData(0, 0, "*注意*");
		for (List bean : beans) {
			System.out.println(bean.size());
			for (Object o : bean) {
				System.out.print("  ");
				System.out.print(o);
			}
			System.out.println();
		}
	}
}
