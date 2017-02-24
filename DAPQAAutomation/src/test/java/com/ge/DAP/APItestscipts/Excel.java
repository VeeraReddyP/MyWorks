/*package com.ge.DAP.APItestscipts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {
	
	public List readBooksFromExcelFile(String excelFilePath) throws IOException {
	    List listBooks = new ArrayList<>();
	    FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
	 
	    Workbook workbook = null;
	    if (excelFilePath.endsWith("xlsx")) {
	        workbook = new XSSFWorkbook(inputStream);
	    } else if (excelFilePath.endsWith("xls")) {
	    	workbook = new HSSFWorkbook(inputStream);
	    }	      
	    Sheet firstSheet = workbook.getSheetAt(0);
	    Iterator<Row> iterator = firstSheet.iterator();
	 
	    while (iterator.hasNext()) {
	        Row nextRow = iterator.next();
	       // if(nextRow.getRowNum()==0){
	       // 	continue;
	       // }
	        Iterator<Cell> cellIterator = nextRow.cellIterator();
	       // Book aBook = new Book();
	 
	        while (cellIterator.hasNext()) {
	            Cell nextCell = cellIterator.next();
	            int columnIndex = nextCell.getColumnIndex();
	 
	            switch (columnIndex) {
	            case 1:
	                aBook.setTitle((String) getCellValue(nextCell));
	                break;
	            case 2:
	                aBook.setAuthor((String) getCellValue(nextCell));
	                break;
	            case 3:
	                aBook.setPrice((String) getCellValue(nextCell));
	                break;
	            }
	 
	 
	        }
	        
	            while (cellIterator.hasNext()) {
	                Cell cell = cellIterator.next();
	                 
	                switch (cell.getCellType()) {
	                    case Cell.CELL_TYPE_STRING:	  
	                    	//listBooks.add(cell.getStringCellValue());
	                        //System.out.print(cell.getStringCellValue());
	                        break;
	                    case Cell.CELL_TYPE_BOOLEAN:
	                        //System.out.print(cell.getBooleanCellValue());
	                        break;
	                    case Cell.CELL_TYPE_NUMERIC:
	                       // System.out.print(cell.getNumericCellValue());
	                        break;
	                }
	                listBooks.add(cell); 
	            }
	        
	    }
	 
	   // workbook.close();
	    inputStream.close();
	 
	    return listBooks;
	
	}
	private Object getCellValue(Cell cell) {
	    switch (cell.getCellType()) {
	    case Cell.CELL_TYPE_STRING:
	        return cell.getStringCellValue();
	 
	    case Cell.CELL_TYPE_BOOLEAN:
	        return cell.getBooleanCellValue();
	 
	    case Cell.CELL_TYPE_NUMERIC:
	        return cell.getNumericCellValue();
	    }
	 
	    return null;
	}

	public static void main(String[] args) throws IOException  {
		
		String excelFilePath = "/Users/veerareddy/Desktop/test.xlsx";
		Excel reader = new Excel();
	    List<Book> listBooks = reader.readBooksFromExcelFile(excelFilePath);
	    System.out.println(listBooks);
		
        
        
	}       
}
*/
