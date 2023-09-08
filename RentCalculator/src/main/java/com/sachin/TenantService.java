package com.sachin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TenantService {
	private Properties properties = new Properties();

	private TenantDetails tenant1;
	private TenantDetails tenant2;
	private TenantDetails tenant3;
	private int msebUnitRate = 0;

	public TenantService() {
		initializeTenantDetails();
	}

	private TenantDetails getTenantDetails(XSSFSheet sheet, int floorNumber) {
		Row rentRow = sheet.getRow(floorNumber);
		TenantDetails tenantDetails = new TenantDetails(floorNumber, rentRow.getCell(2).getStringCellValue(),
				rentRow.getCell(1).getNumericCellValue(), msebUnitRate);
		return tenantDetails;
	}

	private void updateTenantDetails(TenantDetails tenantDetails, Row previousMonthRow, Row thisMonthRow,
			String readingColumnProp, int waterUsage) {
		int columnNumber = Integer.parseInt(properties.getProperty(readingColumnProp));

		int previousMonthReading = (int) previousMonthRow.getCell(columnNumber).getNumericCellValue();
		int thisMonthReading = (int) thisMonthRow.getCell(columnNumber).getNumericCellValue();

		tenantDetails.setPreviousMonthReading(previousMonthReading);
		tenantDetails.setCurrentMonthReading(thisMonthReading);
		tenantDetails.setWaterElecticUsage(waterUsage);
	}

	public static Properties readPropertiesFile(String fileName) throws Exception {
		FileInputStream fis = null;
		Properties prop = null;
		try {
		   fis = new FileInputStream(fileName);
		   prop = new Properties();
		   prop.load(fis);
		} catch(Exception ioe) {
		   ioe.printStackTrace();
		} finally {
		   fis.close();
		}
		return prop;
	 }

	private void initializeTenantDetails() {
		try {
			// properties
			// 		.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));

			properties = readPropertiesFile(".\\src\\main\\resources\\application.properties");
			
			XSSFWorkbook wb = new XSSFWorkbook(
					new FileInputStream(new File(properties.getProperty(Constants.INPUT_FILE))));
			XSSFSheet sheet = wb.getSheetAt(0); // creating a Sheet object to retrieve object

			msebUnitRate = (int) (sheet
					.getRow(Integer.parseInt(properties.getProperty(Constants.MSEB_PER_UNIT_RATE_ROW)))).getCell(1)
					.getNumericCellValue();

			tenant1 = getTenantDetails(sheet, Integer.parseInt(properties.getProperty(Constants.FIRST_FLOOR_RENT_ROW)));
			tenant2 = getTenantDetails(sheet,
					Integer.parseInt(properties.getProperty(Constants.SECOND_FLOOR_RENT_ROW)));
			tenant3 = getTenantDetails(sheet, Integer.parseInt(properties.getProperty(Constants.THIRD_FLOOR_RENT_ROW)));

			int previousMonth = -1;
			int thisMonth = -1;
			for (int rowNumber = 0; rowNumber < sheet.getPhysicalNumberOfRows(); rowNumber++) {
				Row row = sheet.getRow(rowNumber);
				if (row.getCell(0).getCellType() == CellType.BLANK) {
					break;
				}
				previousMonth = thisMonth;
				thisMonth = rowNumber;
			}

			Row previousMonthRow = sheet.getRow(previousMonth);
			Row thisMonthRow = sheet.getRow(thisMonth);

			int previousMonthWaterReading = (int) previousMonthRow
					.getCell(Integer.parseInt(properties.getProperty(Constants.WATER_METER_READING_COL)))
					.getNumericCellValue();
			int thisMonthWaterReading = (int) thisMonthRow
					.getCell(Integer.parseInt(properties.getProperty(Constants.WATER_METER_READING_COL)))
					.getNumericCellValue();

			int waterUsage = (int) ((thisMonthWaterReading - previousMonthWaterReading + 6) / 3);

			updateTenantDetails(tenant1, previousMonthRow, thisMonthRow, Constants.FIRST_FLOOR_METER_READING_COL,
					waterUsage);
			updateTenantDetails(tenant2, previousMonthRow, thisMonthRow, Constants.SECOND_FLOOR_METER_READING_COL,
					waterUsage);
			updateTenantDetails(tenant3, previousMonthRow, thisMonthRow, Constants.THIRD_FLOOR_METER_READING_COL,
					waterUsage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public TenantDetails getTenant1() {
		return tenant1;
	}

	public TenantDetails getTenant2() {
		return tenant2;
	}

	public TenantDetails getTenant3() {
		return tenant3;
	}

	public int getMsebUnitRate() {
		return msebUnitRate;
	}
}