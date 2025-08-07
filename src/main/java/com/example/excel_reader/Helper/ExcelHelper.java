package com.example.excel_reader.Helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.example.excel_reader.model.Tutorial;

public class ExcelHelper {
	  public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	  static String[] HEADERs = { "Id", "Title", "Description", "Published" };
	  static String SHEET = "Tutorials";
	  
	  public static boolean hasExcelFormat(MultipartFile file) {
		  System.out.println("Content Type is : "+ file.getContentType());
		  return TYPE.equals(file.getContentType());
	  }
	  
	  public static List<Tutorial> excelToTurial(InputStream is){
		  try {
			 
			  XSSFWorkbook workbook = new XSSFWorkbook(is);

		      XSSFSheet sheet = workbook.getSheet(SHEET);
		      Iterator<Row> rows = sheet.iterator();
		      List<Tutorial> tutorials = new ArrayList<Tutorial>();
		      int rowNumber = 0;
		      while(rows.hasNext()) {
		    	  Row currentRow = rows.next();
		    	  //skipping header
		    	  if(rowNumber == 0) {
		    		  rowNumber++;
		    		  System.out.println("row number : "+rowNumber);
		    		  continue;
		    	  }
		    	  
		    	  Iterator<Cell> cellsInRow = currentRow.iterator();
		    	  Tutorial tutorial = new Tutorial();
		    	  int cellIdx = 0;
		    	  
		    	  while(cellsInRow.hasNext()) {
		    		  Cell currentCell = cellsInRow.next();
		    		  switch(cellIdx) {
			    		  case 0 -> tutorial.setId((long) currentCell.getNumericCellValue());
	                      case 1 -> tutorial.setTitle(currentCell.getStringCellValue());
	                      case 2 -> tutorial.setDescription(currentCell.getStringCellValue());
	                      case 3 -> tutorial.setPublished(currentCell.getBooleanCellValue());
	                      default -> {}
		    		  }
		    		  cellIdx++;
		    	  }
		    	  tutorials.add(tutorial);
		      }
		      return tutorials;
		  }catch(IOException e) {
			  throw new RuntimeException("Fail to parse ExcelFile"+ e.getMessage());
		  }
	  }

	    
	  

}
