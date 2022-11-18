package model;

public class eBike extends Bike{
    private int batteryPercentage;
    private int loadCycle;
    private double usageTimeRemaining;
    
	public int getBatteryPercentage() {
		return batteryPercentage;
	}
	public void setBatteryPercentage(int batteryPercentage) {
		this.batteryPercentage = batteryPercentage;
	}
	public int getLoadCycle() {
		return loadCycle;
	}
	public void setLoadCycle(int loadCycle) {
		this.loadCycle = loadCycle;
	}
	public double getUsageTimeRemaining() {
		return usageTimeRemaining;
	}
	public void setUsageTimeRemaining(double usageTimeRemaining) {
		this.usageTimeRemaining = usageTimeRemaining;
	}
}