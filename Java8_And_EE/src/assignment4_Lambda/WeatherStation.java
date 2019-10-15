package assignment4_Lambda;

//Import ArraList and DecimalFormat
import java.util.*;
import java.text.*;

public class WeatherStation {

	//Create and populate array list of type Measurement
	private List<Measurement> measurements = Arrays.asList(
			new Measurement("Galway", 1, 10.00),
			new Measurement("Galway", 3, 20.00),
			new Measurement("Galway", 6, 6.20),
			new Measurement("Mayo", 1, 12.30),
			new Measurement("Mayo", 3, 9.50),
			new Measurement("Mayo", 6, 7.40),
			new Measurement("Sligo", 1, 19.50),
			new Measurement("Sligo", 3, 300.10),
			new Measurement("Sligo", 6, 21.20)
			);
	
	//Create format for print statement
	DecimalFormat df = new DecimalFormat("0.00");
	
		//Create method to get average temperature
		public void avgTemp(String city, int startTime, int endTime) {
			
		//Assign output to double avTemp
		//Create Java 8 stream for measurements list, use .filter and lambda expression to select entries with correct city
		double avTemp = measurements.stream().filter(m -> m.getCity().equalsIgnoreCase(city))
		//Use .filter and lambda expression to find correct time period 
		.filter(m -> m.getTime() >= startTime &&(m.getTime() <= endTime))
		//Get average of temperatures in that time period
		.mapToDouble(m -> m.getTemp())
		.average()
		.getAsDouble();
	
		//Print statement with average temperature 
		System.out.println("The average temperature for " + city + " between the hours of " + startTime + " and " 
				+ endTime + " o'clock is: " + df.format(avTemp) + " degrees celcius");
	}	
}
