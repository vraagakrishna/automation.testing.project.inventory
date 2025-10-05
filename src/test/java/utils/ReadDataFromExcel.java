package utils;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadDataFromExcel {

    static final String excelDir = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "user-data.xlsx";

    static FileInputStream fileInputStream;

    static XSSFWorkbook workbook;

    public ReadDataFromExcel() throws IOException {
        fileInputStream = new FileInputStream(excelDir);
        workbook = new XSSFWorkbook(fileInputStream);
    }

    public String getStringDataFromExcel(String sheetName, int rowNumber, int cellNumber) {
        XSSFSheet sheet = workbook.getSheet(sheetName);
        return sheet.getRow(rowNumber).getCell(cellNumber).getStringCellValue();
    }

}
