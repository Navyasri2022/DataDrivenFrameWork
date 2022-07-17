package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExelFileUtil {
XSSFWorkbook wb;
//constructor for reading exel file
public ExelFileUtil(String exelpath)throws Throwable 
{
	FileInputStream fi = new FileInputStream(exelpath);
	wb = new XSSFWorkbook(fi);
}
//method for count rows
public int rowCount(String sheetName) 
{
return wb.getSheet(sheetName).getLastRowNum();
}
//method for count cell in a row
public int cellCount(String sheetname) {
	return wb.getSheet(sheetname).getRow(0).getLastCellNum();
}
//method for cell data
public String getcelldata(String sheetname,int row,int column)
{
	String data= "";
if (wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC) {
	int celldata = (int)wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
	data=String.valueOf(celldata);
}
else {
	data=wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
}
return data;
}
//method for set cell data
public void setcelldata(String sheetname,int row,int column,String status,String writeexel) throws Throwable {
//get sheet from workbook
XSSFSheet ws = wb.getSheet(sheetname);
//get row from sheet
XSSFRow rowNum = ws.getRow(row);
//get cell from row
XSSFCell cell = rowNum.createCell(column);
// write status
cell.setCellValue(status);
if (status.equalsIgnoreCase("Pass")) {
XSSFCellStyle style = wb.createCellStyle();
XSSFFont font = wb.createFont();
font.setColor(IndexedColors.BRIGHT_GREEN.getIndex());
font.setBold(true);
font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
style.setFont(font);
rowNum.getCell(column).setCellStyle(style);
}
else if (status.equalsIgnoreCase("Fail")) {
	XSSFCellStyle style = wb.createCellStyle();
	XSSFFont font = wb.createFont();
	font.setColor(IndexedColors.RED.getIndex());
	font.setBold(true);
	font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
	style.setFont(font);
	rowNum.getCell(column).setCellStyle(style);
}
else if(status.equalsIgnoreCase("Blocked"))
{
	XSSFCellStyle style = wb.createCellStyle();
	XSSFFont font = wb.createFont();
	font.setColor(IndexedColors.BLUE_GREY.getIndex());
	font.setBold(true);
	font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
	style.setFont(font);
	rowNum.getCell(column).setCellStyle(style);
}
FileOutputStream fo = new FileOutputStream(writeexel);
wb.write(fo);
}
public static void main(String[] args)throws Throwable {
ExelFileUtil xl = new ExelFileUtil("d:/Dummy.xlsx");
//count no of rows
int rc =xl.rowCount("Login");
//count no of cells in row
int cc =xl.cellCount("Login");
System.out.println(rc+"    "+cc);
for(int i=1;i<=rc;i++)
{
	String user =xl.getcelldata("Login", i, 0);
	String pass =xl.getcelldata("Login", i, 1);
	System.out.println(user+"    "+pass);
	xl.setcelldata("Login", i, 2, "Pass", "D://Results.xlsx");
}

}
}


