package assignment4_Lambda;

public class Measurement {
	
	//Create private variables
	private int time;
	private double temp;
	private String city;
	
	//Create measurement constructor
	public Measurement(String city, int time, double temp) {
		this.city = city;
		this.time = time;
		this.temp = temp;
	}
	
	//Create setters for variables
	public String getCity() {
		return city;
	}
	
	public int getTime() {
		return time;
	}

	public double getTemp() {
		return temp;
	}
}
