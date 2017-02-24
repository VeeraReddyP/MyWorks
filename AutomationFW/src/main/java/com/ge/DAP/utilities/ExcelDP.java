package com.ge.DAP.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

import jxl.Sheet;
import jxl.Workbook;

/**
 * @author Veera
 *
 * 
 */
public class ExcelDP {
	
	public static String[][] getTableArray(String filename,String sheetName) throws Exception{
		try{			
			String FilePath = System.getProperty("user.dir")+"/src/test/resources/"+filename+".xls";
			FileInputStream fs = new FileInputStream(FilePath);
			Workbook workbook = Workbook.getWorkbook(fs);
			Sheet sheet = workbook.getSheet(sheetName);
			int rows = sheet.getRows();
			int cols = sheet.getColumns();
			String[][] tabArray=new String[rows-1][cols];
			for (int i=1;i<rows;i++){
				for (int j=0;j<cols;j++){
				tabArray[i-1][j]=sheet.getCell(j,i).getContents();
				}
			}
			workbook.close();
			return(tabArray);
		}
		catch (Exception e) {
			System.out.println(e+Thread.currentThread().getStackTrace()[1].getClassName()+" dataprovider");
			return null;
		}
	}
	
	public static Object[][] tabledata(String filename,String sheetName) throws IOException{
		Object[][] data;
		String FilePath = System.getProperty("user.dir")+"/src/test/resources/"+filename+".xls";
		FileInputStream fs = new FileInputStream(FilePath);
		 HSSFWorkbook workbook = new HSSFWorkbook(fs);
		 org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheet(sheetName);
		 int noOfColumns = sheet.getRow(0).getLastCellNum();
		 System.out.println("noOfColumns:::::: "+noOfColumns);
		 int numberOfRows = sheet.getLastRowNum() ;
		 System.out.println("numberOfRows:::::: "+numberOfRows);
		 data = new Object[numberOfRows - 1][noOfColumns-1 ];
		 System.out.println(data);
		 for (int rowNum = 1; rowNum < numberOfRows; rowNum++) {
	            Row row = sheet.getRow(rowNum);
	            if (isEmpty(row)) {
	                break;
	            } else {
	                for (int column = 1; column < noOfColumns; column++) {
	                    Cell cell = row.getCell(column);
	                    if (cell == null
	                            || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
	                        data[rowNum - 1][column-1] = "";
	                    } else {
	                        data[rowNum - 1][column-1] = objectFrom(workbook,
	                                cell);
	                    }
	                }
	            }
	        }
		 
		
		return data;
		
		
	}
	 private static boolean isEmpty(Row row) {
	        // TODO Auto-generated method stub
	        Cell firstCell = row.getCell(0);
	        boolean rowIsEmpty = (firstCell == null)
	                || (firstCell.getCellType() == Cell.CELL_TYPE_BLANK);
	        return rowIsEmpty;
	    }
	 private static Object objectFrom(HSSFWorkbook workbook, Cell cell) {
	        // TODO Auto-generated method stub
	        Object cellValue = null;
	        if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
	            cellValue = cell.getRichStringCellValue().getString();
	        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
	            cellValue = getNumericCellValue(cell);
	        } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
	            cellValue = cell.getBooleanCellValue();
	        } else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
	            cellValue = evaluateCellFormula(workbook, cell);
	        }

	        return cellValue;
	    }
	 private static Object getNumericCellValue(final Cell cell) {
	        Object cellValue;
	        if (DateUtil.isCellDateFormatted(cell)) {
	            cellValue = new Date(cell.getDateCellValue().getTime());
	        } else {
	            cellValue = cell.getNumericCellValue();
	        }
	        return cellValue;
	    }
	 private static Object evaluateCellFormula(final HSSFWorkbook workbook,
	            final Cell cell) {
	        FormulaEvaluator evaluator = workbook.getCreationHelper()
	                .createFormulaEvaluator();
	        CellValue cellValue = evaluator.evaluate(cell);
	        Object result = null;

	        if (cellValue.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
	            result = cellValue.getBooleanValue();
	        } else if (cellValue.getCellType() == Cell.CELL_TYPE_NUMERIC) {
	            result = cellValue.getNumberValue();
	        } else if (cellValue.getCellType() == Cell.CELL_TYPE_STRING) {
	            result = cellValue.getStringValue();
	        }

	        return result;
	    }
	 
	 public void hssf() throws IOException{
		 String FilePath = System.getProperty("user.dir")+"/src/test/resources/Api_testdata.xls";
		 FileInputStream fs = new FileInputStream(FilePath);
		 HSSFWorkbook workbook = new HSSFWorkbook(fs);
		 HSSFSheet sheet = workbook.getSheetAt(1);
		 
		 int numberOfColumns = sheet.getRow(0).getLastCellNum();
		
		 int numberOfRows = sheet.getLastRowNum() ;
		 Object[][] data = new Object[numberOfRows - 1][numberOfColumns - 1];

		 Iterator<Row> rowIterator = sheet.iterator();
		 
		 while(rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while(cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch(cell.getCellType()) {
					case Cell.CELL_TYPE_BOOLEAN:
						System.out.print(cell.getBooleanCellValue() + "\t\t");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						System.out.print(cell.getNumericCellValue() + "\t\t");
						break;
					case Cell.CELL_TYPE_STRING:
						System.out.print(cell.getStringCellValue() + "\t\t");
						break;
				}
				}
				System.out.println("");
				
	 }
		 fs.close();
	 }

}
