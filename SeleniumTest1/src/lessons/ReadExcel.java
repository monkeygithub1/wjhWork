package lessons;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadExcel {

	public static void main(String[] args) {
		try{
			File src = new File(".\\Files\\test-data.xlsx");
			FileInputStream fis = new FileInputStream(src);
			
			@SuppressWarnings("resource")
			XSSFWorkbook wb=new XSSFWorkbook(fis);
			
			XSSFSheet sh1 = wb.getSheetAt(0);
			
//			System.out.println(sh1.getRow(0).getCell(0).getStringCellValue());
//			System.out.println(sh1.getRow(0).getCell(1).getStringCellValue());
//			System.out.println(sh1.getRow(1).getCell(0).getStringCellValue());
//			System.out.println(sh1.getRow(1).getCell(1).getStringCellValue());
			
			System.out.println(sh1.getPhysicalNumberOfRows()+"rows");
			System.out.println(sh1.getRow(0).getPhysicalNumberOfCells()+"cells");
			for(int i=0;i<sh1.getPhysicalNumberOfRows();i++){
				for(int j=0;j<sh1.getRow(0).getPhysicalNumberOfCells();j++){
					System.out.println(sh1.getRow(i).getCell(j).getStringCellValue());
				}
			}
			
			sh1.getRow(0).createCell(2).setCellValue("Pass");
			sh1.getRow(1).createCell(2).setCellValue("Fail");
			sh1.getRow(2).createCell(2).setCellValue("NULL");
			sh1.getRow(3).createCell(2).setCellValue("undefined");
			
			FileOutputStream fout = new FileOutputStream(new File(".\\Files\\test-data.xlsx"));
			wb.write(fout);
			fout.close();
		
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

	}

}
