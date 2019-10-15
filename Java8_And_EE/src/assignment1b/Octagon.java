package assignment1b;
//Import Decimal format to format output
import java.text.DecimalFormat;
//Import Scanner
import java.util.Scanner;
//Create Octagon class, extending GeoFig2 and implementing interfaces
class Octagon<T> extends GeometricFigure2 implements ConsoleIO, ComparableGeometricFigure<T> {
	
	//Create length variable
	private double length;
	
	public Octagon(double length) {
		this.length = length;
	}
	
	//Create compare method
	public void compareTo(Object o) {
		//Create instance of octagon to compare with user octagon
		Octagon<T> a = new Octagon<T>(10);
		
		//If/Else statement to compare octagons(calling calcArea method) and print result to console
		if(a.calcArea() > ((Octagon<?>)o).calcArea())
			System.out.println("Your octagon is smaller than octagon A.");
		else if(a.calcArea() < ((Octagon<?>)o).calcArea())
			System.out.println("Your octagon is greater than octagon A.");
		else
			System.out.println("Your octagon is equal with octagon A.");;
		}
	
	//Method to set length of user octagon using scanner
	public void updateFromConsole() {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter length of side in cm: ");
		length = in.nextDouble();
		in.close();
	}
	
	//Print total length and area(calling calcarea method) of user octagon to console
	public void WriteToConsole() {
		DecimalFormat df = new DecimalFormat(".##");
		System.out.println("\nThe area of your octagon is: " + df.format((calcArea())) + " cm.");
		System.out.println("The length of your octagon sides is: " + length*8 + " cm.");
	}

	//Method to calculate area of octagon
	public double calcArea() {
		return (2 * (1 + (Math.sqrt(2)))* length * length);
	}
	
	//Override display method from super
	public void display() {
		System.out.println("\nThis is an octagon.\n");
	}	
}
