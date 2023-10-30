package Car;

public class Car {
	private String CarType;
	private String Color;
	private int Miles;
	private int Year;
	
	public String getCarType() {
		return CarType;
	}
	public String getColor() {
		return Color;
	}
	public int getMiles() {
		return Miles;
	}
	public int getYear() {
		return Year;
	}
	public void setCarType(String CarType) {
		this.CarType = CarType;
	}
	public void setColor(String Color) {
		this.Color = Color;
	}
	public void setMiles(int Miles) {
		this.Miles = Miles;
	}
	public void setYear(int Year) {
		this.Year = Year;
	}
}