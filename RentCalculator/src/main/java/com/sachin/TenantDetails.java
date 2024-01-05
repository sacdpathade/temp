package com.sachin;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TenantDetails {

	private int floorNumber;

	private String tenantName;

	private int basicRent;

	private int msebPerUnitCost;

	private int previousMonthReading;

	private int currentMonthReading;

	private int waterElecticUsage;

	public TenantDetails(int floorNumber, String tenantName, double basicRent, double msebPerUnitCost) {
		super();
		this.floorNumber = floorNumber;
		this.tenantName = tenantName;
		this.basicRent = (int) basicRent;
		this.msebPerUnitCost = (int) msebPerUnitCost;
	}

	public int getFloorNumber() {
		return floorNumber;
	}

	public String getTenantName() {
		return tenantName;
	}

	public int getBasicRent() {
		return basicRent;
	}

	public int getMsebPerUnitCost() {
		return msebPerUnitCost;
	}

	public int getPreviousMonthReading() {
		return previousMonthReading;
	}

	public void setPreviousMonthReading(double previousMonthReading) {
		this.previousMonthReading = (int) previousMonthReading;
	}

	public int getCurrentMonthReading() {
		return currentMonthReading;
	}

	public void setCurrentMonthReading(double currentMonthReading) {
		this.currentMonthReading = (int) currentMonthReading;
	}

	public int getWaterElecticUsage() {
		return waterElecticUsage;
	}

	public void setWaterElecticUsage(double waterElecticUsage) {
		this.waterElecticUsage = (int) waterElecticUsage;
	}

	public String getTenantDetailsString() {
		StringBuilder builder = new StringBuilder();
		builder.append("##########-" + new SimpleDateFormat("MMM-yy").format(Calendar.getInstance().getTime())
				+ "-##########");
		builder.append("\n Name : " + tenantName);
		builder.append("\n ========= Floor:" + floorNumber + "=========");
		builder.append("\n चालू महिन्यातील रिडींग  : " + currentMonthReading);
		builder.append("\n मागील महिन्यातील रिडींग : " + previousMonthReading);
		int homeLightUsage = (currentMonthReading - previousMonthReading);
		builder.append("\n लाईट वापर : " + homeLightUsage + "\n पाणी मोटर वापर : " + waterElecticUsage);
		int totalLightUsage = homeLightUsage + waterElecticUsage;
		builder.append("\n एकूण लाईट वापर => " + totalLightUsage);
		builder.append("\n----------------------------");
		builder.append("\n घरभाडे  : " + basicRent);
		int lightCost = totalLightUsage * msebPerUnitCost;
		if(totalLightUsage > 60) {
			lightCost = lightCost + (totalLightUsage - 60);
		}
		builder.append("\n लाईटभाडे : " + lightCost);
		builder.append("\n============================");
		int totalRent = basicRent + lightCost;
		builder.append("\n एकूण भाडे = " + totalRent);
		builder.append("\n############################\n");

		String tenantDetailsStr = builder.toString();
		return getTenantDetailsString1();
	}

	public String getTenantDetailsString1() {
		StringBuilder builder = new StringBuilder();
		builder.append("\t ##############################################################");
		builder.append("\n\t\t Floor Number : "+ floorNumber + "\t\t\tName : " + tenantName);
		builder.append("\n\t ==============================================================");
		int homeLightUsage = (currentMonthReading - previousMonthReading);
		builder.append("\n\t\t चालू महिन्यातील रिडींग  : " + currentMonthReading + "\t\tलाईट वापर : " + homeLightUsage);
		builder.append("\n\t\t मागील महिन्यातील रिडींग : " + previousMonthReading + "\t\tपाणी मोटर वापर : " + waterElecticUsage);
		int totalLightUsage = homeLightUsage + waterElecticUsage;
		int lightCost = totalLightUsage * msebPerUnitCost;
		builder.append("\n\t       --------------एकूण लाईट वापर : " + totalLightUsage +"-------------------      ");
		builder.append("\n\t\t घरभाडे  : " + basicRent + "\t\t\t\tलाईटभाडे : " + lightCost);
		builder.append("\n\t ==============================================================");
		int totalRent = basicRent + lightCost;
		builder.append("\n\t\t एकूण भाडे = " + totalRent);
		builder.append("\n\t ##############################################################\n");

		String tenantDetailsStr = builder.toString();
		return tenantDetailsStr;
	}

	@Override
	public String toString() {
		return "TenantDetails [floorNumber=" + floorNumber + ", tenantName=" + tenantName + ", basicRent=" + basicRent
				+ ", previousMonthReading=" + previousMonthReading + ", currentMonthReading=" + currentMonthReading
				+ ", waterElecticUsage=" + waterElecticUsage + "]";
	}
}
