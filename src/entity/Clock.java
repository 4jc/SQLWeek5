package entity;

public class Clock {

	private int clockId;
	private String brand;
	private String type;
	
	public Clock(int clockId, String type, String brand) {
		this.setClockId(clockId);
		this.setType(type);
		this.setBrand(brand);
	}

	public int getClockId() {
		return clockId;
	}

	public void setClockId(int clockId) {
		this.clockId = clockId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}	
}
