package assignment4_Lambda;

//Import Scanner
import java.util.*;

public class WeatherTest {

	public static void main(String[] args) {
		//Create new Scanner
		Scanner in = new Scanner(System.in);
		
		//Create and assign variables using user input
		System.out.print("Enter City: ");
		String city = in.next();
		
		System.out.print("Please enter start time: ");
		int start = in.nextInt();
		
		System.out.print("Please enter end time: ");
		int end = in.nextInt();
		
		//Create instance of WeatherStation
		WeatherStation ws = new WeatherStation();
		
		//Call average tempreture method passing in user input
		ws.avgTemp(city, start, end);
		
		//Close Scanner
		in.close();

	}
}
