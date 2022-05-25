package nag.worldcup.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelUtils {

	private static final Logger LOGGER = Logger.getLogger(ExcelUtils.class);

	public void createExclSheet(String path, String sheetName, List<String> colArray) throws IOException {
		// Creating file object of existing excel file
		File xlsxFile = new File(path);
		// Creating input stream
		FileInputStream inputStream = new FileInputStream(xlsxFile);
		// Creating workbook from input stream
		HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
		HSSFSheet sheet = workbook.createSheet(sheetName);
		HSSFRow rowhead = sheet.createRow((short) 0);
		// creating cell by using the createCell() method and setting the values to the
		// cell by using the setCellValue() method
		for (int i=0;i<colArray.size();i++) {
			rowhead.createCell(i).setCellValue(colArray.get(i));
		}
		inputStream.close();

		// Crating output stream and writing the updated workbook
		FileOutputStream os = new FileOutputStream(xlsxFile);
		workbook.write(os);

		// Close the workbook and output stream
		os.close();
	}

	public void writeExcel(String path, String sheetname, List<String> details) throws IOException {
		File xlsxFile = new File(path);
		// Creating input stream
		FileInputStream inputStream = new FileInputStream(xlsxFile);
		// Creating workbook from input stream
		HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
		HSSFSheet sheet = workbook.getSheet(sheetname);

		int rw = 1;
		for (String detail : details) {
			HSSFRow row = sheet.createRow((short) rw++);
			row.createCell(0).setCellValue(detail.split(";")[0]);
			row.createCell(1).setCellValue(detail.split(";")[1]);

		}
		inputStream.close();

		// Crating output stream and writing the updated workbook
		FileOutputStream os = new FileOutputStream(xlsxFile);
		workbook.write(os);

		// Close the workbook and output stream
		os.close();

	}

	public void writeExcel1(String path, String sheetname, Map<String, String> details) throws IOException {
		File xlsxFile = new File(path);
		// Creating input stream
		FileInputStream inputStream = new FileInputStream(xlsxFile);
		// Creating workbook from input stream
		HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
		HSSFSheet sheet = workbook.getSheet(sheetname);

		int rw = 1;
		for (Map.Entry<String, String> entry : details.entrySet()) {
			HSSFRow row = sheet.createRow((short) rw++);
			row.createCell(0).setCellValue(entry.getKey());
			row.createCell(1).setCellValue(entry.getValue().split(";")[0]);
			row.createCell(2).setCellValue(entry.getValue().split(";")[1]);
		}

		inputStream.close();

		// Crating output stream and writing the updated workbook
		FileOutputStream os = new FileOutputStream(xlsxFile);
		workbook.write(os);

		// Close the workbook and output stream
		os.close();

	}

	public void writeExcel(String path, String sheetname, Map<String, Integer> details) throws IOException {
		File xlsxFile = new File(path);
		// Creating input stream
		FileInputStream inputStream = new FileInputStream(xlsxFile);
		// Creating workbook from input stream
		HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
		HSSFSheet sheet = workbook.getSheet(sheetname);

		int rw = 1;
		for (Map.Entry<String, Integer> entry : details.entrySet()) {
			HSSFRow row = sheet.createRow((short) rw++);
			row.createCell(0).setCellValue(entry.getKey());
			row.createCell(1).setCellValue(entry.getValue());
		}

		inputStream.close();

		// Crating output stream and writing the updated workbook
		FileOutputStream os = new FileOutputStream(xlsxFile);
		workbook.write(os);

		// Close the workbook and output stream
		os.close();

	}

	public void createExcel(String path) throws IOException {
		Workbook wb = new HSSFWorkbook();
		// creates an excel file at the specified location
		OutputStream fileOut = new FileOutputStream(path);
		LOGGER.info("Excel File has been created successfully.");
		wb.write(fileOut);
	}

}
