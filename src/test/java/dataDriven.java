import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dataDriven {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
	}
	
	public ArrayList<String> getData(String testcaseName) throws IOException {
		ArrayList<String> purchaseDataArray = new ArrayList<String>();
		FileInputStream file = new FileInputStream("C://Users//zzmar_000//Desktop//IT_Related//ExcelDriven//dummyExcelDatabase.xlsx");
		// Has access to specified file
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		// Get count of number of sheets in Excel file
		int numberOfSheets = workbook.getNumberOfSheets();
		// Finds sheet that is named sheet1
		for(int i=0; i<numberOfSheets; i++) {
			if(workbook.getSheetName(i).equalsIgnoreCase("sheet1")) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				// Identifies Testcases column by scanning the entire 1st row
				Iterator<Row> rows = sheet.iterator();
				Row firstRow = rows.next();
				Iterator<Cell> cells = firstRow.cellIterator();
				int columnIndex = 0;
				int column = 0;
				while(cells.hasNext()) {
					Cell value = cells.next();
					if(value.getStringCellValue().equalsIgnoreCase("Testcases")) {
						column = columnIndex;
					}
					columnIndex++;
				}
				// Identifies entered testcase row by scanning the entire Testcases column
				while(rows.hasNext()) {
					Row rowValue = rows.next();
					if(rowValue.getCell(column).getStringCellValue().equalsIgnoreCase(testcaseName)) {
						// pull all data from entered testcase row and feed into test
						Iterator<Cell> purchaseRow= rowValue.cellIterator();
						while(purchaseRow.hasNext()) {
							Cell currentPurchaseRowCell = purchaseRow.next();
							if(currentPurchaseRowCell.getCellType() == CellType.STRING) {
								purchaseDataArray.add(currentPurchaseRowCell.getStringCellValue());
							} else {
								purchaseDataArray.add(NumberToTextConverter.toText(currentPurchaseRowCell.getNumericCellValue()));
							}
						}
					}
				}
			}
		}
		return purchaseDataArray;
	}

}
