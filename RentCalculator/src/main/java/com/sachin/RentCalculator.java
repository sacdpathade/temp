package com.sachin;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RentCalculator {
	public static void main(String[] args) throws IOException {
		TenantService tenantService = new TenantService();

		String fileName = new SimpleDateFormat("MMM-yy").format(Calendar.getInstance().getTime());
		FileWriter myWriter = new FileWriter("C:\\Users\\spathade\\Desktop\\SasaneColonyRent\\" + fileName + ".txt");
		myWriter.write(tenantService.getTenant1().getTenantDetailsString() + "\n");
		myWriter.write(tenantService.getTenant2().getTenantDetailsString()+ "\n");
		myWriter.write(tenantService.getTenant3().getTenantDetailsString());
		myWriter.close();

	}
}